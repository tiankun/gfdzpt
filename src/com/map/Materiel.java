package com.map;

public class Materiel{    
    
	private java.math.BigDecimal id;  //
	private java.math.BigDecimal kind_id;  //类型
	private java.math.BigDecimal brand_id;  //品牌
	private String name;  //名称
	private String shortcode;  //简码
	private String model;  //规格型号
	private String unit;  //计量单位
	private String parameter;  //产品参数
	private java.math.BigDecimal lrr;  //录入人
	private java.math.BigDecimal yxbz;  //有效标志
	private String note;  //备注
    
  public java.math.BigDecimal getId() {
   	return this.id;
	}
	public void setId(java.math.BigDecimal value) {
		this.id = value;
	}
  public java.math.BigDecimal getKind_id() {
   	return this.kind_id;
	}
	public void setKind_id(java.math.BigDecimal value) {
		this.kind_id = value;
	}
  public java.math.BigDecimal getBrand_id() {
   	return this.brand_id;
	}
	public void setBrand_id(java.math.BigDecimal value) {
		this.brand_id = value;
	}
  public String getName() {
   	return this.name;
	}
	public void setName(String value) {
		this.name = value;
	}
  public String getShortcode() {
   	return this.shortcode;
	}
	public void setShortcode(String value) {
		this.shortcode = value;
	}
  public String getModel() {
   	return this.model;
	}
	public void setModel(String value) {
		this.model = value;
	}
  public String getUnit() {
   	return this.unit;
	}
	public void setUnit(String value) {
		this.unit = value;
	}
  public String getParameter() {
   	return this.parameter;
	}
	public void setParameter(String value) {
		this.parameter = value;
	}
  public java.math.BigDecimal getLrr() {
   	return this.lrr;
	}
	public void setLrr(java.math.BigDecimal value) {
		this.lrr = value;
	}
  public java.math.BigDecimal getYxbz() {
   	return this.yxbz;
	}
	public void setYxbz(java.math.BigDecimal value) {
		this.yxbz = value;
	}
  public String getNote() {
   	return this.note;
	}
	public void setNote(String value) {
		this.note = value;
	}

}
