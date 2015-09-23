package com.map;

public class Fi_expenses_plan{    
    
	private java.lang.Long id;  //主键
	private java.lang.Long personid;  //申请人ID
	private java.lang.Long departid;  //部门ID
	private java.sql.Date apply_time;  //申请时间
	private java.sql.Date advance_time;  //预支时间
	private java.math.BigDecimal total_sum;  //申请金额
	private String upper_chn;  //大写金额
	private String print_status;  //打印状态
	private java.math.BigDecimal final_sum;  //最终审批金额
	private String buy;  //是否要综合办购买
	private String remark;  //备注
	private java.lang.Long pro_id;  //项目ID
	private java.lang.Long nextapproverid;	//下一阶段审批人员ID
	private String subflag;  //提交标志
	private String appstate;  //审核状态
    
  public String getAppstate() {
		return appstate;
	}
	public void setAppstate(String appstate) {
		this.appstate = appstate;
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
  public java.lang.Long getPersonid() {
   	return this.personid;
	}
	public void setPersonid(java.lang.Long value) {
		this.personid = value;
	}
  public java.lang.Long getDepartid() {
   	return this.departid;
	}
	public void setDepartid(java.lang.Long value) {
		this.departid = value;
	}
  public java.sql.Date getApply_time() {
   	return this.apply_time;
	}
	public void setApply_time(java.sql.Date value) {
		this.apply_time = value;
	}
  public java.sql.Date getAdvance_time() {
   	return this.advance_time;
	}
	public void setAdvance_time(java.sql.Date value) {
		this.advance_time = value;
	}
  public java.math.BigDecimal getTotal_sum() {
   	return this.total_sum;
	}
	public void setTotal_sum(java.math.BigDecimal value) {
		this.total_sum = value;
	}
  public String getUpper_chn() {
   	return this.upper_chn;
	}
	public void setUpper_chn(String value) {
		this.upper_chn = value;
	}
  public String getPrint_status() {
   	return this.print_status;
	}
	public void setPrint_status(String value) {
		this.print_status = value;
	}
  public java.math.BigDecimal getFinal_sum() {
   	return this.final_sum;
	}
	public void setFinal_sum(java.math.BigDecimal value) {
		this.final_sum = value;
	}
  public String getBuy() {
   	return this.buy;
	}
	public void setBuy(String value) {
		this.buy = value;
	}
  public String getRemark() {
   	return this.remark;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
  public java.lang.Long getPro_id() {
   	return this.pro_id;
	}
	public void setPro_id(java.lang.Long value) {
		this.pro_id = value;
	}
	public java.lang.Long getNextapproverid() {
		return nextapproverid;
	}
	public void setNextapproverid(java.lang.Long nextapproverid) {
		this.nextapproverid = nextapproverid;
	}

}
