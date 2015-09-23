package com.map;

public class Fi_payfor{    
    
	private java.math.BigDecimal id;  //
	private java.math.BigDecimal p_id;  //申请人
	private java.sql.Date odate;  //申请时间
	private java.math.BigDecimal money;  //总金额
	private String payforstate;  //审批阶段
	private java.math.BigDecimal dept_id;  //部门
	private String note;  //备注
	private String bmoney;
	private String paytype;
	private String reason;
	private String kjtype;
	private String zfreason;
	private String pjdtype;
	private String type;
	private java.math.BigDecimal print;
	private java.math.BigDecimal sk_id;//收款单位
	private Integer pro_id;//项目
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
  public java.sql.Date getOdate() {
   	return this.odate;
	}
	public void setOdate(java.sql.Date value) {
		this.odate = value;
	}
  public java.math.BigDecimal getMoney() {
   	return this.money;
	}
	public void setMoney(java.math.BigDecimal value) {
		this.money = value;
	}
  public String getPayforstate() {
   	return this.payforstate;
	}
	public void setPayforstate(String value) {
		this.payforstate = value;
	}
  public java.math.BigDecimal getDept_id() {
   	return this.dept_id;
	}
	public void setDept_id(java.math.BigDecimal value) {
		this.dept_id = value;
	}
  public String getNote() {
   	return this.note;
	}
	public void setNote(String value) {
		this.note = value;
	}
	public String getBmoney() {
		return bmoney;
	}
	public void setBmoney(String bmoney) {
		this.bmoney = bmoney;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getKjtype() {
		return kjtype;
	}
	public void setKjtype(String kjtype) {
		this.kjtype = kjtype;
	}
	public String getZfreason() {
		return zfreason;
	}
	public void setZfreason(String zfreason) {
		this.zfreason = zfreason;
	}
	public String getPjdtype() {
		return pjdtype;
	}
	public void setPjdtype(String pjdtype) {
		this.pjdtype = pjdtype;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public java.math.BigDecimal getPrint() {
		return print;
	}
	public void setPrint(java.math.BigDecimal print) {
		this.print = print;
	}
	public java.math.BigDecimal getSk_id() {
		return sk_id;
	}
	public void setSk_id(java.math.BigDecimal sk_id) {
		this.sk_id = sk_id;
	}
	public Integer getPro_id() {
		return pro_id;
	}
	public void setPro_id(Integer pro_id) {
		this.pro_id = pro_id;
	}
	
	
	

}
