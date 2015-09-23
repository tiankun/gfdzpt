package com.map;

public class Hr_achieve {

	private Long id; // 主键id
	private Long dept_id; // 部门id
	private String audit_date; // 考核时间
	private String state; // 状态（为1时表示已提交，此时人事部才能查看）
	private Long audit_person; // 考核人
	private String type; // （0--职员考核；1--部门经理考核）
	private Long count;
	
    
	

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public Long getDept_id() {
		return this.dept_id;
	}

	public void setDept_id(Long value) {
		this.dept_id = value;
	}

	public String getAudit_date() {
		return this.audit_date;
	}

	public void setAudit_date(String value) {
		this.audit_date = value;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}

	public Long getAudit_person() {
		return this.audit_person;
	}

	public void setAudit_person(Long value) {
		this.audit_person = value;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String value) {
		this.type = value;
	}

}
