package com.ruoyi.project.tool.esmapping;

import com.alibaba.fastjson.annotation.JSONField;

public class PropertiesField {
  public PropertiesField(String type) {
    this.type = type;
  }

  @JSONField(ordinal = 1)
  private String type;
  @JSONField(ordinal = 0)
  private String format;
  @JSONField(ordinal = 3)
  private RawFullTextFieldWrapper fields;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public RawFullTextFieldWrapper getFields() {
    return fields;
  }

  public void setFields(RawFullTextFieldWrapper fields) {
    this.fields = fields;
  }

  static class RawFullTextFieldWrapper {

    public RawFullTextFieldWrapper(RawFullTextField raw_fulltext) {
      this.raw_fulltext = raw_fulltext;
    }

    private RawFullTextField raw_fulltext;

    public RawFullTextField getRaw_fulltext() {
      return raw_fulltext;
    }

    public void setRaw_fulltext(RawFullTextField raw_fulltext) {
      this.raw_fulltext = raw_fulltext;
    }
  }

  static class RawFullTextField {
    public RawFullTextField(String type) {
      this.type = type;
    }

    private String type;

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }
  }
}
