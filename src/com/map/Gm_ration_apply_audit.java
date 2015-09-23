package com.map;

public class Gm_ration_apply_audit{    
    
	private java.math.BigDecimal id;  //主键id
	private java.math.BigDecimal purchaseapply_id;  //请假单id
	private java.math.BigDecimal audit_id;  //审核人id
	private String opinion;  //审核意见
	private String state;  //状态
	private java.sql.Timestamp audit_date;  //审核时间
    
  public java.math.BigDecimal getId() {
   	return this.id;
	}
	public void setId(java.math.BigDecimal value) {
		this.id = value;
	}
  public java.math.BigDecimal getPurchaseapply_id() {
   	return this.purchaseapply_id;
	}
	public void setPurchaseapply_id(java.math.BigDecimal value) {
		this.purchaseapply_id = value;
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
  public String getState() {
   	return this.state;
	}
	public void setState(String value) {
		this.state = value;
	}
  public java.sql.Timestamp getAudit_date() {
   	return this.audit_date;
	}
	public void setAudit_date(java.sql.Timestamp value) {
		this.audit_date = value;
	}

}
