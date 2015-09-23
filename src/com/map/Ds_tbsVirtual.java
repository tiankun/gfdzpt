package com.map;

import java.util.List;

public class Ds_tbsVirtual{    
    
	private java.lang.Integer id;  //
	private java.lang.Integer scheme_id;  //策划书
	private List<String> task;  //任务
	private List<String> designer_id;  //设计负责人
	private List<String> ph_deptid;  //配合部门
	private List<String> finish_time;  //完成日期
    
  public java.lang.Integer getId() {
   	return this.id;
	}
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
  public java.lang.Integer getScheme_id() {
   	return this.scheme_id;
	}
	public void setScheme_id(java.lang.Integer value) {
		this.scheme_id = value;
	}
	public List<String> getTask() {
		return task;
	}
	public void setTask(List<String> task) {
		this.task = task;
	}
	public List<String> getDesigner_id() {
		return designer_id;
	}
	public void setDesigner_id(List<String> designer_id) {
		this.designer_id = designer_id;
	}
	public List<String> getPh_deptid() {
		return ph_deptid;
	}
	public void setPh_deptid(List<String> ph_deptid) {
		this.ph_deptid = ph_deptid;
	}
	public List<String> getFinish_time() {
		return finish_time;
	}
	public void setFinish_time(List<String> finish_time) {
		this.finish_time = finish_time;
	}

}
