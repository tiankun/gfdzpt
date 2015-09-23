package com.map;

import java.sql.Clob;

public class Gm_info{    
    
	private java.math.BigDecimal id;  //
	private String title;  //标题
	@com.sysFrams.util.Type(value="Clob")
	private String content;  //内容
	private java.sql.Date input_date;  //录入时间
	private java.math.BigDecimal moduleid;  //所属模块
	private java.math.BigDecimal p_id;  //发布人
    
  public java.math.BigDecimal getId() {
   	return this.id;
	}
	public void setId(java.math.BigDecimal value) {
		this.id = value;
	}
  public String getTitle() {
   	return this.title;
	}
	public void setTitle(String value) {
		this.title = value;
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
  public java.math.BigDecimal getModuleid() {
   	return this.moduleid;
	}
	public void setModuleid(java.math.BigDecimal value) {
		this.moduleid = value;
	}
  public java.math.BigDecimal getP_id() {
   	return this.p_id;
	}
	public void setP_id(java.math.BigDecimal value) {
		this.p_id = value;
	}

}
