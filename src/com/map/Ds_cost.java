package com.map;

public class Ds_cost{    
    
	private java.lang.Integer id;  //
	private java.lang.Integer result_id;  //成果清单
	private java.lang.Integer proj_id;  //项目
	private String ds_type;  //设计类型
	private java.math.BigDecimal labor_cost;  //人工费
	private java.math.BigDecimal install_cost;  //安装费
	private java.math.BigDecimal manage_cost;  //管理费
	private java.math.BigDecimal aim_lc;  //目标人工费
	private java.math.BigDecimal aim_ic;  //目标安装费
	private java.math.BigDecimal aim_mc;  //目标管理费
	private String mf_reason;  //变更原因
	private String ds_mf_status;  //变更状态
	private java.lang.Integer operator;  //操作人
	private java.sql.Date op_time;  //操作日期
    
  public java.lang.Integer getId() {
   	return this.id;
	}
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
  public java.lang.Integer getResult_id() {
   	return this.result_id;
	}
	public void setResult_id(java.lang.Integer value) {
		this.result_id = value;
	}
  public java.lang.Integer getProj_id() {
   	return this.proj_id;
	}
	public void setProj_id(java.lang.Integer value) {
		this.proj_id = value;
	}
  public String getDs_type() {
   	return this.ds_type;
	}
	public void setDs_type(String value) {
		this.ds_type = value;
	}
  public java.math.BigDecimal getLabor_cost() {
   	return this.labor_cost;
	}
	public void setLabor_cost(java.math.BigDecimal value) {
		this.labor_cost = value;
	}
  public java.math.BigDecimal getInstall_cost() {
   	return this.install_cost;
	}
	public void setInstall_cost(java.math.BigDecimal value) {
		this.install_cost = value;
	}
  public java.math.BigDecimal getManage_cost() {
   	return this.manage_cost;
	}
	public void setManage_cost(java.math.BigDecimal value) {
		this.manage_cost = value;
	}
  public java.math.BigDecimal getAim_lc() {
   	return this.aim_lc;
	}
	public void setAim_lc(java.math.BigDecimal value) {
		this.aim_lc = value;
	}
  public java.math.BigDecimal getAim_ic() {
   	return this.aim_ic;
	}
	public void setAim_ic(java.math.BigDecimal value) {
		this.aim_ic = value;
	}
  public java.math.BigDecimal getAim_mc() {
   	return this.aim_mc;
	}
	public void setAim_mc(java.math.BigDecimal value) {
		this.aim_mc = value;
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
  public java.lang.Integer getOperator() {
   	return this.operator;
	}
	public void setOperator(java.lang.Integer value) {
		this.operator = value;
	}
  public java.sql.Date getOp_time() {
   	return this.op_time;
	}
	public void setOp_time(java.sql.Date value) {
		this.op_time = value;
	}

}
