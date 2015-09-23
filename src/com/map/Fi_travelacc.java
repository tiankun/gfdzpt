package com.map;

public class Fi_travelacc{    
    
	private java.lang.Long id;  //主键ID
	private java.lang.Long deptid;  //部门ID
	private java.lang.Long travelid;  //出差申请ID
	private java.lang.Long p_id;  //出差人ID
	private java.sql.Date reim_time;  //报销日期
	private java.lang.Long proj_id;  //项目名称
	private java.math.BigDecimal totalcost;  //报销总金额
	private String appstate;  //审批状态
	private String pyt;  //发票标识
	private java.lang.Long nextapproverid;  //审批下一阶段的人员ID
	private String subids;  //随行人员
	private String subflag;  //提交标志
	private String costchn;  //提交标志
	private java.math.BigDecimal print;  //打印标志
	private String fukuan_unit;  //付款单位
	private java.math.BigDecimal fiflag;  //财务审批标志
	private String zf_reason;  //作废原因
	private java.math.BigDecimal zf_person;  //作废人
	private java.sql.Date zf_time;  //作废时间
    
  public String getCostchn() {
		return costchn;
	}
	public void setCostchn(String costchn) {
		this.costchn = costchn;
	}
public String getSubflag() {
		return subflag;
	}
	public void setSubflag(String subflag) {
		this.subflag = subflag;
	}
public String getSubids() {
		return subids;
	}
	public void setSubids(String subids) {
		this.subids = subids;
	}
public java.lang.Long getProj_id() {
		return proj_id;
	}
	public void setProj_id(java.lang.Long proj_id) {
		this.proj_id = proj_id;
	}
public java.lang.Long getId() {
   	return this.id;
	}
	public void setId(java.lang.Long value) {
		this.id = value;
	}
  public java.lang.Long getDeptid() {
   	return this.deptid;
	}
	public void setDeptid(java.lang.Long value) {
		this.deptid = value;
	}
  public java.lang.Long getTravelid() {
   	return this.travelid;
	}
	public void setTravelid(java.lang.Long value) {
		this.travelid = value;
	}
  public java.lang.Long getP_id() {
   	return this.p_id;
	}
	public void setP_id(java.lang.Long value) {
		this.p_id = value;
	}
  public java.sql.Date getReim_time() {
   	return this.reim_time;
	}
	public void setReim_time(java.sql.Date value) {
		this.reim_time = value;
	}
  public java.math.BigDecimal getTotalcost() {
   	return this.totalcost;
	}
	public void setTotalcost(java.math.BigDecimal value) {
		this.totalcost = value;
	}
  public String getAppstate() {
   	return this.appstate;
	}
	public void setAppstate(String value) {
		this.appstate = value;
	}
  public String getPyt() {
   	return this.pyt;
	}
	public void setPyt(String value) {
		this.pyt = value;
	}
  public java.lang.Long getNextapproverid() {
   	return this.nextapproverid;
	}
	public void setNextapproverid(java.lang.Long value) {
		this.nextapproverid = value;
	}
	public java.math.BigDecimal getPrint() {
		return print;
	}
	public void setPrint(java.math.BigDecimal print) {
		this.print = print;
	}
	public String getFukuan_unit() {
		return fukuan_unit;
	}
	public void setFukuan_unit(String fukuan_unit) {
		this.fukuan_unit = fukuan_unit;
	}
	public java.math.BigDecimal getFiflag() {
		return fiflag;
	}
	public void setFiflag(java.math.BigDecimal fiflag) {
		this.fiflag = fiflag;
	}
	public String getZf_reason() {
		return zf_reason;
	}
	public void setZf_reason(String zf_reason) {
		this.zf_reason = zf_reason;
	}
	public java.math.BigDecimal getZf_person() {
		return zf_person;
	}
	public void setZf_person(java.math.BigDecimal zf_person) {
		this.zf_person = zf_person;
	}
	public java.sql.Date getZf_time() {
		return zf_time;
	}
	public void setZf_time(java.sql.Date zf_time) {
		this.zf_time = zf_time;
	}
	
}
