package com.map;

public class Fi_travelacc_dtl{    
    
	private java.lang.Long id;  //主键ID
	private java.lang.Integer accessory;  //附件数
	private java.math.BigDecimal trans_cost;  //交通费用
	private java.math.BigDecimal citytrans_cost;  //市内交通费
	private java.math.BigDecimal hotel_cost;  //住宿费用
	private String remark;  //备用
	private java.math.BigDecimal r_fund;  //报销标准
	private java.lang.Long accid;  //出差管理表ID
	private String vehicles;  //交通工具
	private String province;  //省
	private String city;  //州市
	private String district;  //区县
	private java.sql.Date r_time;  //出差开始时间
	private java.sql.Date l_time;  //出差结束时间
	private String stay_type;  //住宿方式
	private java.math.BigDecimal realacc;  //实际报销费用
	private java.math.BigDecimal buzhu;  //补助
    
public java.math.BigDecimal getRealacc() {
		return realacc;
	}
	public void setRealacc(java.math.BigDecimal realacc) {
		this.realacc = realacc;
	}
public String getStay_type() {
		return stay_type;
	}
	public void setStay_type(String stay_type) {
		this.stay_type = stay_type;
	}
public String getVehicles() {
		return vehicles;
	}
	public void setVehicles(String vehicles) {
		this.vehicles = vehicles;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public java.sql.Date getR_time() {
		return r_time;
	}
	public void setR_time(java.sql.Date r_time) {
		this.r_time = r_time;
	}
	public java.sql.Date getL_time() {
		return l_time;
	}
	public void setL_time(java.sql.Date l_time) {
		this.l_time = l_time;
	}
public java.lang.Long getAccid() {
		return accid;
	}
	public void setAccid(java.lang.Long accid) {
		this.accid = accid;
	}
public java.lang.Long getId() {
   	return this.id;
	}
	public void setId(java.lang.Long value) {
		this.id = value;
	}
  public java.lang.Integer getAccessory() {
   	return this.accessory;
	}
	public void setAccessory(java.lang.Integer value) {
		this.accessory = value;
	}
  public java.math.BigDecimal getTrans_cost() {
   	return this.trans_cost;
	}
	public void setTrans_cost(java.math.BigDecimal value) {
		this.trans_cost = value;
	}
	
  public java.math.BigDecimal getCitytrans_cost() {
		return citytrans_cost;
	}
	public void setCitytrans_cost(java.math.BigDecimal citytrans_cost) {
		this.citytrans_cost = citytrans_cost;
	}
public java.math.BigDecimal getHotel_cost() {
   	return this.hotel_cost;
	}
	public void setHotel_cost(java.math.BigDecimal value) {
		this.hotel_cost = value;
	}
  public String getRemark() {
   	return this.remark;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
  public java.math.BigDecimal getR_fund() {
   	return this.r_fund;
	}
	public void setR_fund(java.math.BigDecimal value) {
		this.r_fund = value;
	}
	public java.math.BigDecimal getBuzhu() {
		return buzhu;
	}
	public void setBuzhu(java.math.BigDecimal buzhu) {
		this.buzhu = buzhu;
	}
	
}
