package com.ruoyi.project.bus.DictMediaType.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.bus.DictMediaType.domain.DictMediaType;
import com.ruoyi.project.bus.DictMediaType.service.IDictMediaTypeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 媒体类型管理Controller
 * 
 * @author goonie
 * @date 2020-04-12
 */
@Controller
@RequestMapping("/bus/DictMediaType")
public class DictMediaTypeController extends BaseController
{
    private String prefix = "bus/DictMediaType";

    @Autowired
    private IDictMediaTypeService dictMediaTypeService;

    @RequiresPermissions("bus:DictMediaType:view")
    @GetMapping()
    public String DictMediaType(ModelMap mmap)
    {
        return prefix + "/DictMediaType";
    }

    /**
     * 查询媒体类型管理列表
     */
    @RequiresPermissions("bus:DictMediaType:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DictMediaType dictMediaType)
    {
        startPage();
        List<DictMediaType> list = dictMediaTypeService.selectDictMediaTypeList(dictMediaType);
        return getDataTable(list);
    }

    /**
     * 导出媒体类型管理列表
     */
    @RequiresPermissions("bus:DictMediaType:export")
    @Log(title = "媒体类型管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DictMediaType dictMediaType)
    {
        List<DictMediaType> list = dictMediaTypeService.selectDictMediaTypeList(dictMediaType);
        ExcelUtil<DictMediaType> util = new ExcelUtil<DictMediaType>(DictMediaType.class);
        return util.exportExcel(list, "DictMediaType");
    }

    /**
     * 新增媒体类型管理
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        return prefix + "/add";
    }

    /**
     * 新增保存媒体类型管理
     */
    @RequiresPermissions("bus:DictMediaType:add")
    @Log(title = "媒体类型管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DictMediaType dictMediaType)
    {
        return toAjax(dictMediaTypeService.insertDictMediaType(dictMediaType));
    }

    /**
     * 修改媒体类型管理
     */
    @GetMapping("/edit/{mediaTypeId}")
    public String edit(@PathVariable("mediaTypeId") Long mediaTypeId, ModelMap mmap)
    {
        DictMediaType dictMediaType = dictMediaTypeService.selectDictMediaTypeById(mediaTypeId);
        mmap.put("dictMediaType", dictMediaType);
        return prefix + "/edit";
    }

    /**
     * 修改保存媒体类型管理
     */
    @RequiresPermissions("bus:DictMediaType:edit")
    @Log(title = "媒体类型管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DictMediaType dictMediaType)
    {
        return toAjax(dictMediaTypeService.updateDictMediaType(dictMediaType));
    }

    /**
     * 删除媒体类型管理
     */
    @RequiresPermissions("bus:DictMediaType:remove")
    @Log(title = "媒体类型管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(dictMediaTypeService.deleteDictMediaTypeByIds(ids));
    }
}
