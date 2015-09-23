package com.map;

public class Hr_pchange{    
    
	private java.lang.Integer id;  //主键
	private java.lang.Integer p_id;  //人员编号
	private String unit_o;  //变动前单位
	private String dept_o;  //变动前部门
	private String position_o;  //变动前岗位
	private String dept_id_o;  //变动前行政级别
	private String unit_n;  //变动后单位
	private String dept_n;  //变动后部门
	private String position_n;  //变动后岗位
	private String dept_id_n;  //变动后行政级别
	private String pchag_type;  //变动类型
	private java.sql.Date pchag_time;  //变动时间
	private String pic;  //相关变动文件扫描件
	private String remark;  //具体内容说明
	private String hr_type_o;  //变动前状态
	private String hr_type_n;  //变动后状态
	private String mark;  //说明(管理员添加/非纳入人员变更)
    
  public java.lang.Integer getId() {
   	return this.id;
	}
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
  public java.lang.Integer getP_id() {
   	return this.p_id;
	}
	public void setP_id(java.lang.Integer value) {
		this.p_id = value;
	}
  public String getUnit_o() {
   	return this.unit_o;
	}
	public void setUnit_o(String value) {
		this.unit_o = value;
	}
  public String getDept_o() {
   	return this.dept_o;
	}
	public void setDept_o(String value) {
		this.dept_o = value;
	}
  public String getPosition_o() {
   	return this.position_o;
	}
	public void setPosition_o(String value) {
		this.position_o = value;
	}
  public String getDept_id_o() {
   	return this.dept_id_o;
	}
	public void setDept_id_o(String value) {
		this.dept_id_o = value;
	}
  public String getUnit_n() {
   	return this.unit_n;
	}
	public void setUnit_n(String value) {
		this.unit_n = value;
	}
  public String getDept_n() {
   	return this.dept_n;
	}
	public void setDept_n(String value) {
		this.dept_n = value;
	}
  public String getPosition_n() {
   	return this.position_n;
	}
	public void setPosition_n(String value) {
		this.position_n = value;
	}
  public String getDept_id_n() {
   	return this.dept_id_n;
	}
	public void setDept_id_n(String value) {
		this.dept_id_n = value;
	}
  public String getPchag_type() {
   	return this.pchag_type;
	}
	public void setPchag_type(String value) {
		this.pchag_type = value;
	}
  public java.sql.Date getPchag_time() {
   	return this.pchag_time;
	}
	public void setPchag_time(java.sql.Date value) {
		this.pchag_time = value;
	}
  public String getPic() {
   	return this.pic;
	}
	public void setPic(String value) {
		this.pic = value;
	}
  public String getRemark() {
   	return this.remark;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
  public String getHr_type_o() {
   	return this.hr_type_o;
	}
	public void setHr_type_o(String value) {
		this.hr_type_o = value;
	}
  public String getHr_type_n() {
   	return this.hr_type_n;
	}
	public void setHr_type_n(String value) {
		this.hr_type_n = value;
	}
  public String getMark() {
   	return this.mark;
	}
	public void setMark(String value) {
		this.mark = value;
	}

}
