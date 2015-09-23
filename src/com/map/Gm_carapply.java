package com.map;

public class Gm_carapply{    
    
	private java.math.BigDecimal id;  //主键id
	private java.math.BigDecimal p_id;  //申请人id
	private java.math.BigDecimal dept_id;  //用车申请人部门
	private String plan_sdate;  //计划用车起始时间
	private String plan_edate;  //计划用车结束时间
	private String reason;  //用车原因
	private String road;  //行车路线
	private java.math.BigDecimal driver;  //驾驶员
	private String car_num;  //车牌号
	private String act_sdate;  //实际用车起始时间
	private String act_edate;  //实际用车结束时间
	private String length;  //行驶里程
	private java.sql.Timestamp input_date;  //申请时间
	private String carapply_state;  //当前审核状态
	private String opinion;  //当前审核意见
	private String usetype;
    
  public java.math.BigDecimal getId() {
   	return this.id;
	}
	public void setId(java.math.BigDecimal value) {
		this.id = value;
	}
  public java.math.BigDecimal getP_id() {
   	return this.p_id;
	}
	public void setP_id(java.math.BigDecimal value) {
		this.p_id = value;
	}
  public java.math.BigDecimal getDept_id() {
   	return this.dept_id;
	}
	public void setDept_id(java.math.BigDecimal value) {
		this.dept_id = value;
	}
  public String getPlan_sdate() {
   	return this.plan_sdate;
	}
	public void setPlan_sdate(String value) {
		this.plan_sdate = value;
	}
  public String getPlan_edate() {
   	return this.plan_edate;
	}
	public void setPlan_edate(String value) {
		this.plan_edate = value;
	}
  public String getReason() {
   	return this.reason;
	}
	public void setReason(String value) {
		this.reason = value;
	}
  public String getRoad() {
   	return this.road;
	}
	public void setRoad(String value) {
		this.road = value;
	}
  public java.math.BigDecimal getDriver() {
   	return this.driver;
	}
	public void setDriver(java.math.BigDecimal value) {
		this.driver = value;
	}
  public String getCar_num() {
   	return this.car_num;
	}
	public void setCar_num(String value) {
		this.car_num = value;
	}
  public String getAct_sdate() {
   	return this.act_sdate;
	}
	public void setAct_sdate(String value) {
		this.act_sdate = value;
	}
  public String getAct_edate() {
   	return this.act_edate;
	}
	public void setAct_edate(String value) {
		this.act_edate = value;
	}
  public String getLength() {
   	return this.length;
	}
	public void setLength(String value) {
		this.length = value;
	}
  public java.sql.Timestamp getInput_date() {
   	return this.input_date;
	}
	public void setInput_date(java.sql.Timestamp value) {
		this.input_date = value;
	}
  public String getCarapply_statetate() {
   	return this.carapply_state;
	}
	public void setCarapply_state(String value) {
		this.carapply_state = value;
	}
  public String getOpinion() {
   	return this.opinion;
	}
	public void setOpinion(String value) {
		this.opinion = value;
	}
	
	public String getUsetype() {
		return usetype;
	}
	public void setUsetype(String usetype) {
		this.usetype = usetype;
	}

}
