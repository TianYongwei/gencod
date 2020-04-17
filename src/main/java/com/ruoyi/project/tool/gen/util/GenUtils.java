package com.ruoyi.project.tool.gen.util;

import java.util.Arrays;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.project.tool.gen.mapper.GenTableMapper;
import org.apache.commons.lang3.RegExUtils;
import com.ruoyi.common.constant.GenConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.config.GenConfig;
import com.ruoyi.project.tool.gen.domain.GenTable;
import com.ruoyi.project.tool.gen.domain.GenTableColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 代码生成器 工具类
 * 
 * @author ruoyi
 */
@Component
public class GenUtils
{

    @Autowired
    private GenTableMapper tableMapper;
    /**
     * 初始化表信息
     */
    public void initTable(GenTable genTable, String operName)
    {
        genTable.setClassName(convertClassName(genTable.getTableName()));
        genTable.setPackageName(GenConfig.getPackageName());
        genTable.setModuleName(getModuleName(GenConfig.getPackageName()));
        /** author:田永威 -- start */
        // 改成 convertClassName 方法
        // genTable.setBusinessName(getBusinessName(genTable.getTableName()));
        genTable.setBusinessName(convertClassName(genTable.getTableName()));
        // tree 相关
        String tableComment = replaceText(genTable.getTableComment());
        String functionName = tableComment;
        if(tableComment.contains("[") && tableComment.contains("]")) {
            String treeOptions = tableComment.substring(tableComment.indexOf('[') + 1, tableComment.indexOf(']'));
            tableComment = tableComment.replaceAll("\\[.*\\]", "");
            functionName = tableComment;
            if(StringUtils.isNotBlank(treeOptions)) {
                genTable.setTplCategory("tree");
                genTable.setOptions(treeOptions);
            }
        }
        genTable.setFunctionName(functionName);
        genTable.setTableComment(tableComment);
        /** author:田永威 -- end */
        genTable.setFunctionAuthor(GenConfig.getAuthor());
        genTable.setCreateBy(operName);
    }

    /**
     * 初始化列属性字段
     */
    public void initColumnField(GenTableColumn column, GenTable table)
    {
        String dataType = getDbType(column.getColumnType());
        String columnName = column.getColumnName();
        String columnComment = column.getColumnComment();
        String busDictType = "";
        String tableName = "";
        String required = "";
        String isFilterVisible = "1";
        String isListVisible = "1";
        if(StringUtils.isNotBlank(columnComment)
                && columnComment.contains("[") && columnComment.contains("]")) {
            String commentStr = columnComment.substring(columnComment.indexOf('[') + 1, columnComment.indexOf(']'));
            JSONObject commentJson = JSON.parseObject(commentStr);
            tableName = commentJson.getString("bus_dict");
            busDictType = commentJson.getString("bus_dict");
            required = commentJson.getString("required");
            isFilterVisible = commentJson.getString("is_filter_visible");
            isListVisible = commentJson.getString("is_list_visible");

            if(StringUtils.isNotBlank(busDictType)) {
                busDictType = StringUtils.toCamelCase(busDictType.replace("bus_", ""));
            }
        }
        column.setIsRequired(required);
        column.setIsFilterVisible(isFilterVisible);
        column.setIsListVisible(isListVisible);
        column.setDictType(busDictType);

        column.setTableId(table.getTableId());
        column.setCreateBy(table.getCreateBy());
        // 设置java字段名
        column.setJavaField(StringUtils.toCamelCase(columnName));
        if (arraysContains(GenConstants.COLUMNTYPE_STR, dataType))
        {
            column.setJavaType(GenConstants.TYPE_STRING);
            // 字符串长度超过500设置为文本域
            Integer columnLength = getColumnLength(column.getColumnType());
            String htmlType = columnLength >= 500 ? GenConstants.HTML_TEXTAREA : GenConstants.HTML_INPUT;
            column.setHtmlType(htmlType);
        }
        else if (arraysContains(GenConstants.COLUMNTYPE_TIME, dataType))
        {
            column.setJavaType(GenConstants.TYPE_DATE);
            column.setHtmlType(GenConstants.HTML_DATETIME);
        }
        else if (arraysContains(GenConstants.COLUMNTYPE_NUMBER, dataType))
        {
            column.setHtmlType(GenConstants.HTML_INPUT);

            // 如果是浮点型
            String[] str = StringUtils.split(StringUtils.substringBetween(column.getColumnType(), "(", ")"), ",");
            if (str != null && str.length == 2 && Integer.parseInt(str[1]) > 0)
            {
                column.setJavaType(GenConstants.TYPE_DOUBLE);
            }
            // 如果是整形
            else if (str != null && str.length == 1 && Integer.parseInt(str[0]) <= 10)
            {
                column.setJavaType(GenConstants.TYPE_INTEGER);
            }
            // 长整形
            else
            {
                column.setJavaType(GenConstants.TYPE_LONG);
            }
        }

        // 插入字段（默认所有字段都需要插入）
        column.setIsInsert(GenConstants.REQUIRE);

        // 编辑字段
        if (!arraysContains(GenConstants.COLUMNNAME_NOT_EDIT, columnName) && !column.isPk())
        {
            column.setIsEdit(GenConstants.REQUIRE);
        }
        // 列表字段
        if (!arraysContains(GenConstants.COLUMNNAME_NOT_LIST, columnName) && !column.isPk())
        {
            column.setIsList(GenConstants.REQUIRE);
        }
        // 查询字段
        if (!arraysContains(GenConstants.COLUMNNAME_NOT_QUERY, columnName) && !column.isPk())
        {
            column.setIsQuery(GenConstants.REQUIRE);
        }

        // 查询字段类型
        if (StringUtils.endsWithIgnoreCase(columnName, "name"))
        {
            column.setQueryType(GenConstants.QUERY_LIKE);
        }
        //
        GenTable genTable = tableMapper.selectGenTableByName(tableName);
        if(genTable != null) {
            if (GenConstants.HTML_TREE.equals(genTable.getTplCategory())) {
                column.setHtmlType(GenConstants.HTML_TREE);
            } else {
                column.setHtmlType(GenConstants.HTML_SELECT);
            }
        }
        // 状态字段设置单选框
        else if (StringUtils.endsWithIgnoreCase(columnName, "status"))
        {
            column.setHtmlType(GenConstants.HTML_RADIO);
        }
        // 性别字段设置下拉框
        else if (StringUtils.endsWithIgnoreCase(columnName, "sex"))
        {
            column.setHtmlType(GenConstants.HTML_SELECT);
        }
        // 类型字段设置下拉框
        else if (StringUtils.endsWithIgnoreCase(columnName, "type"))
        {
            column.setHtmlType(GenConstants.HTML_SELECT);
        }
    }

    /**
     * 校验数组是否包含指定值
     * 
     * @param arr 数组
     * @param targetValue 值
     * @return 是否包含
     */
    public  boolean arraysContains(String[] arr, String targetValue)
    {
        return Arrays.asList(arr).contains(targetValue);
    }

    /**
     * 获取模块名
     * 
     * @param packageName 包名
     * @return 模块名
     */
    public String getModuleName(String packageName)
    {
        int lastIndex = packageName.lastIndexOf(".");
        int nameLength = packageName.length();
        String moduleName = StringUtils.substring(packageName, lastIndex + 1, nameLength);
        return moduleName;
    }

    /**
     * 获取业务名
     * 
     * @param tableName 表名
     * @return 业务名
     */
    public String getBusinessName(String tableName)
    {
        int lastIndex = tableName.lastIndexOf("_");
        int nameLength = tableName.length();
        String businessName = StringUtils.substring(tableName, lastIndex + 1, nameLength);
        return businessName;
    }

    /**
     * 表名转换成Java类名
     * 
     * @param tableName 表名称
     * @return 类名
     */
    public String convertClassName(String tableName)
    {
        boolean autoRemovePre = GenConfig.getAutoRemovePre();
        String tablePrefix = GenConfig.getTablePrefix();
        if (autoRemovePre && StringUtils.isNotEmpty(tablePrefix))
        {
            String[] searchList = StringUtils.split(tablePrefix, ",");
            String[] replacementList = emptyList(searchList.length);
            tableName = StringUtils.replaceEach(tableName, searchList, replacementList);
        }
        return StringUtils.convertToCamelCase(tableName);
    }

    /**
     * 关键字替换
     * 
     * @param text 需要被替换的名字
     * @return 替换后的名字
     */
    public String replaceText(String text)
    {
        return RegExUtils.replaceAll(text, "(?:表|若依)", "");
    }

    /**
     * 获取数据库类型字段
     * 
     * @param columnType 列类型
     * @return 截取后的列类型
     */
    public String getDbType(String columnType)
    {
        if (StringUtils.indexOf(columnType, "(") > 0)
        {
            return StringUtils.substringBefore(columnType, "(");
        }
        else
        {
            return columnType;
        }
    }

    /**
     * 获取字段长度
     * 
     * @param columnType 列类型
     * @return 截取后的列类型
     */
    public Integer getColumnLength(String columnType)
    {
        if (StringUtils.indexOf(columnType, "(") > 0)
        {
            String length = StringUtils.substringBetween(columnType, "(", ")");
            return Integer.valueOf(length);
        }
        else
        {
            return 0;
        }
    }

    /**
     * 获取空数组列表
     * 
     * @param length 长度
     * @return 数组信息
     */
    public String[] emptyList(int length)
    {
        String[] values = new String[length];
        for (int i = 0; i < length; i++)
        {
            values[i] = StringUtils.EMPTY;
        }
        return values;
    }
}