package com.map;

public class code{

	private java.math.BigDecimal id; // id
	private String dmlb; // 代码类别
	private String dmlbmc; // 代码类别名称
	private String dmz; // 代码值
	private String dmzmc; // 代码值名称
	private String kwhbz; // 可维护标志
	private String yxbz; // 有效标志
	private java.math.BigDecimal plsx; // 排列顺序
	
	public java.math.BigDecimal getId() {
		return id;
	}
	public void setId(java.math.BigDecimal id) {
		this.id = id;
	}
	public String getDmlb() {
		return dmlb;
	}
	public void setDmlb(String dmlb) {
		this.dmlb = dmlb;
	}
	public String getDmlbmc() {
		return dmlbmc;
	}
	public void setDmlbmc(String dmlbmc) {
		this.dmlbmc = dmlbmc;
	}
	public String getDmz() {
		return dmz;
	}
	public void setDmz(String dmz) {
		this.dmz = dmz;
	}
	public String getDmzmc() {
		return dmzmc;
	}
	public void setDmzmc(String dmzmc) {
		this.dmzmc = dmzmc;
	}
	public String getKwhbz() {
		return kwhbz;
	}
	public void setKwhbz(String kwhbz) {
		this.kwhbz = kwhbz;
	}
	public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
	public java.math.BigDecimal getPlsx() {
		return plsx;
	}
	public void setPlsx(java.math.BigDecimal plsx) {
		this.plsx = plsx;
	}
	@Override
	public String toString() {
		return "code [id=" + id + ", dmlb=" + dmlb + ", dmlbmc=" + dmlbmc
				+ ", dmz=" + dmz + ", dmzmc=" + dmzmc + ", kwhbz=" + kwhbz
				+ ", yxbz=" + yxbz + ", plsx=" + plsx + "]";
	}

}
