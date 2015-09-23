/*
* <p> Company: 官房电子科技有限公司</p>
* <p> Created on 2013-12-11下午5:07:29</p>
* <p> @author 李存永</p>
*/
package com.web.fi.travel;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;

/**
 * @类功能说明：省市县 区域获取 及 出差报账标准计算
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作	    者：廖望舒
 * @创建时间：2013-12-11 下午5:07:29
 */
public class SysAreaAction  extends BaseAction {

	private DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	
	/*获取省*/
	public void getProvince(final HttpServletRequest request , HttpServletResponse response) throws Exception {
		String sql = "select id,name from sys_area sa where sa.parent_id = '0' order by id ";
		List<Map<String,Object>> traveAccountlList = dataBaseControl.getOutResultSet(sql, null);		
		String jsonStr = JSON.toJSONString(traveAccountlList);				
		setAjaxInfo(response,jsonStr);
	}
	/*获取市*/
	public void getCity(final HttpServletRequest request , HttpServletResponse response)  throws Exception {
		String provinceID = request.getParameter("id");
		if(provinceID == null || "".equals(provinceID.trim())) 
			return;
		
		/*
		 *  ||!StringUtils.isNumeric(provinceID)  判断字符串是否是数字 ： 不能是负数
		 * */
		String sql = "select id,name from sys_area sa where sa.parent_id=? order by id ";
			List<Map<String,Object>> traveAccountlList = dataBaseControl.getOutResultSet(sql, new Object[]{provinceID});		
			String jsonStr = JSON.toJSONString(traveAccountlList);				
			setAjaxInfo(response,jsonStr);
	}
	
	/*获取报销标准*/
	public void getAreaLv(final HttpServletRequest request , HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String duty = request.getParameter("duty");
		String vehi = request.getParameter("vehi");
		String pid = request.getParameter("pid");
		Double subFund = 0.0;
		Double Std = 0.0;
		

		Map dutyMap = dataBaseControl.getOneResultSet("select t.id from code t where t.dmlb='duty_id' and t.dmz=?", new Object[]{duty});
		String duty_id = dutyMap.get("id").toString();
		if(!id.equals("--请选择区县--")){
			Map areaMap = dataBaseControl.getOneResultSet("select t.arealevel from sys_area t where t.id = ?", new Object[]{id});		
			String arealevel = areaMap.get("arealevel").toString();
			
			Map StdMap = dataBaseControl.getOneResultSet("select t.travel_substandards std from fi_travelsubstandard t where t.arealevelid=? and admlevel=?", new Object[]{arealevel,duty_id});
			Std = Double.parseDouble(StdMap.get("std").toString());
			
			String subIds = request.getParameter("subids")==null?"":request.getParameter("subids");
			
			/* 随行人员不为空时 */
			if(!subIds.equals("")){
				Map pMap = dataBaseControl.getOneResultSet("select t.sex from hr_base_info t where t.id=?", new Object[]{pid});
				String pSex = pMap.get("sex").toString();
				String subID = "";
				Map subMap = null;
				ArrayList<String> maleArr = new ArrayList<String>();
				ArrayList<String> femaleArr = new ArrayList<String>();
				JSONArray jsonArr = JSON.parseArray(subIds);
				/* 将随行人员Json数组中的人 分男女添加到男女数组中  */
				for (int i = 0; i < jsonArr.size(); i++) {
					subID = jsonArr.getJSONObject(i).get("ID").toString();
					subMap = dataBaseControl.getOneResultSet("select t.duty_id,t.sex from hr_base_info t where t.id=?", new Object[]{subID});
					if(subMap.get("sex").equals("0"))
						maleArr.add(subMap.get("duty_id").toString());
					if(subMap.get("sex").equals("1"))
						femaleArr.add(subMap.get("duty_id").toString());
				}
				
				/* 出差人与随行只有异性 */
				if((!maleArr.isEmpty()&&pSex.equals("1")&&femaleArr.isEmpty())||(!femaleArr.isEmpty()&&pSex.equals("0")&&maleArr.isEmpty())){
					ArrayList<String> tempArr = new ArrayList<String>();
					if(pSex.equals("1"))
						tempArr=maleArr;
					if(pSex.equals("0"))
						tempArr=femaleArr;
						
					String max=tempArr.get(0); //有异性按异性人员的职务最高者计算标准
					for(String i: tempArr){
						if(Integer.parseInt(i)>Integer.parseInt(max)) 
							max=i;
					}
					if(Integer.parseInt(duty)>Integer.parseInt(max)){	//比较出差人与异性职位最高者的职位，以职位大的为标准
						max=duty;
					}
					dutyMap = dataBaseControl.getOneResultSet("select t.id from code t where t.dmlb='duty_id' and t.dmz=?", new Object[]{max});
					String duty_idOpp = dutyMap.get("id").toString();
					
					areaMap = dataBaseControl.getOneResultSet("select t.arealevel from sys_area t where t.id = ?", new Object[]{id});		
					String arealevelOpp = areaMap.get("arealevel").toString();
					
					StdMap = dataBaseControl.getOneResultSet("select t.travel_substandards std from fi_travelsubstandard t where t.arealevelid=? and admlevel=?", new Object[]{arealevelOpp,duty_idOpp});
					Double StdOpp = Double.parseDouble(StdMap.get("std").toString());
					
					if(pSex.equals("0")){	//出差者为男性
						if(femaleArr.size()==1)	//随行人员人数判断
							subFund = StdOpp;
						if(femaleArr.size()>1){	//随行人员人数判断
							if(femaleArr.size()%2==0)	//随行人员为偶数
								subFund = StdOpp*(femaleArr.size()/2)*1.2;
							if(femaleArr.size()%2==1)	//随行人员为奇数
								subFund = StdOpp + StdOpp*(int)(femaleArr.size()/2)*1.2;
						}
					}
					if(pSex.equals("1")){	//出差者为女性
						if(maleArr.size()==1)	//计算方法同上
							subFund = StdOpp;
						if(maleArr.size()>1){
							if(maleArr.size()%2==0)
								subFund = StdOpp*(maleArr.size()/2)*1.2;
							if(maleArr.size()%2==1)
								subFund = StdOpp + StdOpp*(int)(maleArr.size()/2)*1.2;
						}
					}
				}
				//出差人与随行人只有同性
				else if((maleArr.isEmpty()&&pSex.equals("1")&&!femaleArr.isEmpty())/*出差人为女，随行有女无男*/||(femaleArr.isEmpty()&&pSex.equals("0")&&!maleArr.isEmpty())/*出差人为男，随行有男无女*/){
					if(pSex.equals("0")){
						if(maleArr.size()%2==0) //随行偶数
							Std = Std+Std*(maleArr.size()/2)*1.2;
						if(maleArr.size()%2==1)	//随行奇数
							Std = Std*1.2*(maleArr.size()+1)/2;
					}
					if(pSex.equals("1")){
						if(femaleArr.size()%2==0)	//同上
							Std = Std+Std*(femaleArr.size()/2)*1.2;
						if(femaleArr.size()%2==1)
							Std = Std*1.2*(femaleArr.size()+1)/2;
					}
				}
				//随行人员两性都有
				else{
					ArrayList<String> tempArr = new ArrayList<String>();
					if(pSex.equals("1"))
						tempArr=maleArr;
					if(pSex.equals("0"))
						tempArr=femaleArr;
						
					String max=tempArr.get(0); 
					for(String i: tempArr){		//有异性按异性人员的职务最高者计算标准
						if(Integer.parseInt(i)>Integer.parseInt(max)) 
							max=i;
					}
					if(Integer.parseInt(duty)>Integer.parseInt(max)){	//比较出差人与异性职位最高者的职位，以职位大的为标准
						max=duty;
					}
					dutyMap = dataBaseControl.getOneResultSet("select t.id from code t where t.dmlb='duty_id' and t.dmz=?", new Object[]{max});
					String duty_idOpp = dutyMap.get("id").toString();
					
					areaMap = dataBaseControl.getOneResultSet("select t.arealevel from sys_area t where t.id = ?", new Object[]{id});		
					String arealevelOpp = areaMap.get("arealevel").toString();
					
					StdMap = dataBaseControl.getOneResultSet("select t.travel_substandards std from fi_travelsubstandard t where t.arealevelid=? and admlevel=?", new Object[]{arealevelOpp,duty_idOpp});
					Double StdOpp = Double.parseDouble(StdMap.get("std").toString());
					
					if(pSex.equals("0")){	//出差人为男性
						if(maleArr.size()==1&&femaleArr.size()==1)	//随行男女各1人
							Std = Std*1.2 + StdOpp;
						if(maleArr.size()==1&&femaleArr.size()>1){	//随行男1人，女多人
							if(femaleArr.size()%2==0)	//女为偶数
								Std = Std + StdOpp*(femaleArr.size()/2)*1.2;
							if(femaleArr.size()%2==1)	//女为奇数
								Std = Std + StdOpp + StdOpp*(int)(femaleArr.size()/2)*1.2; 
						}
						if(maleArr.size()>1&&femaleArr.size()==1){	//随行男多人，女1人
							if(maleArr.size()%2==0) 	//男为偶数
								Std = Std+Std*(maleArr.size()/2)*1.2 + StdOpp;
							if(maleArr.size()%2==1)		//男为奇数
								Std = Std*1.2*(maleArr.size()+1)/2 + StdOpp;
						}
						if(maleArr.size()>1&&femaleArr.size()>1){	//随行男多人，女多人
							if(maleArr.size()%2==0&&femaleArr.size()%2==0) 	//男偶，女偶
								Std = Std+Std*(maleArr.size()/2)*1.2 + StdOpp*(femaleArr.size()/2)*1.2;
							if(maleArr.size()%2==1&&femaleArr.size()%2==0)	//男奇，女偶
								Std = Std*1.2*(maleArr.size()+1)/2 + StdOpp*(femaleArr.size()/2)*1.2;
							if(maleArr.size()%2==0&&femaleArr.size()%2==1) 	//男偶，女奇
								Std = Std+Std*(maleArr.size()/2)*1.2 + StdOpp + StdOpp*(int)(femaleArr.size()/2)*1.2;
							if(maleArr.size()%2==1&&femaleArr.size()%2==1)	//男奇，女奇
								Std = Std*1.2*(maleArr.size()+1)/2 + StdOpp + StdOpp*(int)(femaleArr.size()/2)*1.2;
						}
					}
					if(pSex.equals("1")){	//出差人为女性
						if(maleArr.size()==1&&femaleArr.size()==1)	//随行男女各1人
							Std = Std*1.2 + StdOpp;
						if(maleArr.size()==1&&femaleArr.size()>1){	//随行男1人，女多人
							if(femaleArr.size()%2==0)	//女为偶数
								Std = Std+Std*(femaleArr.size()/2)*1.2 + StdOpp;
							if(femaleArr.size()%2==1)	//女为奇数
								Std = Std*1.2*(femaleArr.size()+1)/2 + StdOpp;
						}
						if(maleArr.size()>1&&femaleArr.size()==1){	//随行男多人，女1人
							if(maleArr.size()%2==0) 	//男为偶数
								Std = Std + StdOpp*(maleArr.size()/2)*1.2;
							if(maleArr.size()%2==1)		//男为奇数
								Std = Std + StdOpp + StdOpp*(int)(maleArr.size()/2)*1.2;
						}
						if(maleArr.size()>1&&femaleArr.size()>1){	//随行男多人，女多人
							if(maleArr.size()%2==0&&femaleArr.size()%2==0) 	//男偶，女偶
								Std = Std+Std*(femaleArr.size()/2)*1.2 + StdOpp*(maleArr.size()/2)*1.2;
							if(maleArr.size()%2==1&&femaleArr.size()%2==0)	//男奇，女偶
								Std = Std*1.2*(femaleArr.size()+1)/2 + StdOpp*(maleArr.size()/2)*1.2;
							if(maleArr.size()%2==0&&femaleArr.size()%2==1) 	//男偶，女奇
								Std = Std+Std*(femaleArr.size()/2)*1.2 + StdOpp + StdOpp*(int)(maleArr.size()/2)*1.2;
							if(maleArr.size()%2==1&&femaleArr.size()%2==1)	//男奇，女奇
								Std = Std*1.2*(femaleArr.size()+1)/2 + StdOpp + StdOpp*(int)(maleArr.size()/2)*1.2;
						}
					}
				}
			}
		}
		
		Double fundStd = 0.0;
		if(vehi.equals("5")||vehi.equals("6")){
			fundStd = Std*0.85;
		}else{
			fundStd = Std;
		}
		fundStd = fundStd + subFund;
		PrintWriter writer =response.getWriter();
		writer.print(fundStd);
		writer.flush();
		writer.close();
	}
}
