package com.map;

public class Hr_achieve_cwb{    
    
	private Long id;  //主键id
	private Long p_id;  //考核人员id(对应于base_info的id列)
	private java.sql.Date input_date;  //录入时间
	private String achieve_date;  //考核时间
	private String item1;  //工作业绩
	private String item2;  //工作态度及责任感
	private String item3;  //沟通及协作
	private String item4;  //纪律性
	private String item5;  //加分项
	private String item6;  //减分项
	private String note1;  //总体评价及改进建议
	private String note2;  //备注
	private Long f_id;  //与表HR_ACHIEVE关联（对应于其id列）
    
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
  public java.sql.Date getInput_date() {
   	return this.input_date;
	}
	public void setInput_date(java.sql.Date value) {
		this.input_date = value;
	}
  public String getAchieve_date() {
   	return this.achieve_date;
	}
	public void setAchieve_date(String value) {
		this.achieve_date = value;
	}
  public String getItem1() {
   	return this.item1;
	}
	public void setItem1(String value) {
		this.item1 = value;
	}
  public String getItem2() {
   	return this.item2;
	}
	public void setItem2(String value) {
		this.item2 = value;
	}
  public String getItem3() {
   	return this.item3;
	}
	public void setItem3(String value) {
		this.item3 = value;
	}
  public String getItem4() {
   	return this.item4;
	}
	public void setItem4(String value) {
		this.item4 = value;
	}
  public String getItem5() {
   	return this.item5;
	}
	public void setItem5(String value) {
		this.item5 = value;
	}
  public String getItem6() {
   	return this.item6;
	}
	public void setItem6(String value) {
		this.item6 = value;
	}
  public String getNote1() {
   	return this.note1;
	}
	public void setNote1(String value) {
		this.note1 = value;
	}
  public String getNote2() {
   	return this.note2;
	}
	public void setNote2(String value) {
		this.note2 = value;
	}
  public Long getF_id() {
   	return this.f_id;
	}
	public void setF_id(Long value) {
		this.f_id = value;
	}

}
