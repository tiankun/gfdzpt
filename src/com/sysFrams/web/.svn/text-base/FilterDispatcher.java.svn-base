/*
 * <p> Company: 官房电子科技有限公司</p>
 * <p> Created on 2008-7-30</p>
 * <p> @author ShenChang zou</p>
 */
package com.sysFrams.web;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sysFrams.db.DataBaseControl;
import com.sysFrams.util.CommonProperties;
import com.sysFrams.util.Log;

/**
 * 对web请求内容进行编码
 */
public class FilterDispatcher extends HttpServlet {
	/**
	 * 对用户权限进行验证
	 * 
	 * @param request
	 * @return 当返回0是标示通过
	 * @throws  
	 * @throws Exception
	 */
	private int check(HttpServletRequest request) throws Exception{
		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		url = url.substring(contextPath.length());		//此行代码发布到服务器时会失效
		url = url.startsWith("/gfdzpt") ? url.substring("/gfdzpt".length()) : url;
		
		// 验证是否是不需要验证的地址
		if("/sysAdmin/Login!login.do".equals(url)||"/sysAdmin/Login!logout.do".equals(url)) return 0;
		DataBaseControl dataBaseControl = DataBaseControl.getInstance();
		Map fmap = dataBaseControl.getOneResultSet("select id,publics from sysfuncdic where delflag=0 and url=?", new Object[]{url});
		if(fmap == null || fmap.get("publics") == null) return 2;//无此功能
		if("1".equals(fmap.get("publics").toString())) return 0;
		
		
		HttpSession session = request.getSession(false);
		if (session == null) return -1;//需要登录才可访问
		
		//验证是否具有该功能权限
		Map user = (Map) session.getAttribute("user");
		Map pmap = dataBaseControl.getOneResultSet("select count(funcid) counts from sysfuncrole "
				+" where funcid=? and roleid in (select rolesid from sysuserroles where userid=?)", 
				new Object[]{fmap.get("id").toString(),user.get("id").toString()});
		if (pmap == null || Integer.parseInt(pmap.get("counts").toString())==0) return 3;//无权限
		if(Integer.parseInt(pmap.get("counts").toString())>0)	return 0;
			
		
			
		return 1; //登录过期
		
	}	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 进行总分发
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "/error.jsp"; // 默认为出错页面
		String servletPath = request.getServletPath(); // 获得客户端所请求的脚本文件的文件路径
		int outCheck = 0;
		try {
			int ret_check = check(request);
			if (ret_check==1) {
				Log.info("登录过期:"+servletPath);
				forward= forward +"?err=1";
			}else if(ret_check==3){
				Log.info("无权限:"+servletPath);
				forward= forward +"?err=3";
			}else if(ret_check==-1){
				Log.info("需要登录才可访问:"+servletPath);
				forward= forward +"?err=-1";
			}else if(ret_check==2){
				Log.info("无此功能:"+servletPath);
				forward= forward +"?err=2";
			}
			else {
				long start = 0;
				if (Log.isInfo())
					start = System.currentTimeMillis();
				String action = ""; // 实际请求的action类
				String methodName = "execute"; // 对应action类里面的方法
				// 以下模块用于根据传入路径解析出类名和方法名
				String actionMethod = servletPath.substring(servletPath.indexOf("/"), servletPath.length() - 3); // 获取action!method的组合
				String[] temp = actionMethod.split("!");
				if (temp.length >=2)
					methodName = temp[1];
				action = temp[0].replaceAll("/", ".") + "Action";
				action = action.startsWith(".gfdzpt") ? action.substring(".gfdzpt".length()) : action;	//此行代码发布到服务器时会异常
				// 以下模块用反射机制调用对应的类方法
				Class clazz = Class.forName(CommonProperties.getInstance().getWebPackage()+ action);
				
				Method method = clazz.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
				forward = (String) method.invoke(clazz.newInstance(), request,response);
				if (Log.isInfo())
					Log.info("执行"+ CommonProperties.getInstance().getWebPackage()+ action + "." + methodName + "()耗时："+ (System.currentTimeMillis() - start)+ "ms;  跳转到:" + forward);
			}
		} catch (Exception e) {
			e.printStackTrace();
			forward= forward +"?err=4&errInfo=";
		}
		// 执行相应的跳转
		if(forward!=null)
		if (forward.indexOf(".do") >= 0)// 再次回到后台进行处理
			response.sendRedirect(request.getContextPath() + forward);
		else if(forward.indexOf("error.jsp")>=0){
			response.sendRedirect(request.getContextPath() + forward);
		}
		else{
			getServletContext().getRequestDispatcher(forward).forward(request, response);// 跳转到显示页面
		}
	}
}