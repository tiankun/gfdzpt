package com.map;

public class Fi_bill_audit{    
    
	private java.math.BigDecimal id;  //
	private java.math.BigDecimal billapply_id;  //发票申请表id（对应于表bill的id）
	private java.math.BigDecimal audit_id;  //审批人id
	private String opinion;  //审批意见
	private String bill_state;  //审批状态
	private java.sql.Date audit_date;  //审核时间
    
  public java.math.BigDecimal getId() {
   	return this.id;
	}
	public void setId(java.math.BigDecimal value) {
		this.id = value;
	}
  public java.math.BigDecimal getBillapply_id() {
   	return this.billapply_id;
	}
	public void setBillapply_id(java.math.BigDecimal value) {
		this.billapply_id = value;
	}
  public java.math.BigDecimal getAudit_id() {
   	return this.audit_id;
	}
	public void setAudit_id(java.math.BigDecimal value) {
		this.audit_id = value;
	}
  public String getOpinion() {
   	return this.opinion;
	}
	public void setOpinion(String value) {
		this.opinion = value;
	}
  public String getBill_state() {
   	return this.bill_state;
	}
	public void setBill_state(String value) {
		this.bill_state = value;
	}
  public java.sql.Date getAudit_date() {
   	return this.audit_date;
	}
	public void setAudit_date(java.sql.Date value) {
		this.audit_date = value;
	}

}
