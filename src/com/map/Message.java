package com.map;

public class Message{    
    
	private java.math.BigDecimal id;  //
	private String rolename;  //
	private String name;  //姓名
	private java.math.BigDecimal dept;  //部门id
	private String num;  //手机号码
	private String note;  //备注
	private java.math.BigDecimal pid;  //人员id
    
  public java.math.BigDecimal getPid() {
		return pid;
	}
	public void setPid(java.math.BigDecimal pid) {
		this.pid = pid;
	}
public java.math.BigDecimal getId() {
   	return this.id;
	}
	public void setId(java.math.BigDecimal value) {
		this.id = value;
	}
  public String getRolename() {
   	return this.rolename;
	}
	public void setRolename(String value) {
		this.rolename = value;
	}
  public String getName() {
   	return this.name;
	}
	public void setName(String value) {
		this.name = value;
	}
  public java.math.BigDecimal getDept() {
   	return this.dept;
	}
	public void setDept(java.math.BigDecimal value) {
		this.dept = value;
	}
  public String getNum() {
   	return this.num;
	}
	public void setNum(String value) {
		this.num = value;
	}
  public String getNote() {
   	return this.note;
	}
	public void setNote(String value) {
		this.note = value;
	}

}
