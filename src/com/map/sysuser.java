package com.map;

public class sysuser{    
    
	private java.lang.Long id;  //主键
	private String log_name;  //登录名称
	private String user_name;  //用户实际名称
	private String log_pass;  //登录密码
	private java.sql.Date cdate;  //创建日期
	private java.sql.Date exp_date;  //有效期
	private java.lang.Long branchid;  //所属机构id
	private java.lang.Integer base_info_id;  //人员id
	private String usertype;  //用户类别
	private String flag;  //是否需要用锁登录
	private String delflag;  //删除标志
	private Integer lockid;
    
  public java.lang.Long getId() {
   	return this.id;
	}
	public void setId(java.lang.Long value) {
		this.id = value;
	}
  public String getLog_name() {
   	return this.log_name;
	}
	public void setLog_name(String value) {
		this.log_name = value;
	}
  public String getUser_name() {
   	return this.user_name;
	}
	public void setUser_name(String value) {
		this.user_name = value;
	}
  public String getLog_pass() {
   	return this.log_pass;
	}
	public void setLog_pass(String value) {
		this.log_pass = value;
	}
  public java.sql.Date getCdate() {
   	return this.cdate;
	}
	public void setCdate(java.sql.Date value) {
		this.cdate = value;
	}
  public java.sql.Date getExp_date() {
   	return this.exp_date;
	}
	public void setExp_date(java.sql.Date value) {
		this.exp_date = value;
	}
  public java.lang.Long getBranchid() {
   	return this.branchid;
	}
	public void setBranchid(java.lang.Long value) {
		this.branchid = value;
	}
  public java.lang.Integer getBase_info_id() {
   	return this.base_info_id;
	}
	public void setBase_info_id(java.lang.Integer value) {
		this.base_info_id = value;
	}
  public String getUsertype() {
   	return this.usertype;
	}
	public void setUsertype(String value) {
		this.usertype = value;
	}
  public String getFlag() {
   	return this.flag;
	}
	public void setFlag(String value) {
		this.flag = value;
	}
  public String getDelflag() {
   	return this.delflag;
	}
	public void setDelflag(String value) {
		this.delflag = value;
	}
	public Integer getLockid() {
		return lockid;
	}
	public void setLockid(Integer lockid) {
		this.lockid = lockid;
	}
	
	

}
