package com.map;

public class sysrolesdic{    
    
	private java.math.BigDecimal id;  //
	private String rolename;  //岗位名称
	private String description;  //岗位描述
	private String roletype;  //岗位类型
	private String delflag;  //删除标志
    
  public java.math.BigDecimal getid() {
   	return this.id;
	}
	public void setid(java.math.BigDecimal value) {
		this.id = value;
	}
  public String getrolename() {
   	return this.rolename;
	}
	public void setrolename(String value) {
		this.rolename = value;
	}
  public String getdescription() {
   	return this.description;
	}
	public void setdescription(String value) {
		this.description = value;
	}
  public String getroletype() {
   	return this.roletype;
	}
	public void setroletype(String value) {
		this.roletype = value;
	}
  public String getdelflag() {
   	return this.delflag;
	}
	public void setdelflag(String value) {
		this.delflag = value;
	}

}
