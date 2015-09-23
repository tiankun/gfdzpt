package com.map;

public class sysfuncdic{    
    
	private java.math.BigDecimal id;  //id
	private String funcname;  //功能名称
	private String description;  //功能描述
	private String delflag;  //删除标志
	private java.math.BigDecimal parentid;  //父功能id
	private java.math.BigDecimal indexid;  //排序号
	private String url;  //路径
	private String publics;  //是否公共
	private String target;  //目标
	private String treenodetype;  //功能节点类型
	private String img_path;  //图片路径
	private String qylc;  //是否启用流程
	private String isauthorize;  //是否需要授权
    
  public java.math.BigDecimal getid() {
   	return this.id;
	}
	public void setid(java.math.BigDecimal value) {
		this.id = value;
	}
  public String getfuncname() {
   	return this.funcname;
	}
	public void setfuncname(String value) {
		this.funcname = value;
	}
  public String getdescription() {
   	return this.description;
	}
	public void setdescription(String value) {
		this.description = value;
	}
  public String getdelflag() {
   	return this.delflag;
	}
	public void setdelflag(String value) {
		this.delflag = value;
	}
  public java.math.BigDecimal getparentid() {
   	return this.parentid;
	}
	public void setparentid(java.math.BigDecimal value) {
		this.parentid = value;
	}
  public java.math.BigDecimal getindexid() {
   	return this.indexid;
	}
	public void setindexid(java.math.BigDecimal value) {
		this.indexid = value;
	}
  public String geturl() {
   	return this.url;
	}
	public void seturl(String value) {
		this.url = value;
	}
  public String getpublics() {
   	return this.publics;
	}
	public void setpublics(String value) {
		this.publics = value;
	}
  public String gettarget() {
   	return this.target;
	}
	public void settarget(String value) {
		this.target = value;
	}
  public String gettreenodetype() {
   	return this.treenodetype;
	}
	public void settreenodetype(String value) {
		this.treenodetype = value;
	}
  public String getimg_path() {
   	return this.img_path;
	}
	public void setimg_path(String value) {
		this.img_path = value;
	}
  public String getqylc() {
   	return this.qylc;
	}
	public void setqylc(String value) {
		this.qylc = value;
	}
  public String getisauthorize() {
   	return this.isauthorize;
	}
	public void setisauthorize(String value) {
		this.isauthorize = value;
	}

}
