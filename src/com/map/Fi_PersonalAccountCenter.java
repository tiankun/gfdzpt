package com.map;

public class Fi_PersonalAccountCenter{    
    
	private String id;  //主键ID
	private String departid;  //部门ID
	private String personid;  //当事人ID
	private String operationid;  //操作目标表ID
	private String operationtype;  //操作类型
	private java.math.BigDecimal occurmoney;  //发生金额
	private String isexpend;  //是否支出
	private String isapproval;  //是否审批
	private String projectid;  //项目ID
	private String remark;  //备注
    
  public String getId() {
   	return this.id;
	}
	public void setId(String value) {
		this.id = value;
	}
  public String getDepartid() {
   	return this.departid;
	}
	public void setDepartid(String value) {
		this.departid = value;
	}
  public String getPersonid() {
   	return this.personid;
	}
	public void setPersonid(String value) {
		this.personid = value;
	}
  public String getOperationid() {
   	return this.operationid;
	}
	public void setOperationid(String value) {
		this.operationid = value;
	}
  public String getOperationtype() {
   	return this.operationtype;
	}
	public void setOperationtype(String value) {
		this.operationtype = value;
	}
  public java.math.BigDecimal getOccurmoney() {
   	return this.occurmoney;
	}
	public void setOccurmoney(java.math.BigDecimal value) {
		this.occurmoney = value;
	}
  public String getIsexpend() {
   	return this.isexpend;
	}
	public void setIsexpend(String value) {
		this.isexpend = value;
	}
  public String getIsapproval() {
   	return this.isapproval;
	}
	public void setIsapproval(String value) {
		this.isapproval = value;
	}
  public String getProjectid() {
   	return this.projectid;
	}
	public void setProjectid(String value) {
		this.projectid = value;
	}
  public String getRemark() {
   	return this.remark;
	}
	public void setRemark(String value) {
		this.remark = value;
	}

}
