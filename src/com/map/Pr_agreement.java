package com.map;

public class Pr_agreement{    
    
	private java.math.BigDecimal id;  //
	private java.math.BigDecimal item_id;  //项目信息
	private String agree_code;  //合同编码
	private String agree_name;  //合同名称
	private String agree_type;  //合同类型
	private String agree_property;  //合同性质
	private String custom_unitname;  //客户名称（甲方）
	private String custom_name;  //客户签字人
	private java.sql.Date sign_date;  //签字时间
	private java.sql.Date input_date;  //录入时间
	private java.math.BigDecimal input_pid;  //录入人
	private String note;
	
	private String state;
	private String agree_state;
	private java.math.BigDecimal custom_id;  //
	
	
	public java.math.BigDecimal getCustom_id() {
		return custom_id;
	}
	public void setCustom_id(java.math.BigDecimal customId) {
		custom_id = customId;
	}
	
	public String getNote() {
		return note;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAgree_state() {
		return agree_state;
	}
	public void setAgree_state(String agreeState) {
		agree_state = agreeState;
	}
	public void setNote(String note) {
		this.note = note;
	}
	private String contactor;  //联系人
	private String contact_phone;  //联系电话
	private java.math.BigDecimal agree_money;  //合同定价金额
	private java.math.BigDecimal advance_payment;  //预付款
    
  public java.math.BigDecimal getId() {
   	return this.id;
	}
	public void setId(java.math.BigDecimal value) {
		this.id = value;
	}
  public java.math.BigDecimal getItem_id() {
   	return this.item_id;
	}
	public void setItem_id(java.math.BigDecimal value) {
		this.item_id = value;
	}
  public String getAgree_code() {
   	return this.agree_code;
	}
	public void setAgree_code(String value) {
		this.agree_code = value;
	}
  public String getAgree_name() {
   	return this.agree_name;
	}
	public void setAgree_name(String value) {
		this.agree_name = value;
	}
  public String getAgree_type() {
   	return this.agree_type;
	}
	public void setAgree_type(String value) {
		this.agree_type = value;
	}
  public String getAgree_property() {
   	return this.agree_property;
	}
	public void setAgree_property(String value) {
		this.agree_property = value;
	}
  public String getCustom_unitname() {
   	return this.custom_unitname;
	}
	public void setCustom_unitname(String value) {
		this.custom_unitname = value;
	}
  public String getCustom_name() {
   	return this.custom_name;
	}
	public void setCustom_name(String value) {
		this.custom_name = value;
	}
  public java.sql.Date getSign_date() {
   	return this.sign_date;
	}
	public void setSign_date(java.sql.Date value) {
		this.sign_date = value;
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
  
  public String getContactor() {
   	return this.contactor;
	}
	public void setContactor(String value) {
		this.contactor = value;
	}
  public String getContact_phone() {
   	return this.contact_phone;
	}
	public void setContact_phone(String value) {
		this.contact_phone = value;
	}
  public java.math.BigDecimal getAgree_money() {
   	return this.agree_money;
	}
	public void setAgree_money(java.math.BigDecimal value) {
		this.agree_money = value;
	}
  public java.math.BigDecimal getAdvance_payment() {
   	return this.advance_payment;
	}
	public void setAdvance_payment(java.math.BigDecimal value) {
		this.advance_payment = value;
	}

}
