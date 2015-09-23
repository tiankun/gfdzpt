package com.map;

public class Fi_bill_one{    
    
	private java.math.BigDecimal id;  //
	private java.math.BigDecimal bill_id;  //发票id号
	private java.math.BigDecimal money;  //此次收款金额
	private java.sql.Date rdate;  //收款时间
    
  public java.math.BigDecimal getId() {
   	return this.id;
	}
	public void setId(java.math.BigDecimal value) {
		this.id = value;
	}
  public java.math.BigDecimal getBill_id() {
   	return this.bill_id;
	}
	public void setBill_id(java.math.BigDecimal value) {
		this.bill_id = value;
	}
  public java.math.BigDecimal getMoney() {
   	return this.money;
	}
	public void setMoney(java.math.BigDecimal value) {
		this.money = value;
	}
  public java.sql.Date getRdate() {
   	return this.rdate;
	}
	public void setRdate(java.sql.Date value) {
		this.rdate = value;
	}

}
