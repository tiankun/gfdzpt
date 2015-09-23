/*
 <p> Company: 官房电子科技有限公司</p>
* <p> Created on 2013-12-2上午11:18:59</p>
* <p> @author 李存永</p>
*/
package com.web.common;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.phprpc.PHPRPC_Client;

import com.alibaba.fastjson.JSON;
import com.map.Hr_base_info;
import com.map.Sys_approve;
import com.map.code;
import com.map.mrbranch;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.util.Constant;
import com.zsc.Mas;

/**
 * @类功能说明：通用审批工具 
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作	    者：李存永
 * @创建时间：2013-12-2 上午11:18:59
 */
public class ApproveUtil{
	
	private static final String BACKSTATUS="打回";
	private static final String CANCELSTATUS="不通过";
	private static ApproveUtil approveUtil = null;
	private DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	public List<ApprovalPermission> permissionList ;
	private Map<Object,Object> isPassByApprovalPrem ;
	private Approval approval;
	
	@SuppressWarnings("static-access")
	private ApproveUtil(){
		if(isPassByApprovalPrem == null){
			try {
				isPassByApprovalPrem =  BuildDDLUtil.getInstance().getDataMap(null,new String[] {
										"select co.dmzmc,co.DMZ from code co where co.dmlb='isPassByApproval' order by co.plsx ",
										"dmzmc", "dmz" });
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(isPassByApprovalPrem == null)
				isPassByApprovalPrem = new HashMap<Object,Object>();			
		}
		
	}
	
	public static ApproveUtil getInstance(){
		if(approveUtil == null){
			synchronized(ApproveUtil.class){
				if(approveUtil == null){
					approveUtil = new ApproveUtil();
				}
			}
		}
		return approveUtil;
	}
	
	/*审批处理*/
	@SuppressWarnings("unused")
	public void approveRequest(Approval sourApproval) {
		//TODO:验证权限:当前审批状态下的 权限
		this.approval = sourApproval;
		initArrpList();
		
		Map<String,Object> apprMap = resetLevel("appstatus",false,null,true);
		if(apprMap != null && apprMap.get("dmz")!=null){
			int levelFlag = Integer.valueOf(String.valueOf(apprMap.get("dmz")));
			resetStatus(levelFlag);
			saveAppInfo(levelFlag);
			if(apprMap != null && apprMap.get("sys_approve")!=null){
				Sys_approve sys_approve = (Sys_approve) apprMap.get("sys_approve");
				if(sys_approve.getLicensorid() != null){
					Long nextApprovalId =  sys_approve.getLicensorid();
					if(isLastByCurStatus(approval))
						nextApprovalId = Long.valueOf(String.valueOf(getMainTFieldVal("personid")));					
					resetLevel("nextapproverid",true,nextApprovalId,false);
				}	
			}
		
			try {
				dataBaseControl.beginTransaction();
				dataBaseControl.updateByBean(approval.getMainT());
				List<Object> subObjList = this.approval.getSublistT();
				for (Object object : subObjList) {
					dataBaseControl.insertByBean(object , dataBaseControl.getSeq(object.getClass()));
				}
				dataBaseControl.endTransaction();
				notifyNextApprover(apprMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/* 待办事项 提醒 && 将下一阶段审批人员ID写入主表*/	 
	public void notifyNextApprover(Map<String, Object> apprMap) {
		if(apprMap == null || apprMap.get("sys_approve")==null || apprMap.get("dmz") == null)
			return;
		Sys_approve sys_approve = (Sys_approve) apprMap.get("sys_approve");
		if("0".equals(sys_approve.getIsreceivemsg()))//如果接收短信状态为0则返回，不发送短信
			return ;
		
		Integer dmz = Integer.valueOf(String.valueOf(apprMap.get("dmz")).trim());
		Long requesterID =  Long.valueOf(String.valueOf(getMainTFieldVal("personid")));
		if(sys_approve.getLicensorid() != null && dmz > 0){
			Long nextApproverID = sys_approve.getLicensorid();

			
			String sql = "select hbi.name approverName,hbi.phone approverPhone,(select name from hr_base_info where id=?) requesterName,"+
					"(select phone from hr_base_info where id=?) requestPhone from hr_base_info hbi where id=?";
			List<Map<String,Object>> notiList = null;
			try {
				notiList = dataBaseControl.getOutResultSet(sql, new Object[]{requesterID,requesterID,nextApproverID});
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String curDmz = String.valueOf(dmz);
			String objAppr = "";
			for (ApprovalPermission curPermiss : permissionList) {
				code curCode = curPermiss.getCode();
				if(curDmz.equals(curCode.getDmz())){
					objAppr = curCode.getDmlbmc();
				}
			}
			String approverName = "",approverPhone = "";
			String requesterName = "",requestPhone = "";
			if(notiList.size() ==1){
				Map<String,Object> notiMap = notiList.get(0);
				approverName = String.valueOf(notiMap.get("approvername"));
				approverPhone = String.valueOf(notiMap.get("approverphone"));
				requesterName = String.valueOf(notiMap.get("requestername"));
				requestPhone = String.valueOf(notiMap.get("requestPhone"));
				
				PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
				Mas m = (Mas) client.useService(Mas.class);
				String infor= "来自官房电子平台： " + requesterName + " 请求 [ " + objAppr + " ] ，请您批阅！";
				if(isLastByCurStatus(approval)){
					infor= "来自官房电子平台： 您  提出的 [ "+objAppr+" ] 申请审批通过了！ ";
					approverName = requesterName;
					approverPhone = requestPhone;
				}	
				System.out.println("==================================================================================================================>>> 发送给号码：   " + approverPhone + " 发送对象：   "+approverName+"   短信： " + infor);
				//m.sendGFDZ("13648802937",infor);//m.sendGFDZ(approverPhone , infor);
			}
		}else{
			String sql = "select hbi.phone requesterPhone from hr_base_info hbi where id=?";
			List<Map<String,Object>> notiList = null;
			try {
				notiList = dataBaseControl.getOutResultSet(sql, new Object[]{requesterID});
			} catch (Exception e) {
				e.printStackTrace();
			}
			Map<String,Object> notiMap = null;
			if(notiList.size()==1){
				notiMap = notiList.get(0);
				String approverPhone = String.valueOf(notiMap.get("requesterphone"));
				String preInfo = approval.isBack() ? "  打回  " : "  不通过  ";
				preInfo = String.format(" 您有  %s 的申请，请查阅！", preInfo);
				
				PHPRPC_Client client = new PHPRPC_Client(Constant.HTTPDRESS);
				Mas m = (Mas) client.useService(Mas.class);
				preInfo = "来自官房电子平台： " + preInfo;
				System.out.println("===================================>>> 发送短信： " + preInfo);
				//m.sendGFDZ("13648802937",preInfo);//m.sendGFDZ(approverPhone , infor);
			}
		}
	}

	/*获取初始保存状态  : 普通员工  && 经理 申报得取消 本人审批   ====>>> 登录用户为 Admin 时异常！*/
	public Map<String,Object> saveAppLevelByInitStatus(Approval approvalSour){
		this.approval = approvalSour;
		initArrpList();
		Map<String,Object> initAppMap = new HashMap<String,Object>();
		
		if(approval.getLoginUser() != null && approval.getLoginUser().get("base_info_id") != null && approval.getLoginUser().get("dept_id") != null){
			Integer loginUserID = Integer.valueOf(String.valueOf(approval.getLoginUser().get("base_info_id")));
			BigDecimal loginUserDepartid = BigDecimal.valueOf(Long.valueOf(String.valueOf(approval.getLoginUser().get("dept_id"))));
		
			try {
				String sqlPerson = "select hbi.duty_id from hr_base_info hbi where hbi.id = ?";
				List<Map<String,Object>> personDuty = dataBaseControl.getOutResultSet(sqlPerson, new Object[]{loginUserID});
				if(personDuty!= null && personDuty.size() == 1 && personDuty.get(0).get("duty_id") != null){
					String dutyId = String.valueOf(personDuty.get(0).get("duty_id"));
					/*部门级审批*/
					Object planeflag = getMainTFieldVal("planeflag");
					//当planeflag不为空且等于0 时执行部门内审批：出差申请过滤此条件 planeflag != null&&Integer.valueOf(String.valueOf(planeflag))==0 					
					if("1".equals(dutyId) && loginUserDepartid.compareTo(new BigDecimal("9")) != 0 
							&& loginUserDepartid.compareTo(new BigDecimal("6")) != 0	//弃除财务部申请[部门级审批]
							&& (planeflag == null || (planeflag != null&&Integer.valueOf(String.valueOf(planeflag))==0))){
						if(permissionList != null && permissionList.size() >0 && permissionList.get(0) != null && permissionList.get(0).getCode() != null && permissionList.get(0).getCode().getDmz() != null){
							String levelStr = permissionList.get(0).getCode().getDmz(); 
							if(levelStr != null && !"".equals(levelStr) && !"null".equals(levelStr)&& !"NULL".equals(levelStr)){
								if(permissionList.get(0).getApproList().size() > 0){
									for (Sys_approve sys_approve : permissionList.get(0).getApproList()) {
										Long loginUserDepartID = Long.valueOf(String.valueOf(approval.getLoginUser().get("dept_id")));
										if(sys_approve.getApproval_departid().equals(loginUserDepartID)){
											initAppMap.put("sys_approve", sys_approve);
										}
									}
								}
								initAppMap.put("isAppr", true);
								initAppMap.put("dmz", levelStr);
							}
						}		
					}else{ /*公司中层级审批*/  //TODO: getLevelByNoAppCriteria(int nextIndex) 代码优化
						Object approvalID = getMainTFieldVal("nextapproverid");
						String requestID = String.valueOf(getMainTFieldVal("personid"));
						for(int i = 1; i< permissionList.size();i++){
							ApprovalPermission permission = permissionList.get(i);
							for (Sys_approve sysApp : permission.getApproList()) {
								if(sysApp.getApprovaldeparts() == null 
									&& sysApp.getApprovalpersons() == null
									&& !Long.valueOf(loginUserID).equals(sysApp.getLicensorid())
									&& (approvalID==null||!String.valueOf(approvalID).equals(String.valueOf(sysApp.getLicensorid())))//下一级别审批授权人不能是当前申请审批阶段的审批人 
									&& !requestID.equals(String.valueOf(sysApp.getLicensorid()))){	//下一级别审批授权人不能是当前申请人
										initAppMap.put("sys_approve", sysApp);
										initAppMap.put("isAppr", true);
										initAppMap.put("dmz", permission.getCode().getDmz());
										return initAppMap;
								}
							}
						}
/*						if(permissionList != null&& permissionList.size() >1 && permissionList.get(0) != null 
								&& permissionList.get(1).getCode() != null && permissionList.get(1).getApproList()!=null 
								&& permissionList.get(1).getApproList().size() > 0 && permissionList.get(1).getApproList().get(0) != null
								&& permissionList.get(1).getApproList().get(0).getApprovaldeparts() == null 
								&& permissionList.get(1).getApproList().get(0).getApprovalpersons() == null
								&& !Long.valueOf(loginUserID).equals(permissionList.get(1).getApproList().get(0).getLicensorid()) //当前申请人不能是授权人：过滤 部门经理或审批公司的授权人
								&& (approvalID==null||!Integer.valueOf(String.valueOf(approvalID)).equals(permissionList.get(1).getApproList().get(0).getLicensorid()))){//下一级别审批授权人不能是当前申请审批阶段的审批人
									initAppMap.put("sys_approve", permissionList.get(1).getApproList().get(0));
									initAppMap.put("isAppr", true);
									initAppMap.put("dmz", permissionList.get(1).getCode().getDmz());
						}else{	*/
							/*公司高层级审批*/
							Map<String,Object> noCritAppMap = getInitStatusByDepartIDPersonID(loginUserID,loginUserDepartid);
							if(noCritAppMap.get("dmz")!=null){
								initAppMap.put("sys_approve", noCritAppMap.get("sys_approve"));
								initAppMap.put("isAppr", noCritAppMap.get("isAppr"));
								initAppMap.put("dmz", noCritAppMap.get("dmz"));								
							}
//						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		return initAppMap;			
	}
	
	/*获取部门经理以上的初始审批状态 && 查找能审批当前业务的审批状态 */
	private Map<String,Object> getInitStatusByDepartIDPersonID(Integer loginUserID,
			BigDecimal loginUserDepartid,int...index) {
		Map<String,Object> noCritAppMap = new HashMap<String,Object>();
		
		if(permissionList != null && permissionList.size() >0){
			
			int i = 0;
			if(index!=null && index.length > 0 && index[0] != -1 && index[0] < permissionList.size())
				i = index[0];
			
			for (; i < permissionList.size(); i++) {
				ApprovalPermission permission = permissionList.get(i);		
				if(permission.getApproList() != null){
					for (Sys_approve sysApp : permission.getApproList()) {
						String persons = sysApp.getApprovalpersons();
						String departs = sysApp.getApprovaldeparts();
						//判断当前业务是否能审批当事人 //TODO:申请人是和审批人不能是同一人:不能审批本人申请===>在部门中进行审批
						if(persons != null && !"".equals(persons) && !"null".equals(persons) && !"NULL".equals(persons)
								&& !loginUserID.equals(Integer.valueOf(String.valueOf(sysApp.getLicensorid())))){
							List<Hr_base_info> personList = JSON.parseArray(persons, Hr_base_info.class);
							for (Hr_base_info hr_base_info : personList) {
								if(hr_base_info.getId().equals(loginUserID)){
									if(permission.getCode() != null){
										noCritAppMap.put("sys_approve", sysApp);
										noCritAppMap.put("isAppr", true);
										noCritAppMap.put("dmz", permission.getCode().getDmz());
									}
								}
						
							}
						}
						//判断当前业务是否能审批当事人所在部门 //TODO:申请人部门是和审批人部门不能相同 xxx  && !loginUserDepartid.equals(new BigDecimal(sysApp.getApproval_departid()))
						if(departs != null && !"".equals(departs) && !"null".equals(departs) && !"NULL".equals(departs)
								&& !loginUserID.equals(Integer.valueOf(String.valueOf(sysApp.getLicensorid())))){
							List<mrbranch> mrbranchList = JSON.parseArray(departs, mrbranch.class);
							for (mrbranch curMrbranch : mrbranchList) {
								//BigDecimal比较大小用 compareTo 返回值：int === > -1表示小于，0是等于，1是大于
								if(curMrbranch.getId().compareTo(loginUserDepartid) == 0
										&& !sysApp.getLicensorid().equals(String.valueOf(loginUserID))){ 
									if(permission.getCode() != null){
										noCritAppMap.put("sys_approve", sysApp);
										noCritAppMap.put("isAppr", true);
										noCritAppMap.put("dmz", permission.getCode().getDmz());
									}
								}
							}
						}
					}					
				}
			}
		}
		return noCritAppMap;
	}

	/*获取当前审批记录的审批权限*/
	public boolean validPermiss(Approval approvalSour){
		this.approval = approvalSour;
		initArrpList();
		
		Long loginUserId = null,loginUserDept = null;
		if(this.approval.getLoginUser() != null && this.approval.getLoginUser().get("base_info_id") != null){
			loginUserId = Long.valueOf(String.valueOf(this.approval.getLoginUser().get("base_info_id")));
			loginUserDept = Long.valueOf(String.valueOf(this.approval.getLoginUser().get("dept_id")));
		}	
		
		//获取当前状态级别
		Object objPrem = getMainTFieldVal("appstatus");
		if(objPrem != null){
			String appstatus = String.valueOf(objPrem).trim();
			Long requestDept = Long.valueOf(String.valueOf(getMainTFieldVal("departid")));
			
			//当前登录人是否在permission列表中
			for (ApprovalPermission permission : permissionList) {
				code curCode = permission.getCode();
				if(appstatus.equals(curCode.getDmz())){
					for (Sys_approve sysApprove : permission.getApproList()) {
						if(loginUserId.equals(sysApprove.getLicensorid()) 
							&& !"1".equals(sysApprove.getIsselfapproval())){
								if("1".equals(sysApprove.getIsapproval_deaprt()) && loginUserDept!=5){//总经办崔总除外:崔总可以进行部门级审批：审批对象(客户部)
									return loginUserDept.equals(requestDept);
								}
								return true;
						}
					}
				}
			}
		}
		return false;		
	}
	
	/*是否是本人申请纪录*/
	public boolean isSelfRequestByRecord(Approval approvalSour){
		this.approval = approvalSour;
		initArrpList();
		
		try{
			String reqPersonid = String.valueOf(getMainTFieldVal("personid"));
			String loginUserID = String.valueOf(approval.getLoginUser().get("base_info_id"));
			if(reqPersonid != null && loginUserID!=null && reqPersonid.equals(loginUserID) && isLastByCurStatus(this.approval,true)){
				return true;				
			}
		}catch(Exception e){
			e.printStackTrace();								
		}
		
		return false;
	}
	
	/*获取初始状态值*/
	public String getFirstStatus(Approval approvalSour){
		this.approval = approvalSour;
		initArrpList();
		
		if(permissionList != null && permissionList.size() > 0){
//			Map<String,Object> initStatus = saveAppLevelByInitStatus(approvalSour);
//			Object dmzObj = initStatus.get("dmz");
//			return dmzObj == null ? null : String.valueOf(dmzObj);	
			code firCode = permissionList.get(0).getCode();
			return firCode.getDmz();
		}
		return null;
	}
	
	/*判断当前状态是否是最终/最初状态*/
	public boolean isLastByCurStatus(Approval approvalSour,boolean...isFirst){
		this.approval = approvalSour;
		initArrpList();
		
		//获取当前状态级别
		String appstatus = String.valueOf(getMainTFieldVal("appstatus"));
		if(appstatus!=null&&!"".equals(appstatus))
			appstatus = appstatus.trim();
		int index = isFirst != null&&isFirst.length > 0&&isFirst[0] ? 0 : permissionList.size()-1;					
		ApprovalPermission permission = permissionList.get(index);
		code lastCode = permission.getCode();
		if(appstatus.equals(lastCode.getDmz())){
			return true;
		}		
		return false;
	}

	/*校验是否是当前部门审批*/
	private Map<String,Object> isCurDepartAppr(int...index) {
		Object id = getFieldVal("id");			
		if(id == null) 
			return null;
		
		Map<String,Object> curDepartApprMap = new HashMap<String,Object>();
		
		String sql = "SELECT * FROM  " + approval.getMainT().getClass().getSimpleName() + " WHERE id = ?";
		String level = "";
		String departID = "";
//		String picePersonid = String.valueOf(getMainTFieldVal("personid"));
//		String loginUserId = String.valueOf(approval.getLoginUser().get("base_info_id"));
		
		try {
//			String appLevel = permissionList.get(permissionList.size()-2).getCode().getDmz();
			List<Map<String,Object>> appstList = dataBaseControl.getOutResultSet(sql, new Object[]{id});
			if(appstList.size() == 1){
				level = String.valueOf(appstList.get(0).get("appstatus")).trim();
				departID = String.valueOf(appstList.get(0).get("departid"));		//TODO： 从 approval mainT 中获取departid
				if(level != null && !"".equals(level) && !"null".equals(level)&& !"NULL".equals(level)){
					String loginUserID = String.valueOf(approval.getLoginUser().get("base_info_id"));
					
					int i = 0;
					if(index!=null && index.length > 0 && index[0] != -1 && index[0] <= permissionList.size())
						i = index[0];
					
					for (; i < permissionList.size(); i++) {
							ApprovalPermission permission = permissionList.get(i);							
							if(level.equals(permission.getCode().getDmz())){
								for (Sys_approve sysApp : permission.getApproList()) {
									String isAppDepart = sysApp.getIsapproval_deaprt();
									String isselfapproval = sysApp.getIsselfapproval();
//									if(index.length <= 0){//TODO:判断是否是部门内审批
										if(isAppDepart != null && !"".equals(isAppDepart) && !"null".equals(isAppDepart)&& !"NULL".equals(isAppDepart)&& "1".equals(isAppDepart.trim())){
											if(departID.equals(String.valueOf(sysApp.getApproval_departid())) && loginUserID.equals(String.valueOf(sysApp.getLicensorid()))){
												curDepartApprMap.put("sys_approve", sysApp);
												curDepartApprMap.put("isAppr", true);
											}
										}else if(loginUserID.equals(String.valueOf(sysApp.getLicensorid())) && isselfapproval != null && !"1".equals(isselfapproval)){
											curDepartApprMap.put("sys_approve", sysApp);
											curDepartApprMap.put("isAppr", true);
										}
								}
							}
					}
				
						
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return curDepartApprMap;
	}

	/*获取字段值*/
	private Object getFieldVal(String fieldName) {
		Object id = null;
		if(approval!= null && approval.getMainT() != null){
			Object obj = approval.getMainT();
			
			Field field = null;
			boolean accessFlag  = false;
			
			try {
				field = obj.getClass().getDeclaredField(fieldName);
				accessFlag = field.isAccessible();
				field.setAccessible(true);
				id = field.get(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(field != null)
					field.setAccessible(accessFlag);
			}
		}
		return id;
	}
	
	private void initArrpList(){
//		if(permissionList == null){
			readDMLBByCode();
//			if(permissionList == null){
//				permissionList = new ArrayList<ApprovalPermission>();
//			}
//		}
	}
	
	/*根据code表读取审批阶梯状态*/
	private void readDMLBByCode(){
		String sqlApp = "SELECT ID,DMLB,DMLBMC,DMZ,DMZMC,KWHBZ,YXBZ,PLSX FROM code co WHERE co.DMLB = ?  ORDER BY plsx";
		
		try {
			List<Map<String,Object>> origCodeList = dataBaseControl.getOutResultSet(sqlApp, new Object[]{approval.getDmlb()});//travel_appstatus
			String codeJsonStr = JSON.toJSONString(origCodeList);		
//			apprList = JSON.parseObject(jsonStr, new TypeReference<List<code>>(){});
			List<code> codeList = JSON.parseArray(codeJsonStr, code.class);
			permissionList = new ArrayList<ApprovalPermission>();
			ApprovalPermission permission = null;
			for (code code : codeList) {
				permission = new ApprovalPermission();
				permission.setCode(code);
				permission.setApproList(initAppPerson(permission.getCode().getId()));
				permissionList.add(permission);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*初始化当前审批级别的用户*/
	private List<Sys_approve> initAppPerson(BigDecimal id) {
		List<Sys_approve> sysApp = null;
		String sqlPerson = "SELECT id,licensorid,approval_departid,approvaldeparts,approvalpersons,approval_rating,precedence_level,isapproval_deaprt,isselfapproval,enable,aroupid,remark,isreceivemsg " +
				" FROM SYS_APPROVE sapp  WHERE sapp.APPROVAL_RATING = ?  ORDER BY sapp.PRECEDENCE_LEVEL";
		try {
			List<Map<String,Object>> origPersonList = dataBaseControl.getOutResultSet(sqlPerson, new Object[]{id});
			String personStr = JSON.toJSONString(origPersonList);
			sysApp = JSON.parseArray(personStr, Sys_approve.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysApp;
	}
	
	/**
	 * 获取下一级别审批状态   && 设置某个字段的值   
	 * @param fieldName 要设置值的字段名称
	 * @param value 字段值
	 * @param isSetVal 是否设置字段值
	 * @param isReSetNextLevel 是否设置为下一审批级别
	 * @return 设置字段值成功   或  返回下一审批状态值
	 */
	public Map<String,Object> resetLevel(String fieldName,boolean isSetVal,Object value,boolean isReSetNextLevel){
		Map<String,Object> mainMap = null;
		if(fieldName == null || "".equals(fieldName))
			return mainMap;
		Field curStaField = null;
		boolean accessFlag = false;
		
		try {
			curStaField = approval.getMainT().getClass().getDeclaredField(fieldName);
			accessFlag = curStaField.isAccessible();
			curStaField.setAccessible(true);
				
			if(isSetVal && value != null){
				curStaField.set(approval.getMainT(), value);
			}else{
				mainMap = new HashMap<String,Object>();
				Sys_approve sys_approve = new Sys_approve();
				sys_approve.setLicensorid(Long.valueOf(String.valueOf(approval.getLoginUser().get("base_info_id"))));	
				if(approval.isBack()){
					mainMap.put("sys_approve", sys_approve);
					mainMap.put("dmz",isPassByApprovalPrem.get(BACKSTATUS));
				}else if(approval.isCancel()){
					mainMap.put("sys_approve", sys_approve);
					mainMap.put("dmz",isPassByApprovalPrem.get(CANCELSTATUS));
				}else if(approval.isPass()){					
					String level = String.valueOf(curStaField.get(approval.getMainT()));
					int index = -1; 
					if(permissionList != null && permissionList.size() > 0){
						for (ApprovalPermission permission  : permissionList) {
							if(permission.getCode() != null && permission.getCode().getDmz() != null && !"".equals(permission.getCode().getDmz())&&permission.getCode().getDmz().trim().equals(level.trim())){
								index = permissionList.indexOf(permission);
								break;
							}
						}
					}
					//TODO: 对外抛出 不同审批过程的 审批规则
					mainMap = loopBackLevel(index);
					if(isReSetNextLevel && mainMap.get("dmz")!=null)
						curStaField.set(approval.getMainT(),mainMap.get("dmz"));
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(curStaField != null)
				curStaField.setAccessible(accessFlag);
		}
		return mainMap;	
	}
	
	/*循环查找出能审批当前记录的审批级别*/
	private Map<String,Object> loopBackLevel(int index) {
		Map<String,Object> nextApprMap = null;
		int nextIndex = index + 1;		
		boolean approvalFlag;
		
		//判断是否是本人审批
		nextApprMap = isCurPersonAppr(index);
		if(nextApprMap!=null && nextApprMap.get("isAppr")!=null){ 
			approvalFlag = (Boolean) nextApprMap.get("isAppr");
			if(approvalFlag){
				ApprovalPermission nextPermisson = permissionList.get(nextIndex);
				if(nextPermisson != null && nextPermisson.getCode() != null 
						&& nextPermisson.getCode().getDmz() != null 
						&& nextPermisson.getCode().getDmz().length() > 0
						&& nextPermisson.getApproList()!=null
						&& nextPermisson.getApproList().size() >0){				
							String level = nextPermisson.getCode().getDmz();
							for (Sys_approve sysApp : nextPermisson.getApproList()) {
								String isAppDepart = sysApp.getIsapproval_deaprt();
								String departID = String.valueOf(getMainTFieldVal("departid"));
								
								if(level != null && !"".equals(level) && !"null".equals(level)&& !"NULL".equals(level)){
									if(isAppDepart != null && !"".equals(isAppDepart) 
											&& !"null".equals(isAppDepart)&& !"NULL".equals(isAppDepart)
											&& "1".equals(isAppDepart.trim())
											&& departID.equals(String.valueOf(sysApp.getApproval_departid()))){
												nextApprMap.put("sys_approve", sysApp);
									}
								}
							}
							nextApprMap.put("dmz", level);
							nextApprMap.put("dmz", nextPermisson.getCode().getDmz());
							return nextApprMap;					
				}
			}
		}
		
		//判断 部门内审批
		Object planeflag = getMainTFieldVal("planeflag");
		//当planeflag不为空且等于0 时执行部门内审批：出差申请过滤此条件
		if(planeflag==null || (planeflag!=null && Integer.valueOf(String.valueOf(planeflag))==0)){		
			nextApprMap = isCurDepartAppr(nextIndex);
			if(nextApprMap!=null && nextApprMap.get("isAppr")!=null){ 
				approvalFlag = (Boolean) nextApprMap.get("isAppr");
				if(approvalFlag){
					ApprovalPermission nextPermisson = permissionList.get(nextIndex);
					if(nextPermisson != null && nextPermisson.getCode() != null && nextPermisson.getCode().getDmz() != null && nextPermisson.getCode().getDmz().length() > 0){
						nextApprMap.put("dmz", nextPermisson.getCode().getDmz());
						return nextApprMap;				
					}
				}
			}
		}
		
		//判断 指定人员/指定部门审批 全公司
		nextApprMap = getLevelByNoAppCriteria(nextIndex);
		if(nextApprMap != null && nextApprMap.get("dmz") != null){
			approvalFlag = (Boolean) nextApprMap.get("isAppr");
			if(approvalFlag){
				return nextApprMap;
			}	
		}
		
		//判断 审批指定人员 和 审批指定部门 : 传入参数：数据申请人 ::: 高层人员审批
		Object userid = getMainTFieldVal("personid");
		Object userDepartid = getMainTFieldVal("departid");
		if(userid != null && userDepartid != null){
			Integer requestUserID = Integer.valueOf(String.valueOf(userid));
			BigDecimal requestUserDepartid = BigDecimal.valueOf(Long.valueOf(String.valueOf(userDepartid)));
			nextApprMap = getInitStatusByDepartIDPersonID(requestUserID,requestUserDepartid,nextIndex);
			if(nextApprMap.get("dmz") != null){
				return nextApprMap;
			}
		}
		
		//判断  结束审批。(非部门内审批、指定人员/指定部门审批)
		nextApprMap = new HashMap<String,Object>();
		for (; nextIndex < permissionList.size(); nextIndex++) {
			ApprovalPermission nextPermisson = permissionList.get(nextIndex);
			if(nextPermisson!= null ){
				code code = nextPermisson.getCode();
				if((nextPermisson.getApproList() == null || nextPermisson.getApproList().size() <= 0) &&
						code != null && code.getDmz() != null){
					//TODO: 是否做当事人结束审批验证
					Sys_approve sys_approve = new Sys_approve();
					sys_approve.setLicensorid(Long.valueOf(String.valueOf(approval.getLoginUser().get("base_info_id"))));					
					nextApprMap.put("sys_approve", sys_approve);
					nextApprMap.put("isAppr", true);
					nextApprMap.put("isOverAppr", true);
					nextApprMap.put("dmz", code.getDmz());
					return nextApprMap;
				}
				for (Sys_approve sysApp : nextPermisson.getApproList()) {
					String isAppDept = sysApp.getIsapproval_deaprt();
					if((isAppDept == null || "null".equals(isAppDept) || "NULL".equals(isAppDept)) 
							&& (sysApp.getApprovaldeparts() == null ||sysApp.getApprovaldeparts().length() < 5) 
							&& (sysApp.getApprovalpersons() == null || sysApp.getApprovalpersons().length() < 5)
							&& nextPermisson.getCode() != null && nextPermisson.getCode().getDmz() != null){
						nextApprMap.put("sys_approve", sysApp);
						nextApprMap.put("isAppr", true);
						nextApprMap.put("isOverAppr", true);
						nextApprMap.put("dmz", nextPermisson.getCode().getDmz());
						return nextApprMap;
					}
				}
			}
		}
		return null;
	}

	/*判断是否是当事人审批:审批过程开始*/
	private Map<String,Object> isCurPersonAppr(int index) {
		Map<String,Object> curPersonApprMap = new HashMap<String,Object>();
		curPersonApprMap.put("isAppr", false);	
		
		if(approval.getLoginUser() != null && approval.getLoginUser().get("base_info_id") != null){
			ApprovalPermission curPermission = permissionList.get(index);
			String reqPersonid = String.valueOf(getMainTFieldVal("personid"));
			String loginUserID = String.valueOf(approval.getLoginUser().get("base_info_id"));
			Integer duty_id = Integer.valueOf(String.valueOf(approval.getLoginUser().get("duty_id")));
			
			for (Sys_approve curApprove : curPermission.getApproList()) {
				if("1".equals(curApprove.getIsselfapproval()) 
						&& reqPersonid.equals(loginUserID)
						&& duty_id==1){//过滤部门经理及以上的审批
					//curPersonApprMap.put("sys_approve", curApprove);
					curPersonApprMap.put("isAppr", true);					
				}
			}
		}
		return curPersonApprMap;
	}

	/*判断 非部门内审批、指定人员/指定部门审批*/
	private Map<String,Object> getLevelByNoAppCriteria(int nextIndex) {
		Map<String,Object> noCritAppMap = null;		
		Object approvalID = getMainTFieldVal("nextapproverid");
		String requestID = String.valueOf(getMainTFieldVal("personid"));
		for (; nextIndex < permissionList.size(); nextIndex++) {
			ApprovalPermission nextPermisson = permissionList.get(nextIndex);
			if(nextPermisson!= null ){
				for (Sys_approve sysApp : nextPermisson.getApproList()) {
					String isAppDept = sysApp.getIsapproval_deaprt();
					if((isAppDept == null || "null".equals(isAppDept) || "NULL".equals(isAppDept))  
							&& (sysApp.getApprovaldeparts() == null ||sysApp.getApprovaldeparts().length() < 5) 
							&& (sysApp.getApprovalpersons() == null || sysApp.getApprovalpersons().length() < 5)
							&& nextPermisson.getCode() != null && nextPermisson.getCode().getDmz() != null
							&& (approvalID==null||!String.valueOf(approvalID).equals(String.valueOf(sysApp.getLicensorid())))//下一级别审批授权人不能是当前申请审批阶段的审批人 
							&& !requestID.equals(String.valueOf(sysApp.getLicensorid()))){	//下一级别审批授权人不能是当前申请人	
						noCritAppMap = new HashMap<String,Object>();
						noCritAppMap.put("sys_approve", sysApp);
						noCritAppMap.put("isAppr", true);
						noCritAppMap.put("dmz", nextPermisson.getCode().getDmz());
						return noCritAppMap;
					}
				}
			}
		}
		return noCritAppMap;
	}

	/*更新子表审批记录*/
	private void saveAppInfo(int levelVal) {
			ApproveInfoTemp approveInfoTemp = new ApproveInfoTemp();
			approveInfoTemp.setDepartid(Long.valueOf(String.valueOf(approval.getLoginUser().get("dept_id"))));
			approveInfoTemp.setParentid(Long.valueOf(String.valueOf(getMainTFieldVal("id"))));
			approveInfoTemp.setPersonnelid(Long.valueOf(String.valueOf(approval.getLoginUser().get("base_info_id"))));
			Integer status = approval.isPass()==true ? 1 : 0;
			approveInfoTemp.setAppstatus(status);
			approveInfoTemp.setAppidear(approval.getApproOpinion());//genAppStatus(levelVal)
			approveInfoTemp.setCurstage(genCurstage(levelVal));
			approveInfoTemp.setApptime(new Date(new java.util.Date().getTime()));
			if(approval.isBack())
				approveInfoTemp.setUndoReason(approval.getApproOpinion());
			if(approval.isCancel())
				approveInfoTemp.setUndoReason(approval.getApproOpinion());
			
			if(approval.getInsertApprovalInfo()!= null){
				Object mainT = approval.getMainT();
				List<Object> subObjList = approval.getInsertApprovalInfo().insertApprovalInfo(approveInfoTemp);
				approval.setMainT(mainT);
				approval.setSublistT(subObjList);
			}	
		}
	
	/*获取当前审批状态描述*/
	private String genCurstage(int levelVal) {
		for (ApprovalPermission permission  : permissionList) {
			if(permission.getCode().getDmz().trim().equals(String.valueOf(levelVal))){
				return permission.getCode().getDmzmc();
			}
		}
		return null;
	}

	/*获取 主表ID*/
	private Object getMainTFieldVal(String fieldName) {
		Object obj = approval.getMainT();
		Field field = null;
		Object result = null;
		boolean accessFlag = false;
		
		Field [] allFields = obj.getClass().getDeclaredFields();
		for (Field curField : allFields) {
			if(fieldName.equals(curField.getName())){
				accessFlag = true;
				break;
			}
		}
		if(!accessFlag)
			return result;		
		
		try {
			field = obj.getClass().getDeclaredField(fieldName);
			accessFlag = field.isAccessible();
			field.setAccessible(true);
			result = field.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(field != null)
				field.setAccessible(accessFlag);
		}
		return result;
	}
	
	/*重置审批状态*/
	private void resetStatus(int levelFlag){
		Field nextStaField = null;
		boolean accessFlag = false;
		Object mainObj = this.approval.getMainT();
		try {
			nextStaField = mainObj.getClass().getDeclaredField("appview");
			accessFlag = nextStaField.isAccessible();						
			nextStaField.setAccessible(true);
			nextStaField.set(mainObj, genAppStatus(levelFlag));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(nextStaField != null)
				nextStaField.setAccessible(accessFlag);
		}
	}

	/*生成审批 状态信息*/
	private String genAppStatus(int levelFlag) {
		String appStatus = " 作废 !";
		for (Object key : isPassByApprovalPrem.keySet()) {
			int keyFlag = Integer.valueOf(String.valueOf(isPassByApprovalPrem.get(key)));
			if(levelFlag == keyFlag){
				appStatus = String.valueOf(key);
				break;
			}
		}
		if(" 作废 !".equals(appStatus))
			appStatus = approval.isPass()? "  通过。" : (approval.isBack() ? " 打回。":" 不通过!");
		String status = String.format(approval.getLoginUser().get("USER_NAME") + " 的 审批意见：  %s " , appStatus);
		return status;
	}

}
