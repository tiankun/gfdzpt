package com.map;

public class Fi_payfor_item{    
    
	private Integer id;  //主键id
	private Integer purchase_item_id;  //对应表gm_purchase_item的id（针对哪个材料付款）
	private Integer materiel_id;  //材料
	private Integer num;  //材料个数
	private java.math.BigDecimal price;  //材料单价
	private java.math.BigDecimal money;  //付款金额
	private Integer person;  //付款申请人
	private java.sql.Date odate;  //付款时间
	private Integer gongys;  //供应商
	private Integer f_id;  //对应于主表id
	private Integer inout_id;  //对应于主表id
	private String applyreason;
	private String fujnum;
	private Integer pro_id;  //项目
  public Integer getId() {
   	return this.id;
	}
	public void setId(Integer value) {
		this.id = value;
	}
  public Integer getPurchase_item_id() {
   	return this.purchase_item_id;
	}
	public void setPurchase_item_id(Integer value) {
		this.purchase_item_id = value;
	}
  public Integer getMateriel_id() {
   	return this.materiel_id;
	}
	public void setMateriel_id(Integer value) {
		this.materiel_id = value;
	}
  public Integer getNum() {
   	return this.num;
	}
	public void setNum(Integer value) {
		this.num = value;
	}
  public java.math.BigDecimal getPrice() {
   	return this.price;
	}
	public void setPrice(java.math.BigDecimal value) {
		this.price = value;
	}
  public java.math.BigDecimal getMoney() {
   	return this.money;
	}
	public void setMoney(java.math.BigDecimal value) {
		this.money = value;
	}
  public Integer getPerson() {
   	return this.person;
	}
	public void setPerson(Integer value) {
		this.person = value;
	}
  public java.sql.Date getOdate() {
   	return this.odate;
	}
	public void setOdate(java.sql.Date value) {
		this.odate = value;
	}
  public Integer getGongys() {
   	return this.gongys;
	}
	public void setGongys(Integer value) {
		this.gongys = value;
	}
  public Integer getF_id() {
   	return this.f_id;
	}
	public void setF_id(Integer value) {
		this.f_id = value;
	}
	public Integer getInout_id() {
		return inout_id;
	}
	public void setInout_id(Integer inoutId) {
		inout_id = inoutId;
	}
	public String getApplyreason() {
		return applyreason;
	}
	public void setApplyreason(String applyreason) {
		this.applyreason = applyreason;
	}
	public String getFujnum() {
		return fujnum;
	}
	public void setFujnum(String fujnum) {
		this.fujnum = fujnum;
	}
	public Integer getPro_id() {
		return pro_id;
	}
	public void setPro_id(Integer pro_id) {
		this.pro_id = pro_id;
	}
	

}
