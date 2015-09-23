package com.map;

public class Fi_TravelExpenseDetail{    
    
	private String id;  //主键ID
	private String travelexpenseid;  //出差报帐主表ID
	private java.sql.Date startime;  //出发时间
	private java.sql.Date endtime;  //到达时间
	private String startplace;  //出发地
	private String endplace;  //目的地
	private String travel_selfcar;  //是否自驾/公司车辆:按补贴的85%报
	private String totalnum;  //附件数
	private String persomnum;  //人数
	private String daytotal;  //出差总天数
	private java.math.BigDecimal transportprice;  //交通费用
	private java.math.BigDecimal citytransport;  //市内交通费
	private java.math.BigDecimal hotelmoney;  //住宿费用
	private java.math.BigDecimal substandardsid;  //出差补贴
	private String remark;  //备用
    
  public String getId() {
   	return this.id;
	}
	public void setId(String value) {
		this.id = value;
	}
  public String getTravelexpenseid() {
   	return this.travelexpenseid;
	}
	public void setTravelexpenseid(String value) {
		this.travelexpenseid = value;
	}
  public java.sql.Date getStartime() {
   	return this.startime;
	}
	public void setStartime(java.sql.Date value) {
		this.startime = value;
	}
  public java.sql.Date getEndtime() {
   	return this.endtime;
	}
	public void setEndtime(java.sql.Date value) {
		this.endtime = value;
	}
  public String getStartplace() {
   	return this.startplace;
	}
	public void setStartplace(String value) {
		this.startplace = value;
	}
  public String getEndplace() {
   	return this.endplace;
	}
	public void setEndplace(String value) {
		this.endplace = value;
	}
  public String getTravel_selfcar() {
   	return this.travel_selfcar;
	}
	public void setTravel_selfcar(String value) {
		this.travel_selfcar = value;
	}
  public String getTotalnum() {
   	return this.totalnum;
	}
	public void setTotalnum(String value) {
		this.totalnum = value;
	}
  public String getPersomnum() {
   	return this.persomnum;
	}
	public void setPersomnum(String value) {
		this.persomnum = value;
	}
  public String getDaytotal() {
   	return this.daytotal;
	}
	public void setDaytotal(String value) {
		this.daytotal = value;
	}
  public java.math.BigDecimal getTransportprice() {
   	return this.transportprice;
	}
	public void setTransportprice(java.math.BigDecimal value) {
		this.transportprice = value;
	}
  public java.math.BigDecimal getCitytransport() {
   	return this.citytransport;
	}
	public void setCitytransport(java.math.BigDecimal value) {
		this.citytransport = value;
	}
  public java.math.BigDecimal getHotelmoney() {
   	return this.hotelmoney;
	}
	public void setHotelmoney(java.math.BigDecimal value) {
		this.hotelmoney = value;
	}
  public java.math.BigDecimal getSubstandardsid() {
   	return this.substandardsid;
	}
	public void setSubstandardsid(java.math.BigDecimal value) {
		this.substandardsid = value;
	}
  public String getRemark() {
   	return this.remark;
	}
	public void setRemark(String value) {
		this.remark = value;
	}

}
