package com.ruoyi.project.bus.DictMediaType.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.bus.DictMediaType.mapper.DictMediaTypeMapper;
import com.ruoyi.project.bus.DictMediaType.domain.DictMediaType;
import com.ruoyi.project.bus.DictMediaType.service.IDictMediaTypeService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 媒体类型管理Service业务层处理
 * 
 * @author goonie
 * @date 2020-04-12
 */
@Service
public class DictMediaTypeServiceImpl implements IDictMediaTypeService 
{
    @Autowired
    private DictMediaTypeMapper dictMediaTypeMapper;

    /**
     * 查询媒体类型管理
     * 
     * @param mediaTypeId 媒体类型管理ID
     * @return 媒体类型管理
     */
    @Override
    public DictMediaType selectDictMediaTypeById(Long mediaTypeId)
    {
        return dictMediaTypeMapper.selectDictMediaTypeById(mediaTypeId);
    }

    /**
     * 查询媒体类型管理列表
     * 
     * @param dictMediaType 媒体类型管理
     * @return 媒体类型管理
     */
    @Override
    public List<DictMediaType> selectDictMediaTypeList(DictMediaType dictMediaType)
    {
        return dictMediaTypeMapper.selectDictMediaTypeList(dictMediaType);
    }

    /**
     * 新增媒体类型管理
     * 
     * @param dictMediaType 媒体类型管理
     * @return 结果
     */
    @Override
    public int insertDictMediaType(DictMediaType dictMediaType)
    {
        dictMediaType.setCreateTime(DateUtils.getNowDate());
        return dictMediaTypeMapper.insertDictMediaType(dictMediaType);
    }

    /**
     * 修改媒体类型管理
     * 
     * @param dictMediaType 媒体类型管理
     * @return 结果
     */
    @Override
    public int updateDictMediaType(DictMediaType dictMediaType)
    {
        dictMediaType.setUpdateTime(DateUtils.getNowDate());
        return dictMediaTypeMapper.updateDictMediaType(dictMediaType);
    }

    /**
     * 删除媒体类型管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDictMediaTypeByIds(String ids)
    {
        return dictMediaTypeMapper.deleteDictMediaTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除媒体类型管理信息
     * 
     * @param mediaTypeId 媒体类型管理ID
     * @return 结果
     */
    @Override
    public int deleteDictMediaTypeById(Long mediaTypeId)
    {
        return dictMediaTypeMapper.deleteDictMediaTypeById(mediaTypeId);
    }
}
