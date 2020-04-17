package com.ruoyi.project.bus.DictMediaType.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 媒体类型管理对象 bus_dict_media_type
 * 
 * @author goonie
 * @date 2020-04-12
 */
public class DictMediaType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long mediaTypeId;

    /** 名称[{"required":"1"}] */
    @Excel(name = "名称")
    private String mediaTypeName;

    /** 描述 */
    @Excel(name = "描述")
    private String mediaTypeDescription;

    public void setMediaTypeId(Long mediaTypeId) 
    {
        this.mediaTypeId = mediaTypeId;
    }

    public Long getMediaTypeId() 
    {
        return mediaTypeId;
    }
    public void setMediaTypeName(String mediaTypeName) 
    {
        this.mediaTypeName = mediaTypeName;
    }

    public String getMediaTypeName() 
    {
        return mediaTypeName;
    }
    public void setMediaTypeDescription(String mediaTypeDescription) 
    {
        this.mediaTypeDescription = mediaTypeDescription;
    }

    public String getMediaTypeDescription() 
    {
        return mediaTypeDescription;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mediaTypeId", getMediaTypeId())
            .append("mediaTypeName", getMediaTypeName())
            .append("mediaTypeDescription", getMediaTypeDescription())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .toString();
    }
}
