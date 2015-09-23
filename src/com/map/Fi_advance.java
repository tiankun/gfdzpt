package com.map;

public class Fi_advance{    
    
	private java.lang.Long id;  //主键
	private java.lang.Long pid;  //申请人ID
	private java.lang.Long deptid;  //部门ID
	private java.lang.Long payeeid;  //收款单位ID
	private String payeename;  //收款单位
	private java.sql.Date apply_time;  //申请时间
	private java.math.BigDecimal apply_money;  //申请金额
	private String chn;  //大写金额
	private String apply_reason;  //申请原因
	private String advance_type;  //借支类型
	private String print_status;  //打印状态
	private java.math.BigDecimal back_money;  //归还金额
	private java.lang.Long pyt;  //发票类型
	private String appstate;  //审批状态
	private String remark;  //备注
	private java.lang.Long nextapproverid;  //审批下一阶段的人员ID
	private java.sql.Date back_time;  //归还时间
	private String subflag;  //备注
	private java.lang.Long travelid;  //出差表ID
	private java.math.BigDecimal print;  //打印标志
	private String fukuan_unit;  //付款单位
	private java.math.BigDecimal fiflag;  //财务审批标志
	private String zf_reason;  //作废原因
	private java.math.BigDecimal zf_person;  //作废人
	private java.sql.Date zf_time;  //作废时间
    
  public java.lang.Long getTravelid() {
		return travelid;
	}
	public void setTravelid(java.lang.Long travelid) {
		this.travelid = travelid;
	}
public java.lang.Long getPayeeid() {
		return payeeid;
	}
	public void setPayeeid(java.lang.Long payeeid) {
		this.payeeid = payeeid;
	}
	public String getPayeename() {
		return payeename;
	}
	public void setPayeename(String payeename) {
		this.payeename = payeename;
	}
public String getSubflag() {
		return subflag;
	}
	public void setSubflag(String subflag) {
		this.subflag = subflag;
	}
public java.lang.Long getId() {
   	return this.id;
	}
	public void setId(java.lang.Long value) {
		this.id = value;
	}
  public java.lang.Long getPid() {
   	return this.pid;
	}
	public void setPid(java.lang.Long value) {
		this.pid = value;
	}
  public java.lang.Long getDeptid() {
   	return this.deptid;
	}
	public void setDeptid(java.lang.Long value) {
		this.deptid = value;
	}
	
public java.sql.Date getApply_time() {
   	return this.apply_time;
	}
	public void setApply_time(java.sql.Date value) {
		this.apply_time = value;
	}
  public java.math.BigDecimal getApply_money() {
   	return this.apply_money;
	}
	public void setApply_money(java.math.BigDecimal value) {
		this.apply_money = value;
	}
  public String getChn() {
   	return this.chn;
	}
	public void setChn(String value) {
		this.chn = value;
	}
  public String getApply_reason() {
   	return this.apply_reason;
	}
	public void setApply_reason(String value) {
		this.apply_reason = value;
	}
  public String getAdvance_type() {
   	return this.advance_type;
	}
	public void setAdvance_type(String value) {
		this.advance_type = value;
	}
  public String getPrint_status() {
   	return this.print_status;
	}
	public void setPrint_status(String value) {
		this.print_status = value;
	}
  public java.math.BigDecimal getBack_money() {
   	return this.back_money;
	}
	public void setBack_money(java.math.BigDecimal value) {
		this.back_money = value;
	}
  public java.lang.Long getPyt() {
   	return this.pyt;
	}
	public void setPyt(java.lang.Long value) {
		this.pyt = value;
	}
  public String getAppstate() {
   	return this.appstate;
	}
	public void setAppstate(String value) {
		this.appstate = value;
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
  public java.sql.Date getBack_time() {
   	return this.back_time;
	}
	public void setBack_time(java.sql.Date value) {
		this.back_time = value;
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
