package com.map;

public class Sys_uppic{    
    
	private java.math.BigDecimal id;  //主键id
	private java.math.BigDecimal f_id;  //关联表主键id
	private String flag;  //标记
	private String pic;  //图片名
	private String ext;  //扩展名
	private String name;  //原文件名
    
  public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
public java.math.BigDecimal getId() {
   	return this.id;
	}
	public void setId(java.math.BigDecimal value) {
		this.id = value;
	}
  public java.math.BigDecimal getF_id() {
   	return this.f_id;
	}
	public void setF_id(java.math.BigDecimal value) {
		this.f_id = value;
	}
  public String getFlag() {
   	return this.flag;
	}
	public void setFlag(String value) {
		this.flag = value;
	}
  public String getPic() {
   	return this.pic;
	}
	public void setPic(String value) {
		this.pic = value;
	}

}
