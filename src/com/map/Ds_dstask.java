package com.map;

public class Ds_dstask{    
    
	private java.lang.Integer proj_id;  //项目id(pr_project.id)
	private String ds_type;  //设计类型
	private String name;  //任务名称
	private java.lang.Integer launch_deptid;  //发起部门(mrbranch.id)
	private java.lang.Integer launch_personid;  //发起人(hr_base_info.id)
	private String content;  //任务内容
	private java.sql.Date launch_time;  //发起日期
	private java.sql.Date delivery_time;  //交付日期
	private String appstate;  //审批状态
	private String appopinion;  //审批意见
	private java.lang.Integer approver_id;  //审批人id(hr_base_info.id)
	private java.sql.Date apptime;  //审批日期
	private java.lang.Integer id;  //
	private String dstask_code;		//任务书编码
	private String flag;		//完成标志
	private String dstask_num;		//任务书编码
	private String task_state;		//任务状态
	private java.lang.Integer design_fzr;  //设计负责人
	private java.lang.Integer task_fzr;  //设计负责人
    
  public java.lang.Integer getTask_fzr() {
		return task_fzr;
	}
	public void setTask_fzr(java.lang.Integer task_fzr) {
		this.task_fzr = task_fzr;
	}
public String getTask_state() {
		return task_state;
	}
	public void setTask_state(String task_state) {
		this.task_state = task_state;
	}
	public java.lang.Integer getDesign_fzr() {
		return design_fzr;
	}
	public void setDesign_fzr(java.lang.Integer design_fzr) {
		this.design_fzr = design_fzr;
	}
public String getDstask_num() {
		return dstask_num;
	}
	public void setDstask_num(String dstask_num) {
		this.dstask_num = dstask_num;
	}
public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
public String getDstask_code() {
		return dstask_code;
	}
	public void setDstask_code(String dstask_code) {
		this.dstask_code = dstask_code;
	}
public java.lang.Integer getProj_id() {
   	return this.proj_id;
	}
	public void setProj_id(java.lang.Integer value) {
		this.proj_id = value;
	}
  public String getDs_type() {
   	return this.ds_type;
	}
	public void setDs_type(String value) {
		this.ds_type = value;
	}
  public String getName() {
   	return this.name;
	}
	public void setName(String value) {
		this.name = value;
	}
  public java.lang.Integer getLaunch_deptid() {
   	return this.launch_deptid;
	}
	public void setLaunch_deptid(java.lang.Integer value) {
		this.launch_deptid = value;
	}
  public java.lang.Integer getLaunch_personid() {
   	return this.launch_personid;
	}
	public void setLaunch_personid(java.lang.Integer value) {
		this.launch_personid = value;
	}
  public String getContent() {
   	return this.content;
	}
	public void setContent(String value) {
		this.content = value;
	}
  public java.sql.Date getLaunch_time() {
   	return this.launch_time;
	}
	public void setLaunch_time(java.sql.Date value) {
		this.launch_time = value;
	}
  public java.sql.Date getDelivery_time() {
   	return this.delivery_time;
	}
	public void setDelivery_time(java.sql.Date value) {
		this.delivery_time = value;
	}
  public String getAppstate() {
   	return this.appstate;
	}
	public void setAppstate(String value) {
		this.appstate = value;
	}
  public String getAppopinion() {
   	return this.appopinion;
	}
	public void setAppopinion(String value) {
		this.appopinion = value;
	}
  public java.lang.Integer getApprover_id() {
   	return this.approver_id;
	}
	public void setApprover_id(java.lang.Integer value) {
		this.approver_id = value;
	}
  public java.sql.Date getApptime() {
   	return this.apptime;
	}
	public void setApptime(java.sql.Date value) {
		this.apptime = value;
	}
  public java.lang.Integer getId() {
   	return this.id;
	}
	public void setId(java.lang.Integer value) {
		this.id = value;
	}

}
