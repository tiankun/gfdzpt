package com.map;

public class Pr_fee_plan{    
    
	private java.lang.Integer id;  //
	private java.lang.Integer plan_id;  //计划id
	private java.lang.Integer prj_id;  //项目
	private java.math.BigDecimal rgf;  //人工费
	private java.math.BigDecimal azf;  //安装费
	private java.math.BigDecimal glf;  //管理费
	private String mf_reason;  //变更原因
	private String ds_mf_status;  //变更状态
	private java.lang.Integer czr;  //操作人
	private java.sql.Date czrq;  //操作日期
    
  public java.lang.Integer getId() {
   	return this.id;
	}
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
  public java.lang.Integer getPlan_id() {
   	return this.plan_id;
	}
	public void setPlan_id(java.lang.Integer value) {
		this.plan_id = value;
	}
  public java.lang.Integer getPrj_id() {
   	return this.prj_id;
	}
	public void setPrj_id(java.lang.Integer value) {
		this.prj_id = value;
	}
  public java.math.BigDecimal getRgf() {
   	return this.rgf;
	}
	public void setRgf(java.math.BigDecimal value) {
		this.rgf = value;
	}
  public java.math.BigDecimal getAzf() {
   	return this.azf;
	}
	public void setAzf(java.math.BigDecimal value) {
		this.azf = value;
	}
  public java.math.BigDecimal getGlf() {
   	return this.glf;
	}
	public void setGlf(java.math.BigDecimal value) {
		this.glf = value;
	}
  public String getMf_reason() {
   	return this.mf_reason;
	}
	public void setMf_reason(String value) {
		this.mf_reason = value;
	}
  public String getDs_mf_status() {
   	return this.ds_mf_status;
	}
	public void setDs_mf_status(String value) {
		this.ds_mf_status = value;
	}
  public java.lang.Integer getCzr() {
   	return this.czr;
	}
	public void setCzr(java.lang.Integer value) {
		this.czr = value;
	}
  public java.sql.Date getCzrq() {
   	return this.czrq;
	}
	public void setCzrq(java.sql.Date value) {
		this.czrq = value;
	}

}
