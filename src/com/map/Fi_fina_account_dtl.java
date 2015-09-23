package com.map;

public class Fi_fina_account_dtl{    
    
	private java.lang.Long id;  //ID
	private java.lang.Long accid;  //财务报帐主表ID
	private String paytype;  //摘要
	private java.math.BigDecimal paymoney;  //支付金额
	private java.lang.Integer documentsnum;  //单据数
	private String remark;  //备注
	private String fplx;  //发票类型
	private String other;
    
  public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
public java.lang.Long getAccid() {
		return accid;
	}
	public void setAccid(java.lang.Long accid) {
		this.accid = accid;
	}
public java.lang.Long getId() {
   	return this.id;
	}
	public void setId(java.lang.Long value) {
		this.id = value;
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
  public String getFplx() {
   	return this.fplx;
	}
	public void setFplx(String value) {
		this.fplx = value;
	}

}
