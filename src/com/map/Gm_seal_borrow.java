package com.map;

public class Gm_seal_borrow{    
    
	private java.math.BigDecimal id;  //主键id
	private java.math.BigDecimal p_id;  //借阅人
	private java.sql.Date bdate;  //申请时间
	private java.sql.Date pdate;  //计划使用日期
	private java.sql.Date rdate;  //归还日期
	private String bitem;  //借用项目
	private String usereason;  //借用用途
	private String isoriginal;  //是否是原件（0--否，1--是）
	private String isprint;  //是否是复印及加盖公章
	private String stamp;  //盖章
	private java.math.BigDecimal num;  //份数
	private String note;  //备注
	private String state;
	private Integer dept_id;
    
  public java.math.BigDecimal getId() {
   	return this.id;
	}
	public void setId(java.math.BigDecimal value) {
		this.id = value;
	}
  public java.math.BigDecimal getP_id() {
   	return this.p_id;
	}
	public void setP_id(java.math.BigDecimal value) {
		this.p_id = value;
	}
  public java.sql.Date getBdate() {
   	return this.bdate;
	}
	public void setBdate(java.sql.Date value) {
		this.bdate = value;
	}
  public java.sql.Date getPdate() {
   	return this.pdate;
	}
	public void setPdate(java.sql.Date value) {
		this.pdate = value;
	}
  public java.sql.Date getRdate() {
   	return this.rdate;
	}
	public void setRdate(java.sql.Date value) {
		this.rdate = value;
	}
  public String getBitem() {
   	return this.bitem;
	}
	public void setBitem(String value) {
		this.bitem = value;
	}
  public String getUsereason() {
   	return this.usereason;
	}
	public void setUsereason(String value) {
		this.usereason = value;
	}
  public String getIsoriginal() {
   	return this.isoriginal;
	}
	public void setIsoriginal(String value) {
		this.isoriginal = value;
	}
  public String getIsprint() {
   	return this.isprint;
	}
	public void setIsprint(String value) {
		this.isprint = value;
	}
  public String getStamp() {
   	return this.stamp;
	}
	public void setStamp(String value) {
		this.stamp = value;
	}
  public java.math.BigDecimal getNum() {
   	return this.num;
	}
	public void setNum(java.math.BigDecimal value) {
		this.num = value;
	}
  public String getNote() {
   	return this.note;
	}
	public void setNote(String value) {
		this.note = value;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getDept_id() {
		return dept_id;
	}
	public void setDept_id(Integer deptId) {
		dept_id = deptId;
	}
	
	
	
	

}
