package com.map;

public class Gm_workreport{    
    
	private java.math.BigDecimal id;  //
	private java.math.BigDecimal p_id;  //汇报人
	@com.sys.util.Type(value ="CLOB")
	private String content;  //汇报内容
	private java.sql.Date input_date;  //上报日期
	private java.sql.Date need_date;  //应报日期
	private String isread;  //领导是否查看（1-已看；0-未看）
	private String opinion;  //领导审批意见
    
  public java.math.BigDecimal getId() {
   	return this.id;
	}
	public void setId(java.math.BigDecimal value) {
		this.id = value;
	}
  public java.math.BigDecimal getP_id() {
   	return this.p_id;
	}
	public void setP_id(java.math.BigDecimal value) {
		this.p_id = value;
	}
  public String getContent() {
   	return this.content;
	}
	public void setContent(String value) {
		this.content = value;
	}
  public java.sql.Date getInput_date() {
   	return this.input_date;
	}
	public void setInput_date(java.sql.Date value) {
		this.input_date = value;
	}
  public java.sql.Date getNeed_date() {
   	return this.need_date;
	}
	public void setNeed_date(java.sql.Date value) {
		this.need_date = value;
	}
  public String getIsread() {
   	return this.isread;
	}
	public void setIsread(String value) {
		this.isread = value;
	}
  public String getOpinion() {
   	return this.opinion;
	}
	public void setOpinion(String value) {
		this.opinion = value;
	}

}
