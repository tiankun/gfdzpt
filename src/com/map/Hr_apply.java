package com.map;

public class Hr_apply{    
    
	private String id;  //主键
	private String p_id;  //人员ID
	private String dept_id;  //部门ID
	private java.sql.Date apply_time;  //申请提交时间
	private String text;  //内容
	private String adjunct;  //附件
	private java.sql.Date ycsyq;  //延长试用期
	private String ycopp;  //延长试用意见
	private String appstate;  //审核状态
    
  public String getAppstate() {
		return appstate;
	}
	public void setAppstate(String appstate) {
		this.appstate = appstate;
	}
public String getYcopp() {
		return ycopp;
	}
	public void setYcopp(String ycopp) {
		this.ycopp = ycopp;
	}
public java.sql.Date getYcsyq() {
		return ycsyq;
	}
	public void setYcsyq(java.sql.Date ycsyq) {
		this.ycsyq = ycsyq;
	}
public String getId() {
   	return this.id;
	}
	public void setId(String value) {
		this.id = value;
	}
  public String getP_id() {
   	return this.p_id;
	}
	public void setP_id(String value) {
		this.p_id = value;
	}
  public String getDept_id() {
   	return this.dept_id;
	}
	public void setDept_id(String value) {
		this.dept_id = value;
	}
  public java.sql.Date getApply_time() {
   	return this.apply_time;
	}
	public void setApply_time(java.sql.Date value) {
		this.apply_time = value;
	}
  public String getText() {
   	return this.text;
	}
	public void setText(String value) {
		this.text = value;
	}
  public String getAdjunct() {
   	return this.adjunct;
	}
	public void setAdjunct(String value) {
		this.adjunct = value;
	}

}
