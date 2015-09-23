package com.map;

public class Fi_financial_account_detail{    
    
	private java.lang.Long id;  //主键ID
	private java.lang.Long financialaccountid;  //财务报帐主表ID
	private String paytype;  //支付类型
	private java.math.BigDecimal paymoney;  //支付金额
	private java.lang.Integer documentsnum;  //单据数
	private String remark;  //备注
    
  public java.lang.Long getId() {
   	return this.id;
	}
	public void setId(java.lang.Long value) {
		this.id = value;
	}
  public java.lang.Long getFinancialaccountid() {
   	return this.financialaccountid;
	}
	public void setFinancialaccountid(java.lang.Long value) {
		this.financialaccountid = value;
	}
  public String getPaytype() {
   	return this.paytype;
	}
	public void setPaytype(String value) {
		this.paytype = value;
	}
  public java.math.BigDecimal getPaymoney() {
   	return this.paymoney;
	}
	public void setPaymoney(java.math.BigDecimal value) {
		this.paymoney = value;
	}
  public java.lang.Integer getDocumentsnum() {
   	return this.documentsnum;
	}
	public void setDocumentsnum(java.lang.Integer value) {
		this.documentsnum = value;
	}
  public String getRemark() {
   	return this.remark;
	}
	public void setRemark(String value) {
		this.remark = value;
	}

}
