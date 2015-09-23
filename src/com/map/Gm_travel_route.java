package com.map;

public class Gm_travel_route{    
    
	private java.lang.Long id;  //主键
	private java.lang.Long travelid;  //出差管理表ID
	private String vehicles;  //交通工具
	private String province;  //省
	private String city;  //州市
	private String district;  //区县
	private java.sql.Date r_time;  //出差开始时间
	private java.sql.Date l_time;  //出差结束时间
	private java.math.BigDecimal r_fund;	//可报销金额
	
	
	public java.math.BigDecimal getR_fund() {
		return r_fund;
	}
	public void setR_fund(java.math.BigDecimal r_fund) {
		this.r_fund = r_fund;
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
	public java.lang.Long getId() {
		return id;
	}
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	public java.lang.Long getTravelid() {
		return travelid;
	}
	public void setTravelid(java.lang.Long travelid) {
		this.travelid = travelid;
	}
	public String getVehicles() {
		return vehicles;
	}
	public void setVehicles(String vehicles) {
		this.vehicles = vehicles;
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
    

}
