package com.map;

public class Fi_Travelsubstandard{    
    
	private java.lang.Long id;  //主键ID
	private java.lang.Long arealevelid;  //区域ID
	private java.lang.Integer admlevel;  //行政级别
	private java.math.BigDecimal travel_substandards;  //补贴标准
	private String attribution;  //归属地:成都/昆明
	private String remark;  //备注
    
  public java.lang.Long getId() {
   	return this.id;
	}
	public void setId(java.lang.Long value) {
		this.id = value;
	}
  public java.lang.Long getArealevelid() {
   	return this.arealevelid;
	}
	public void setArealevelid(java.lang.Long value) {
		this.arealevelid = value;
	}
  public java.lang.Integer getAdmlevel() {
   	return this.admlevel;
	}
	public void setAdmlevel(java.lang.Integer value) {
		this.admlevel = value;
	}
  public java.math.BigDecimal getTravel_substandards() {
   	return this.travel_substandards;
	}
	public void setTravel_substandards(java.math.BigDecimal value) {
		this.travel_substandards = value;
	}
  public String getAttribution() {
   	return this.attribution;
	}
	public void setAttribution(String value) {
		this.attribution = value;
	}
  public String getRemark() {
   	return this.remark;
	}
	public void setRemark(String value) {
		this.remark = value;
	}

}
