package com.ruoyi.project.tool.esmapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;

public class ESMappingController {

  private static final String FILE_NAME = System.getProperty("user.dir") + "/src/main/resources/mapping/新闻大数据ES库表结构设计说明(含字典)0417来自涛哥.xlsx";

  public static void main(String[] args) {
    JSONObject mappingJson = new JSONObject(true);
    // todo 添加其他index配置！
    JSONObject propertiesJson = new JSONObject(true);
    try {
      FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
      Workbook workbook = new XSSFWorkbook(excelFile);
      Sheet datatypeSheet = workbook.getSheetAt(0);// 只读取第一个 sheet
      Iterator<Row> iterator = datatypeSheet.iterator();
      Boolean isFirstLine = Boolean.TRUE;
      while (iterator.hasNext()) {
        Row currentRow = iterator.next();
        if(isFirstLine) {
          isFirstLine = Boolean.FALSE;
          continue;
        }
        Iterator<Cell> cellIterator = currentRow.iterator();

        String fieldName = "";// 字段名
        String dataType = "";// 数据类型
        String fulltext = "";// 全文索引
        String dateFormat = "";// 日期格式
        while (cellIterator.hasNext()) {
          Cell currentCell = cellIterator.next();
          if(currentCell.getColumnIndex() == 3) {
            fieldName = currentCell.getStringCellValue();
          }

          if(currentCell.getColumnIndex() == 4) {
            dataType = currentCell.getStringCellValue();
          }

          if(currentCell.getColumnIndex() == 5) {
            fulltext = currentCell.getStringCellValue();
          }

          if(currentCell.getColumnIndex() == 6) {
            dateFormat = currentCell.getStringCellValue();
          }
        }
        System.out.print("fieldName:" + fieldName);
        System.out.print("  --dataType:" + dataType);
        System.out.print("  --fulltext:" + fulltext);
        System.out.print("  --dateFormat:" + dateFormat);
        System.out.println();

        PropertiesField propertiesField = new PropertiesField(dataType);
        //
        if(StringUtils.isNotBlank(dateFormat)) {
          propertiesField.setFormat(dateFormat);
        } else {
          if(StringUtils.isNotBlank(fulltext)) {
            propertiesField.setFields(
                    new PropertiesField.RawFullTextFieldWrapper(
                            new PropertiesField.RawFullTextField(fulltext)));
          }
        }
        propertiesJson.put(fieldName, JSON.parseObject(JSON.toJSONString(propertiesField)));
      }

      mappingJson.put("properties", propertiesJson);
      System.out.println(mappingJson.toString(SerializerFeature.PrettyFormat));

      final String string = mappingJson.toString(SerializerFeature.PrettyFormat);
      // 保存到文件
      FileUtils.writeStringToFile(new File(System.getProperty("user.dir") + "/src/main/resources/mapping/temp_mapping.json"), string, Charset.forName("UTF-8"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
