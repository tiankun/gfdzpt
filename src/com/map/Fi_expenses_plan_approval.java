package com.map;

public class Fi_expenses_plan_approval{    
    
	private java.lang.Long id;  //主键ID
	private java.lang.Long dept_id;  //部门ID
	private java.lang.Long exp_id;  //费用开支计划ID
	private java.lang.Long auditid;  //审批人ID
	private String appopinion;  //审核意见
	private java.sql.Date apptime;  //审核时间
	private String remark;  //备注
    
  public java.lang.Long getId() {
   	return this.id;
	}
	public void setId(java.lang.Long value) {
		this.id = value;
	}
  public java.lang.Long getDept_id() {
   	return this.dept_id;
	}
	public void setDept_id(java.lang.Long value) {
		this.dept_id = value;
	}
  public java.lang.Long getExp_id() {
   	return this.exp_id;
	}
	public void setExp_id(java.lang.Long value) {
		this.exp_id = value;
	}
	
  public java.lang.Long getAuditid() {
		return auditid;
	}
	public void setAuditid(java.lang.Long auditid) {
		this.auditid = auditid;
	}
	public String getAppopinion() {
		return appopinion;
	}
	public void setAppopinion(String appopinion) {
		this.appopinion = appopinion;
	}
	public java.sql.Date getApptime() {
		return apptime;
	}
	public void setApptime(java.sql.Date apptime) {
		this.apptime = apptime;
	}
public String getRemark() {
   	return this.remark;
	}
	public void setRemark(String value) {
		this.remark = value;
	}

}
