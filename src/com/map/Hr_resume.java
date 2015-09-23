package com.map;

public class Hr_resume{    
    
	private java.lang.Integer id;  //主键
	private java.lang.Integer p_id;  //人员编号
	private String dan_wei;  //单位
	private String bu_men;  //部门
	private String zhi_wu;  //职务
	private java.sql.Date start_time;  //开始时间
	private java.sql.Date end_time;  //结束时间
	private String zheng_ming;  //证明人
	private String zhengm_dianhua;  //证明人电话
	private String li_zhi;  //离职原因
	private String remark;  //备注
    
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
  public String getDan_wei() {
   	return this.dan_wei;
	}
	public void setDan_wei(String value) {
		this.dan_wei = value;
	}
  public String getBu_men() {
   	return this.bu_men;
	}
	public void setBu_men(String value) {
		this.bu_men = value;
	}
  public String getZhi_wu() {
   	return this.zhi_wu;
	}
	public void setZhi_wu(String value) {
		this.zhi_wu = value;
	}
  public java.sql.Date getStart_time() {
   	return this.start_time;
	}
	public void setStart_time(java.sql.Date value) {
		this.start_time = value;
	}
  public java.sql.Date getEnd_time() {
   	return this.end_time;
	}
	public void setEnd_time(java.sql.Date value) {
		this.end_time = value;
	}
  public String getZheng_ming() {
   	return this.zheng_ming;
	}
	public void setZheng_ming(String value) {
		this.zheng_ming = value;
	}
  public String getZhengm_dianhua() {
   	return this.zhengm_dianhua;
	}
	public void setZhengm_dianhua(String value) {
		this.zhengm_dianhua = value;
	}
  public String getLi_zhi() {
   	return this.li_zhi;
	}
	public void setLi_zhi(String value) {
		this.li_zhi = value;
	}
  public String getRemark() {
   	return this.remark;
	}
	public void setRemark(String value) {
		this.remark = value;
	}

}
