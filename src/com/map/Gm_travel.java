package com.map;

public class Gm_travel{    
    
	private java.lang.Long id;  //主键id
	private String deptid;  //部门ID
	private java.lang.Long personid;  //出差人ID
	private java.sql.Date b_time;  //出发时间
	private java.sql.Date e_time;  //返回时间
	private java.math.BigDecimal traveldays;  //出差天数
	private String subflag;  //提交标志
	private String accflag;  //报账标志
	private String reason;  //出差事由
	private java.math.BigDecimal fund;  //出差经费预算
	private java.sql.Date applicate_time;  //申请时间
	private String appstate;  //审批状态
	private String subids;  //随行人员Json
	private String remark;  //备注
	private java.lang.Long nextapproverid;  //审批下一阶段的人员ID
    
  public java.lang.Long getId() {
   	return this.id;
	}
	public void setId(java.lang.Long value) {
		this.id = value;
	}
  public String getDeptid() {
   	return this.deptid;
	}
	public void setDeptid(String value) {
		this.deptid = value;
	}
  public java.lang.Long getPersonid() {
   	return this.personid;
	}
	public void setPersonid(java.lang.Long value) {
		this.personid = value;
	}
  public java.sql.Date getB_time() {
   	return this.b_time;
	}
	public void setB_time(java.sql.Date value) {
		this.b_time = value;
	}
  public java.sql.Date getE_time() {
   	return this.e_time;
	}
	public void setE_time(java.sql.Date value) {
		this.e_time = value;
	}
  public java.math.BigDecimal getTraveldays() {
   	return this.traveldays;
	}
	public void setTraveldays(java.math.BigDecimal value) {
		this.traveldays = value;
	}
  public String getSubflag() {
   	return this.subflag;
	}
	public void setSubflag(String value) {
		this.subflag = value;
	}
  public String getAccflag() {
   	return this.accflag;
	}
	public void setAccflag(String value) {
		this.accflag = value;
	}
  public String getReason() {
   	return this.reason;
	}
	public void setReason(String value) {
		this.reason = value;
	}
  public java.math.BigDecimal getFund() {
   	return this.fund;
	}
	public void setFund(java.math.BigDecimal value) {
		this.fund = value;
	}
  public java.sql.Date getApplicate_time() {
   	return this.applicate_time;
	}
	public void setApplicate_time(java.sql.Date value) {
		this.applicate_time = value;
	}
  public String getAppstate() {
   	return this.appstate;
	}
	public void setAppstate(String value) {
		this.appstate = value;
	}
  public String getSubids() {
   	return this.subids;
	}
	public void setSubids(String value) {
		this.subids = value;
	}
  public String getRemark() {
   	return this.remark;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
  public java.lang.Long getNextapproverid() {
   	return this.nextapproverid;
	}
	public void setNextapproverid(java.lang.Long value) {
		this.nextapproverid = value;
	}

}
