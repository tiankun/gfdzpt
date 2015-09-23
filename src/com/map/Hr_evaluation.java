package com.map;

public class Hr_evaluation{    
    
	private java.math.BigDecimal id;  //主键
	private java.math.BigDecimal p_id;  //被考评人ID
	private java.math.BigDecimal ep_id;  //考评人ID
	private String dept_id;  //部门ID
	private java.sql.Date ev_time;  //考评时间
	private java.math.BigDecimal p_quality;  //工作质量得分
	private String e_quality;  //工作质量说明
	private java.math.BigDecimal p_quantity;  //工作数量得分
	private String e_quantity;  //工作数量说明
	private java.math.BigDecimal p_discipline;  //纪律性得分
	private String e_discipline;  //纪律性说明
	private java.math.BigDecimal p_collective;  //集体观念得分
	private String e_collective;  //集体观念说明
	private java.math.BigDecimal p_positivity;  //积极性得分
	private String e_positivity;  //积极性说明
	private java.math.BigDecimal p_responsibility;  //责任感得分
	private String e_responsibility;  //责任感说明
	private java.math.BigDecimal p_knowledge;  //知识得分
	private String e_knowledge;  //知识说明
	private java.math.BigDecimal p_judge;  //判断得分
	private String e_judge;  //判断说明
	private java.math.BigDecimal p_plan;  //计划得分
	private String e_plan;  //计划说明
	private java.math.BigDecimal p_relationships;  //人际得分
	private String e_relationships;  //人际说明
	private java.math.BigDecimal p_performance;  //成绩得分
	private String r_performance;  //成绩比例
	private java.math.BigDecimal s_performance;  //成绩评分
	private java.math.BigDecimal p_attitude;  //态度得分
	private String r_attitude;  //态度比例
	private java.math.BigDecimal s_attitude;  //态度评分
	private java.math.BigDecimal p_capability;  //能力得分
	private String r_capability;  //能力比例
	private java.math.BigDecimal s_capability;  //能力评分
	private java.math.BigDecimal s_personal;  //个人评分
	private java.math.BigDecimal s_total;  //综合评分
    
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
  public java.math.BigDecimal getEp_id() {
   	return this.ep_id;
	}
	public void setEp_id(java.math.BigDecimal value) {
		this.ep_id = value;
	}
  public String getDept_id() {
   	return this.dept_id;
	}
	public void setDept_id(String value) {
		this.dept_id = value;
	}
  public java.sql.Date getEv_time() {
   	return this.ev_time;
	}
	public void setEv_time(java.sql.Date value) {
		this.ev_time = value;
	}
  public java.math.BigDecimal getP_quality() {
   	return this.p_quality;
	}
	public void setP_quality(java.math.BigDecimal value) {
		this.p_quality = value;
	}
  public String getE_quality() {
   	return this.e_quality;
	}
	public void setE_quality(String value) {
		this.e_quality = value;
	}
  public java.math.BigDecimal getP_quantity() {
   	return this.p_quantity;
	}
	public void setP_quantity(java.math.BigDecimal value) {
		this.p_quantity = value;
	}
  public String getE_quantity() {
   	return this.e_quantity;
	}
	public void setE_quantity(String value) {
		this.e_quantity = value;
	}
  public java.math.BigDecimal getP_discipline() {
   	return this.p_discipline;
	}
	public void setP_discipline(java.math.BigDecimal value) {
		this.p_discipline = value;
	}
  public String getE_discipline() {
   	return this.e_discipline;
	}
	public void setE_discipline(String value) {
		this.e_discipline = value;
	}
  public java.math.BigDecimal getP_collective() {
   	return this.p_collective;
	}
	public void setP_collective(java.math.BigDecimal value) {
		this.p_collective = value;
	}
  public String getE_collective() {
   	return this.e_collective;
	}
	public void setE_collective(String value) {
		this.e_collective = value;
	}
  public java.math.BigDecimal getP_positivity() {
   	return this.p_positivity;
	}
	public void setP_positivity(java.math.BigDecimal value) {
		this.p_positivity = value;
	}
  public String getE_positivity() {
   	return this.e_positivity;
	}
	public void setE_positivity(String value) {
		this.e_positivity = value;
	}
  public java.math.BigDecimal getP_responsibility() {
   	return this.p_responsibility;
	}
	public void setP_responsibility(java.math.BigDecimal value) {
		this.p_responsibility = value;
	}
  public String getE_responsibility() {
   	return this.e_responsibility;
	}
	public void setE_responsibility(String value) {
		this.e_responsibility = value;
	}
  public java.math.BigDecimal getP_knowledge() {
   	return this.p_knowledge;
	}
	public void setP_knowledge(java.math.BigDecimal value) {
		this.p_knowledge = value;
	}
  public String getE_knowledge() {
   	return this.e_knowledge;
	}
	public void setE_knowledge(String value) {
		this.e_knowledge = value;
	}
  public java.math.BigDecimal getP_judge() {
   	return this.p_judge;
	}
	public void setP_judge(java.math.BigDecimal value) {
		this.p_judge = value;
	}
  public String getE_judge() {
   	return this.e_judge;
	}
	public void setE_judge(String value) {
		this.e_judge = value;
	}
  public java.math.BigDecimal getP_plan() {
   	return this.p_plan;
	}
	public void setP_plan(java.math.BigDecimal value) {
		this.p_plan = value;
	}
  public String getE_plan() {
   	return this.e_plan;
	}
	public void setE_plan(String value) {
		this.e_plan = value;
	}
  public java.math.BigDecimal getP_relationships() {
   	return this.p_relationships;
	}
	public void setP_relationships(java.math.BigDecimal value) {
		this.p_relationships = value;
	}
  public String getE_relationships() {
   	return this.e_relationships;
	}
	public void setE_relationships(String value) {
		this.e_relationships = value;
	}
  public java.math.BigDecimal getP_performance() {
   	return this.p_performance;
	}
	public void setP_performance(java.math.BigDecimal value) {
		this.p_performance = value;
	}
  public String getR_performance() {
   	return this.r_performance;
	}
	public void setR_performance(String value) {
		this.r_performance = value;
	}
  public java.math.BigDecimal getS_performance() {
   	return this.s_performance;
	}
	public void setS_performance(java.math.BigDecimal value) {
		this.s_performance = value;
	}
  public java.math.BigDecimal getP_attitude() {
   	return this.p_attitude;
	}
	public void setP_attitude(java.math.BigDecimal value) {
		this.p_attitude = value;
	}
  public String getR_attitude() {
   	return this.r_attitude;
	}
	public void setR_attitude(String value) {
		this.r_attitude = value;
	}
  public java.math.BigDecimal getS_attitude() {
   	return this.s_attitude;
	}
	public void setS_attitude(java.math.BigDecimal value) {
		this.s_attitude = value;
	}
  public java.math.BigDecimal getP_capability() {
   	return this.p_capability;
	}
	public void setP_capability(java.math.BigDecimal value) {
		this.p_capability = value;
	}
  public String getR_capability() {
   	return this.r_capability;
	}
	public void setR_capability(String value) {
		this.r_capability = value;
	}
  public java.math.BigDecimal getS_capability() {
   	return this.s_capability;
	}
	public void setS_capability(java.math.BigDecimal value) {
		this.s_capability = value;
	}
  public java.math.BigDecimal getS_personal() {
   	return this.s_personal;
	}
	public void setS_personal(java.math.BigDecimal value) {
		this.s_personal = value;
	}
  public java.math.BigDecimal getS_total() {
   	return this.s_total;
	}
	public void setS_total(java.math.BigDecimal value) {
		this.s_total = value;
	}

}
