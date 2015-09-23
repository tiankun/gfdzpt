package com.web.sysAdmin;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sysFrams.db.DataBaseControl;

public class codeCreateAction {
	DataBaseControl dataBaseControl=DataBaseControl.getInstance();
	String[] wc=new String[]{"0","1","2"};
	String[] wt=new String[]{"TB","SH","SG"};

	/**
	 * 编号生成
	 * @param String codeType 编码类型, String workCode 下级工作类型编码
	 * @return code
	 * @throws Exception 
	 */
	public void getCodeNum(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String codeType = request.getParameter("codeType")==null?"":request.getParameter("codeType");
		String workCode = request.getParameter("workCode")==null?"":request.getParameter("workCode");
		
		String codeNum = null;
		
		codeNum = getCode(codeType, workCode);
		PrintWriter writer =response.getWriter();
		writer.write(codeNum);
		writer.flush();
		writer.close();
	}
	
	public String getCode(String codeType, String workCode) throws Exception{
		
		String code = "";
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM");
		Date today = new Date();
		String date = dFormat.format(today);
		String thisyear = date.substring(0, 4);
		String thismonth = date.substring(5, 7);
		String time=thisyear+thismonth;
		
		String sql = "";
		Map cMap = new HashMap();
		if(codeType.equals("RW")){
			sql="select count(t.id) c from ds_dstask t where t.ds_type = ? " +
				"and to_number(to_char(launch_time,'yyyy'))= ? " +
				"and to_char(launch_time,'mm') like ? ";
			cMap = dataBaseControl.getOneResultSet(sql, new Object[]{workCode,thisyear,thismonth});
		}
		
		if(codeType.equals("CH")){
			sql="select count(t.id) c from ds_scheme t where t.ds_type = ? " +
				"and to_number(to_char(compile_time,'yyyy'))= ? " +
				"and to_char(compile_time,'mm') like ? ";
			cMap = dataBaseControl.getOneResultSet(sql, new Object[]{workCode,thisyear,thismonth});
		}
		
		if(codeType.equals("SCQD")){
			sql="select count(t.id) c from ds_result t where to_number(to_char(compile_time,'yyyy'))= ? " +
				"and to_char(compile_time,'mm') like ? ";
			cMap = dataBaseControl.getOneResultSet(sql, new Object[]{thisyear,thismonth});
		}
		
		String countString = cMap.get("c")==null&&cMap.get("c").equals("")?"001":(String)cMap.get("c").toString();
		int countInt =  Integer.parseInt(countString); 
		countInt = countInt+1;
		
		if(!codeType.equals("SCQD")){
			code = codeType + "-" + getWorkType(workCode) + "-" + time + "-" + getCount(countInt,countString);
		}
		else{
			code = codeType + "-" + time + "-" + getCount(countInt,countString);
		}
		return code;
	}
	
	/**
	 * 序号统一
	 * @param countInt countString
	 * @return count
	 */
	public String getCount(int countInt, String countString){
		String count = null;
		//使序号位数统一为3位
		int length = countString.length();
		if (length == 1){
			if(countInt==10){
				count = "0"+ Integer.toString(countInt);
			}
			else{
				count = "00"+ Integer.toString(countInt);
			}
		}
		else if(length ==2){
			if(countInt==100){
				count=Integer.toString(countInt);
			}
			else{
				count = "0"+ Integer.toString(countInt);
			}
		}
		else
			count = Integer.toString(countInt);
		
		return count;
	}
	
	/**
	 * 下级工作类型获取
	 * @param workCode
	 * @return workType
	 */
	public String getWorkType(String workCode){
		
		for (int i = 0; i < wc.length; i++) {
			if(workCode.equals(wc[i]))
				return wt[i];
		}
		return workCode;
	}
}
