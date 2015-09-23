/*
* <p> Company: 官房电子科技有限公司</p>
* <p> Created on 2013-12-2下午6:27:55</p>
* <p> @author 李存永</p>
*/
package com.web.common;

import java.util.List;
import java.util.Map;


/**
 * @param <T>
 * @类功能说明：审批操作Entity
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作	    者：李存永
 * @创建时间：2013-12-2 下午6:27:55
 */
public class Approval{

	private Object mainT;			//审批主表
	private List<Object> sublistT;		//审批记录子表
	private boolean isBack;			//是否打回
	private boolean isPass;			//是否通过
	private boolean isCancel;		//是否不通过
	private String approOpinion;	//审批意见
	private String dmlb;			//Code表中审批状态 : 代码类别： DMLB
	private IInsertApprovalInfo insertApprovalInfo ; //审批回填子表(审批记录表)接口
	
	@SuppressWarnings("rawtypes")
	private Map loginUser;			//当前登录用户
	
	public Object getMainT() {
		return mainT;
	}
	public void setMainT(Object mainT) {
		this.mainT = mainT;
	}	
	public List<Object> getSublistT() {
		return sublistT;
	}
	public void setSublistT(List<Object> sublistT) {
		this.sublistT = sublistT;
	}
	public boolean isBack() {
		return isBack;
	}
	public void setBack(boolean isBack) {
		this.isBack = isBack;
	}
	public boolean isPass() {
		return isPass;
	}
	public void setPass(boolean isPass) {
		this.isPass = isPass;
	}
	public boolean isCancel() {
		return isCancel;
	}
	public void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}
	public String getApproOpinion() {
		return approOpinion;
	}
	public void setApproOpinion(String approOpinion) {
		this.approOpinion = approOpinion;
	}
	@SuppressWarnings("rawtypes")
	public Map getLoginUser() {
		return loginUser;
	}
	@SuppressWarnings("rawtypes")
	public void setLoginUser(Map loginUser) {
		this.loginUser = loginUser;
	}	
	public IInsertApprovalInfo getInsertApprovalInfo() {
		return insertApprovalInfo;
	}
	public void setInsertApprovalInfo(IInsertApprovalInfo insertApprovalInfo) {
		this.insertApprovalInfo = insertApprovalInfo;
	}
	public String getDmlb() {
		return dmlb;
	}
	public void setDmlb(String dmlb) {
		this.dmlb = dmlb;
	}
}
