package com.map;

public class Customer{    
    
	private java.math.BigDecimal id;  //
	private String name;  //客户名称
	private String address;  //地址
	private String lxr;  //联系人
	private String lxdh;  //联系电话
	private String khdj;  //客户等级
	private String nsrsbh;  //纳税人识别号
	private String khh;  //开户行
	private String zh;  //账号
	private String note;  //备注
	private String yxbz;
	private java.math.BigDecimal input_pid;
	private java.sql.Date input_date;
    
  public java.math.BigDecimal getInput_pid() {
		return input_pid;
	}
	public void setInput_pid(java.math.BigDecimal inputPid) {
		input_pid = inputPid;
	}
	public java.sql.Date getInput_date() {
		return input_date;
	}
	public void setInput_date(java.sql.Date inputDate) {
		input_date = inputDate;
	}
public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String flag) {
		this.yxbz = flag;
	}
public java.math.BigDecimal getId() {
   	return this.id;
	}
	public void setId(java.math.BigDecimal value) {
		this.id = value;
	}
  public String getName() {
   	return this.name;
	}
	public void setName(String value) {
		this.name = value;
	}
  public String getAddress() {
   	return this.address;
	}
	public void setAddress(String value) {
		this.address = value;
	}
  public String getLxr() {
   	return this.lxr;
	}
	public void setLxr(String value) {
		this.lxr = value;
	}
  public String getLxdh() {
   	return this.lxdh;
	}
	public void setLxdh(String value) {
		this.lxdh = value;
	}
  public String getKhdj() {
   	return this.khdj;
	}
	public void setKhdj(String value) {
		this.khdj = value;
	}
  public String getNsrsbh() {
   	return this.nsrsbh;
	}
	public void setNsrsbh(String value) {
		this.nsrsbh = value;
	}
  public String getKhh() {
   	return this.khh;
	}
	public void setKhh(String value) {
		this.khh = value;
	}
  public String getZh() {
   	return this.zh;
	}
	public void setZh(String value) {
		this.zh = value;
	}
  public String getNote() {
   	return this.note;
	}
	public void setNote(String value) {
		this.note = value;
	}

}
