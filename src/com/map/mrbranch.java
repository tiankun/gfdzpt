package com.map;

public class mrbranch{    
    
	private java.math.BigDecimal id;  //
	private String branchname;  //机构名称
	private String simplecode;  //机构缩写
	private java.math.BigDecimal parentid;  //父机构id
	private String branchtype;  //机构类型
	private String delflag;  //删除标志
	private String address;  //地址
	private String dcode;  //机构编码
	public java.math.BigDecimal getId() {
		return id;
	}
	public void setId(java.math.BigDecimal id) {
		this.id = id;
	}
	public String getBranchname() {
		return branchname;
	}
	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	public String getSimplecode() {
		return simplecode;
	}
	public void setSimplecode(String simplecode) {
		this.simplecode = simplecode;
	}
	public java.math.BigDecimal getParentid() {
		return parentid;
	}
	public void setParentid(java.math.BigDecimal parentid) {
		this.parentid = parentid;
	}
	public String getBranchtype() {
		return branchtype;
	}
	public void setBranchtype(String branchtype) {
		this.branchtype = branchtype;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDcode() {
		return dcode;
	}
	public void setDcode(String dcode) {
		this.dcode = dcode;
	}
	@Override
	public String toString() {
		return "mrbranch [id=" + id + ", branchname=" + branchname
				+ ", simplecode=" + simplecode + ", parentid=" + parentid
				+ ", branchtype=" + branchtype + ", delflag=" + delflag
				+ ", address=" + address + ", dcode=" + dcode + "]";
	}
}
