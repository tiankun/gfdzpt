package com.map;

public class Fi_advance_back{    
    
	private java.lang.Long id;  //主键
	private java.lang.Long advid;  //主键
	private java.math.BigDecimal backed_money;  //归还金额
	private java.sql.Date back_date;  //归还时间
	private String remark;  //备注
    
  public java.lang.Long getAdvid() {
		return advid;
	}
	public void setAdvid(java.lang.Long advid) {
		this.advid = advid;
	}
public java.lang.Long getId() {
   	return this.id;
	}
	public void setId(java.lang.Long value) {
		this.id = value;
	}
  public java.math.BigDecimal getBacked_money() {
   	return this.backed_money;
	}
	public void setBacked_money(java.math.BigDecimal value) {
		this.backed_money = value;
	}
  public java.sql.Date getBack_date() {
   	return this.back_date;
	}
	public void setBack_date(java.sql.Date value) {
		this.back_date = value;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
