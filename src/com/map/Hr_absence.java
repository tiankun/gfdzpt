package com.map;

public class Hr_absence{    
    
	private Long id;  //主键
	private Long p_id;  //人员id
	private Long dept_id;  //部门
	private String absence_type;  //请假类型
	private java.sql.Date begin_date;  //请假开始时间
	private String bam;  //开始请假上午区间
	private String bpm;  //开始请假下午
	private java.sql.Date end_date;  //结束时间
	private String eam;  //结束请假上午区间
	private String epm;  //结束请假下午区间
	private String reason;  //请假原因
	private java.sql.Date input_date;  //申请时间
	private String absence_state;  //审核状态(最近)
	private String days;  //请假天数
	private String opinion;  //审核意见(最近)
    
  public Long getId() {
   	return this.id;
	}
	public void setId(Long value) {
		this.id = value;
	}
  public Long getP_id() {
   	return this.p_id;
	}
	public void setP_id(Long value) {
		this.p_id = value;
	}
  public Long getDept_id() {
   	return this.dept_id;
	}
	public void setDept_id(Long value) {
		this.dept_id = value;
	}
  public String getAbsence_type() {
   	return this.absence_type;
	}
	public void setAbsence_type(String value) {
		this.absence_type = value;
	}
  public java.sql.Date getBegin_date() {
   	return this.begin_date;
	}
	public void setBegin_date(java.sql.Date value) {
		this.begin_date = value;
	}
  public String getBam() {
   	return this.bam;
	}
	public void setBam(String value) {
		this.bam = value;
	}
  public String getBpm() {
   	return this.bpm;
	}
	public void setBpm(String value) {
		this.bpm = value;
	}
  public java.sql.Date getEnd_date() {
   	return this.end_date;
	}
	public void setEnd_date(java.sql.Date value) {
		this.end_date = value;
	}
  public String getEam() {
   	return this.eam;
	}
	public void setEam(String value) {
		this.eam = value;
	}
  public String getEpm() {
   	return this.epm;
	}
	public void setEpm(String value) {
		this.epm = value;
	}
  public String getReason() {
   	return this.reason;
	}
	public void setReason(String value) {
		this.reason = value;
	}
  public java.sql.Date getInput_date() {
   	return this.input_date;
	}
	public void setInput_date(java.sql.Date value) {
		this.input_date = value;
	}
  public String getAbsence_state() {
   	return this.absence_state;
	}
	public void setAbsence_state(String value) {
		this.absence_state = value;
	}
  public String getDays() {
   	return this.days;
	}
	public void setDays(String value) {
		this.days = value;
	}
  public String getOpinion() {
   	return this.opinion;
	}
	public void setOpinion(String value) {
		this.opinion = value;
	}

}
