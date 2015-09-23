package com.map;

public class Gm_ration_item{    
    
	private java.lang.Long id;  //
	private Long apply_id;  //配给申请id
	private java.lang.Integer brand_id;  //品牌
	private java.lang.Integer materiel_id;  //物资名称
	private java.lang.Integer sqsl;  //申请数量
	private java.lang.Integer hasnum;  //审批数量
	private java.sql.Date order_date;  //要求到货日期
	private java.lang.Integer purchase_num;  //已采购数量
	private java.lang.Integer lysl;  //领用数量
	private java.math.BigDecimal mbj;  //目标价格
	private java.math.BigDecimal cgxj;  //采购询价
	private String ds_mf_status;  //变更状态
	private String mf_reason;  //变更原因
	private java.lang.Integer czy;  //操作人
	private java.sql.Date czrq;  //操作日期
	private String note;
	private Long pr_ma_plan_id;
    
  public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
public java.lang.Long getId() {
   	return this.id;
	}
	public void setId(java.lang.Long value) {
		this.id = value;
	}
  public Long getApply_id() {
   	return this.apply_id;
	}
	public void setApply_id(Long value) {
		this.apply_id = value;
	}
  public java.lang.Integer getBrand_id() {
   	return this.brand_id;
	}
	public void setBrand_id(java.lang.Integer value) {
		this.brand_id = value;
	}
  public java.lang.Integer getMateriel_id() {
   	return this.materiel_id;
	}
	public void setMateriel_id(java.lang.Integer value) {
		this.materiel_id = value;
	}
  public java.lang.Integer getSqsl() {
   	return this.sqsl;
	}
	public void setSqsl(java.lang.Integer value) {
		this.sqsl = value;
	}
  public java.lang.Integer getHasnum() {
   	return this.hasnum;
	}
	public void setHasnum(java.lang.Integer value) {
		this.hasnum = value;
	}
  public java.sql.Date getOrder_date() {
   	return this.order_date;
	}
	public void setOrder_date(java.sql.Date value) {
		this.order_date = value;
	}
  public java.lang.Integer getPurchase_num() {
   	return this.purchase_num;
	}
	public void setPurchase_num(java.lang.Integer value) {
		this.purchase_num = value;
	}
  public java.lang.Integer getLysl() {
   	return this.lysl;
	}
	public void setLysl(java.lang.Integer value) {
		this.lysl = value;
	}
  public java.math.BigDecimal getMbj() {
   	return this.mbj;
	}
	public void setMbj(java.math.BigDecimal value) {
		this.mbj = value;
	}
  public java.math.BigDecimal getCgxj() {
   	return this.cgxj;
	}
	public void setCgxj(java.math.BigDecimal value) {
		this.cgxj = value;
	}
  public String getDs_mf_status() {
   	return this.ds_mf_status;
	}
	public void setDs_mf_status(String value) {
		this.ds_mf_status = value;
	}
  public String getMf_reason() {
   	return this.mf_reason;
	}
	public void setMf_reason(String value) {
		this.mf_reason = value;
	}
  public java.lang.Integer getCzy() {
   	return this.czy;
	}
	public void setCzy(java.lang.Integer value) {
		this.czy = value;
	}
  public java.sql.Date getCzrq() {
   	return this.czrq;
	}
	public void setCzrq(java.sql.Date value) {
		this.czrq = value;
	}
	public Long getPr_ma_plan_id() {
		return pr_ma_plan_id;
	}
	public void setPr_ma_plan_id(Long prMaPlanId) {
		pr_ma_plan_id = prMaPlanId;
	}

}
