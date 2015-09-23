package com.map;

public class Hr_achieve_gcb{    
    
	private java.math.BigDecimal id;  //
	private java.math.BigDecimal p_id;  //考核人员id(对应于base_info的id列)
	private java.sql.Date input_date;  //录入时间
	private String achieve_date;  //考核时间
	private String item1;  //工作数量

	private String item2;  //工作质量

	private String item3;  //工作效率

	private String note1;  //情况说明
	private String note2;  //备注
	private java.math.BigDecimal f_id;  //与表HR_ACHIEVE关联（对应于其id列）
	private String item4;  //服务质量

    
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
  public java.math.BigDecimal getF_id() {
   	return this.f_id;
	}
	public void setF_id(java.math.BigDecimal value) {
		this.f_id = value;
	}
  public String getItem4() {
   	return this.item4;
	}
	public void setItem4(String value) {
		this.item4 = value;
	}

}
