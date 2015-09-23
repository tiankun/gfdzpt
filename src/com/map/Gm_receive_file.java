package com.map;

public class Gm_receive_file{    
    
	private java.math.BigDecimal id;  //
	private String filename;  //文件名称
	private String filenum;  //文件编号
	private String num;  //收/发件份数
	private java.math.BigDecimal p_id;  //收发件人
	private java.sql.Date rdate;  //收发日期
	private String note;  //备注
	private String type;
	private String state;
	private java.math.BigDecimal lp_id;  
	private java.sql.Date ldate;  
    
  public java.math.BigDecimal getId() {
   	return this.id;
	}
	public void setId(java.math.BigDecimal value) {
		this.id = value;
	}
  public String getFilename() {
   	return this.filename;
	}
	public void setFilename(String value) {
		this.filename = value;
	}
  public String getFilenum() {
   	return this.filenum;
	}
	public void setFilenum(String value) {
		this.filenum = value;
	}
  public String getNum() {
   	return this.num;
	}
	public void setNum(String value) {
		this.num = value;
	}
  public java.math.BigDecimal getP_id() {
   	return this.p_id;
	}
	public void setP_id(java.math.BigDecimal value) {
		this.p_id = value;
	}
  public java.sql.Date getRdate() {
   	return this.rdate;
	}
	public void setRdate(java.sql.Date value) {
		this.rdate = value;
	}
  public String getNote() {
   	return this.note;
	}
	public void setNote(String value) {
		this.note = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public java.math.BigDecimal getLp_id() {
		return lp_id;
	}
	public void setLp_id(java.math.BigDecimal lpId) {
		lp_id = lpId;
	}
	public java.sql.Date getLdate() {
		return ldate;
	}
	public void setLdate(java.sql.Date ldate) {
		this.ldate = ldate;
	}
	

}
