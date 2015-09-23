package com.map;

public class Fi_fina_account_appr{    
    
	private java.lang.Long id;  //主键ID
	private java.lang.Long accid;  //财务报帐主表ID
	private java.lang.Long deptid;  //部门ID
	private java.lang.Long auditid;  //审批人ID
	private java.lang.Integer appstatus;  //审批状态
	private String appopinion;  //审核意见
	private java.sql.Date apptime;  //审核时间
	private String remark;
	public java.lang.Long getId() {
		return id;
	}
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	public java.lang.Long getAccid() {
		return accid;
	}
	public void setAccid(java.lang.Long accid) {
		this.accid = accid;
	}
	public java.lang.Long getDeptid() {
		return deptid;
	}
	public void setDeptid(java.lang.Long deptid) {
		this.deptid = deptid;
	}
	public java.lang.Long getAuditid() {
		return auditid;
	}
	public void setAuditid(java.lang.Long auditid) {
		this.auditid = auditid;
	}
	public java.lang.Integer getAppstatus() {
		return appstatus;
	}
	public void setAppstatus(java.lang.Integer appstatus) {
		this.appstatus = appstatus;
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
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}  //备注
}
