package com.map;

public class Pr_project{    
    
	private java.math.BigDecimal id;  //
	private String name;  //项目名称
	private String pr_owner;  //项目业主
	private java.sql.Date start_date;  //开始日期
	private java.sql.Date end_date;  //结束日期
	private java.math.BigDecimal pr_manage;  //项目经理
	private String pr_member;  //项目成员
	private String pr_step;  //项目阶段
	private String pr_note;  //项目备注
	private java.sql.Date input_date;  //项目录入时间
	private java.math.BigDecimal input_pid;  //录入人员
	private String state;
	private Long sale_manage;
	private String sale_members;
    
  public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
public java.math.BigDecimal getId() {
   	return this.id;
	}
	public void setId(java.math.BigDecimal value) {
		this.id = value;
	}
  public String getName() {
   	return this.name;
	}
	public void setName(String value) {
		this.name = value;
	}
  public String getPr_owner() {
   	return this.pr_owner;
	}
	public void setPr_owner(String value) {
		this.pr_owner = value;
	}
  public java.sql.Date getStart_date() {
   	return this.start_date;
	}
	public void setStart_date(java.sql.Date value) {
		this.start_date = value;
	}
  public java.sql.Date getEnd_date() {
   	return this.end_date;
	}
	public void setEnd_date(java.sql.Date value) {
		this.end_date = value;
	}
  public java.math.BigDecimal getPr_manage() {
   	return this.pr_manage;
	}
	public void setPr_manage(java.math.BigDecimal value) {
		this.pr_manage = value;
	}
  public String getPr_member() {
   	return this.pr_member;
	}
	public void setPr_member(String value) {
		this.pr_member = value;
	}
  public String getPr_step() {
   	return this.pr_step;
	}
	public void setPr_step(String value) {
		this.pr_step = value;
	}
  public String getPr_note() {
   	return this.pr_note;
	}
	public void setPr_note(String value) {
		this.pr_note = value;
	}
  public java.sql.Date getInput_date() {
   	return this.input_date;
	}
	public void setInput_date(java.sql.Date value) {
		this.input_date = value;
	}
  public java.math.BigDecimal getInput_pid() {
   	return this.input_pid;
	}
	public void setInput_pid(java.math.BigDecimal value) {
		this.input_pid = value;
	}
	public Long getSale_manage() {
		return sale_manage;
	}
	public void setSale_manage(Long saleManage) {
		sale_manage = saleManage;
	}
	public String getSale_members() {
		return sale_members;
	}
	public void setSale_members(String saleMembers) {
		sale_members = saleMembers;
	}

}
