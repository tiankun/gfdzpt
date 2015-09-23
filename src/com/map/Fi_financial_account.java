package com.map;

public class Fi_financial_account{    
    
	private java.lang.Long id;  //主键
	private java.lang.Long personid;  //申请人ID
	private java.lang.Long departid;  //部门ID
	private java.lang.Long projectid;  //项目名称
	private java.sql.Date apply_date;  //申请时间
	private java.math.BigDecimal apply_money;  //申请金额
	private String amount_in_words;  //大写金额
	private String print_status;  //打印状态
	private String advance_type;  //支付方式
	private String appstate;  //当前审批状态
	private String remark;  //备注
	private java.lang.Long nextapproverid;  //审批下一阶段的人员ID
	private String fjpath;  //附件路径
	private java.lang.Long skdw;  //收款单位
	private String subflag;  //提交标志
	private java.math.BigDecimal print;  //打印标志
	private String fukuan_unit;  //付款单位
	private java.math.BigDecimal fiflag;  //财务审批标志
	private String zf_reason;  //作废原因
	private java.math.BigDecimal zf_person;  //作废人
	private java.sql.Date zf_time;  //作废时间
    
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
  public java.lang.Long getProjectid() {
   	return this.projectid;
	}
	public void setProjectid(java.lang.Long value) {
		this.projectid = value;
	}
  public java.sql.Date getApply_date() {
   	return this.apply_date;
	}
	public void setApply_date(java.sql.Date value) {
		this.apply_date = value;
	}
  public java.math.BigDecimal getApply_money() {
   	return this.apply_money;
	}
	public void setApply_money(java.math.BigDecimal value) {
		this.apply_money = value;
	}
  public String getAmount_in_words() {
   	return this.amount_in_words;
	}
	public void setAmount_in_words(String value) {
		this.amount_in_words = value;
	}
  public String getPrint_status() {
   	return this.print_status;
	}
	public void setPrint_status(String value) {
		this.print_status = value;
	}
  public String getAdvance_type() {
   	return this.advance_type;
	}
	public void setAdvance_type(String value) {
		this.advance_type = value;
	}
	
  public String getAppstate() {
		return appstate;
	}
	public void setAppstate(String appstate) {
		this.appstate = appstate;
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
  public String getFjpath() {
   	return this.fjpath;
	}
	public void setFjpath(String value) {
		this.fjpath = value;
	}
	public java.lang.Long getSkdw() {
		return skdw;
	}
	public void setSkdw(java.lang.Long skdw) {
		this.skdw = skdw;
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
