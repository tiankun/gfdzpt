package com.map;

public class Hr_dimission{    
    
	private java.lang.Integer id;  //ID
	private String dim_apply;  //离职申请
	private String dm_opp;  //本部门经理审核意见
	private String dm_affirm;  //部门经理确认
	private String gm_affirm;  //综合部经理确认
	private String fi_affirm;  //财务部经理确认
	private String hr_affirm;  //人事部经理确认
	private String dept_id;  //部门ID
	private String p_id;  //人员ID
	private String gem_opp;  //总经理意见
	private java.sql.Date dim_time;  //申请时间
	private String appstate;  //审核状态
	private String hr_opp;  //人事部审核
    
  public String getDm_affirm() {
		return dm_affirm;
	}
	public void setDm_affirm(String dm_affirm) {
		this.dm_affirm = dm_affirm;
	}
public String getDm_opp() {
		return dm_opp;
	}
	public void setDm_opp(String dm_opp) {
		this.dm_opp = dm_opp;
	}
	public String getHr_opp() {
		return hr_opp;
	}
	public void setHr_opp(String hr_opp) {
		this.hr_opp = hr_opp;
	}
public String getAppstate() {
		return appstate;
	}
	public void setAppstate(String appstate) {
		this.appstate = appstate;
	}
public java.sql.Date getDim_time() {
		return dim_time;
	}
	public void setDim_time(java.sql.Date dim_time) {
		this.dim_time = dim_time;
	}
public java.lang.Integer getId() {
   	return this.id;
	}
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
  public String getDim_apply() {
   	return this.dim_apply;
	}
	public void setDim_apply(String value) {
		this.dim_apply = value;
	}
  public String getGm_affirm() {
   	return this.gm_affirm;
	}
	public void setGm_affirm(String value) {
		this.gm_affirm = value;
	}
  public String getFi_affirm() {
   	return this.fi_affirm;
	}
	public void setFi_affirm(String value) {
		this.fi_affirm = value;
	}
  public String getHr_affirm() {
   	return this.hr_affirm;
	}
	public void setHr_affirm(String value) {
		this.hr_affirm = value;
	}
  public String getDept_id() {
   	return this.dept_id;
	}
	public void setDept_id(String value) {
		this.dept_id = value;
	}
  public String getP_id() {
   	return this.p_id;
	}
	public void setP_id(String value) {
		this.p_id = value;
	}
  public String getGem_opp() {
   	return this.gem_opp;
	}
	public void setGem_opp(String value) {
		this.gem_opp = value;
	}

}
