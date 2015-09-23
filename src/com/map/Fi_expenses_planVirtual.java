package com.map;

import java.util.List;

public class Fi_expenses_planVirtual {
	private static final long serialVersionUID = -6694636760798380623L;
	private java.lang.Long id;  //主键
	private java.lang.Long p_id;  //申请人ID
	private java.lang.Long dept_id;  //部门ID
	private java.sql.Date apply_time;  //申请时间
	private java.sql.Date advance_time;  //预支时间
	private java.math.BigDecimal total_sum;  //申请金额
	private String upper_chn;  //大写金额
	private String print_status;  //打印状态
	private String exp_status;  //审批状态
	private String latest_opinion;  //审批意见:当前申请最后审批意见
	private java.math.BigDecimal final_sum;  //最终审批金额
	private String buy;  //是否要综合办购买
	private String remark;  //备注
	private java.lang.Long pro_id;  //项目ID
	private List<String> item;
	private List<String> plan_money;
	public java.lang.Long getId() {
		return id;
	}
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	public java.lang.Long getP_id() {
		return p_id;
	}
	public void setP_id(java.lang.Long p_id) {
		this.p_id = p_id;
	}
	public java.lang.Long getDept_id() {
		return dept_id;
	}
	public void setDept_id(java.lang.Long dept_id) {
		this.dept_id = dept_id;
	}
	public java.sql.Date getApply_time() {
		return apply_time;
	}
	public void setApply_time(java.sql.Date apply_time) {
		this.apply_time = apply_time;
	}
	public java.sql.Date getAdvance_time() {
		return advance_time;
	}
	public void setAdvance_time(java.sql.Date advance_time) {
		this.advance_time = advance_time;
	}
	public java.math.BigDecimal getTotal_sum() {
		return total_sum;
	}
	public void setTotal_sum(java.math.BigDecimal total_sum) {
		this.total_sum = total_sum;
	}
	public String getUpper_chn() {
		return upper_chn;
	}
	public void setUpper_chn(String upper_chn) {
		this.upper_chn = upper_chn;
	}
	public String getPrint_status() {
		return print_status;
	}
	public void setPrint_status(String print_status) {
		this.print_status = print_status;
	}
	public String getExp_status() {
		return exp_status;
	}
	public void setExp_status(String exp_status) {
		this.exp_status = exp_status;
	}
	public String getLatest_opinion() {
		return latest_opinion;
	}
	public void setLatest_opinion(String latest_opinion) {
		this.latest_opinion = latest_opinion;
	}
	public java.math.BigDecimal getFinal_sum() {
		return final_sum;
	}
	public void setFinal_sum(java.math.BigDecimal final_sum) {
		this.final_sum = final_sum;
	}
	public String getBuy() {
		return buy;
	}
	public void setBuy(String buy) {
		this.buy = buy;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public java.lang.Long getPro_id() {
		return pro_id;
	}
	public void setPro_id(java.lang.Long pro_id) {
		this.pro_id = pro_id;
	}
	public List<String> getItem() {
		return item;
	}
	public void setItem(List<String> item) {
		this.item = item;
	}
	public List<String> getPlan_money() {
		return plan_money;
	}
	public void setPlan_money(List<String> plan_money) {
		this.plan_money = plan_money;
	}
	
}
