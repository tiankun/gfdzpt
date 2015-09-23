package com.map;

public class Gm_materiel_inout{    
    
	private java.math.BigDecimal id;  //主键
	private java.math.BigDecimal purchase_item_id;  //入库材料所对应的配给执行单id（即表gm_purchase_item的id）
	private java.math.BigDecimal materiel_id;  //材料
	private java.math.BigDecimal brand_id;  //品牌
	private java.math.BigDecimal num;  //数量
	private String type;  //操作类型
	private java.sql.Date odate;  //操作日期
	private java.math.BigDecimal operson;  //操作人
	private java.lang.Integer prj_id;  //项目
	private String note;
	private java.math.BigDecimal price;
	private java.math.BigDecimal money;
	private String ispay;
	private String dh;
    private Integer lyr;
  public java.math.BigDecimal getId() {
   	return this.id;
	}
	public void setId(java.math.BigDecimal value) {
		this.id = value;
	}
  public java.math.BigDecimal getPurchase_item_id() {
   	return this.purchase_item_id;
	}
	public void setPurchase_item_id(java.math.BigDecimal value) {
		this.purchase_item_id = value;
	}
  public java.math.BigDecimal getMateriel_id() {
   	return this.materiel_id;
	}
	public void setMateriel_id(java.math.BigDecimal value) {
		this.materiel_id = value;
	}
  public java.math.BigDecimal getBrand_id() {
   	return this.brand_id;
	}
	public void setBrand_id(java.math.BigDecimal value) {
		this.brand_id = value;
	}
  public java.math.BigDecimal getNum() {
   	return this.num;
	}
	public void setNum(java.math.BigDecimal value) {
		this.num = value;
	}
  public String getType() {
   	return this.type;
	}
	public void setType(String value) {
		this.type = value;
	}
  public java.sql.Date getOdate() {
   	return this.odate;
	}
	public void setOdate(java.sql.Date value) {
		this.odate = value;
	}
  public java.math.BigDecimal getOperson() {
   	return this.operson;
	}
	public void setOperson(java.math.BigDecimal value) {
		this.operson = value;
	}
	public java.lang.Integer getPrj_id() {
		return prj_id;
	}
	public void setPrj_id(java.lang.Integer prjId) {
		prj_id = prjId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public java.math.BigDecimal getPrice() {
		return price;
	}
	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}
	public java.math.BigDecimal getMoney() {
		return money;
	}
	public void setMoney(java.math.BigDecimal money) {
		this.money = money;
	}
	public String getIspay() {
		return ispay;
	}
	public void setIspay(String ispay) {
		this.ispay = ispay;
	}
	public String getDh() {
		return dh;
	}
	public void setDh(String dh) {
		this.dh = dh;
	}
	public Integer getLyr() {
		return lyr;
	}
	public void setLyr(Integer lyr) {
		this.lyr = lyr;
	}
	
	
	

}
