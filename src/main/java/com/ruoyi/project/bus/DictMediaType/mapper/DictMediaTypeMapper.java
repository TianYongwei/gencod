package com.ruoyi.project.bus.DictMediaType.mapper;

import com.ruoyi.project.bus.DictMediaType.domain.DictMediaType;
import java.util.List;

/**
 * 媒体类型管理Mapper接口
 * 
 * @author goonie
 * @date 2020-04-12
 */
public interface DictMediaTypeMapper 
{
    /**
     * 查询媒体类型管理
     * 
     * @param mediaTypeId 媒体类型管理ID
     * @return 媒体类型管理
     */
    public DictMediaType selectDictMediaTypeById(Long mediaTypeId);

    /**
     * 查询媒体类型管理列表
     * 
     * @param dictMediaType 媒体类型管理
     * @return 媒体类型管理集合
     */
    public List<DictMediaType> selectDictMediaTypeList(DictMediaType dictMediaType);

    /**
    * 查询媒体类型管理列表
    *
    * @return 媒体类型管理集合
    */
    public List<DictMediaType> selectAll();

    /**
     * 新增媒体类型管理
     * 
     * @param dictMediaType 媒体类型管理
     * @return 结果
     */
    public int insertDictMediaType(DictMediaType dictMediaType);

    /**
     * 修改媒体类型管理
     * 
     * @param dictMediaType 媒体类型管理
     * @return 结果
     */
    public int updateDictMediaType(DictMediaType dictMediaType);

    /**
     * 删除媒体类型管理
     * 
     * @param mediaTypeId 媒体类型管理ID
     * @return 结果
     */
    public int deleteDictMediaTypeById(Long mediaTypeId);

    /**
     * 批量删除媒体类型管理
     * 
     * @param mediaTypeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteDictMediaTypeByIds(String[] mediaTypeIds);
}
