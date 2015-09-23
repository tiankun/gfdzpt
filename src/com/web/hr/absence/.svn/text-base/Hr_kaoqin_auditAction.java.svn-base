package com.web.hr.absence;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import com.sysFrams.db.DataBaseControl;
import com.sysFrams.web.BaseAction;
import com.sysFrams.db.Page;
import com.sysFrams.util.codeTableUtil;

public class Hr_kaoqin_auditAction extends BaseAction {
	DataBaseControl dataBaseControl = DataBaseControl.getInstance();

	public void displaymonth(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String sdate = fmt.format(date);

		String nian = sdate.substring(0, 4);
		String yue = sdate.substring(5, 7);
		// 当月
		Map map1 = dataBaseControl
				.getOneResultSet(
						"select to_char(add_months(to_date(?,'yyyy-MM'),1),'yyyy-MM') kqdate from dual",
						new Object[] { nian + "-" + yue });
		// 前一月
		Map map2 = dataBaseControl
				.getOneResultSet(
						"select to_char(add_months(to_date(?,'yyyy-MM'),0),'yyyy-MM') kqdate from dual",
						new Object[] { nian + "-" + yue });
		// 前二月
		Map map3 = dataBaseControl
				.getOneResultSet(
						"select to_char(add_months(to_date(?,'yyyy-MM'),-1),'yyyy-MM') kqdate from dual",
						new Object[] { nian + "-" + yue });
		// 前三月
		Map map6 = dataBaseControl
				.getOneResultSet(
						"select to_char(add_months(to_date(?,'yyyy-MM'),-2),'yyyy-MM') kqdate from dual",
						new Object[] { nian + "-" + yue });

		// 下月
		Map map4 = dataBaseControl
				.getOneResultSet(
						"select to_char(add_months(to_date(?,'yyyy-MM'),2),'yyyy-MM') kqdate from dual",
						new Object[] { nian + "-" + yue });

		List list = new ArrayList();
		list.add(map6);
		list.add(map3);
		list.add(map2);
		list.add(map1);
		list.add(map4);

		request.setAttribute("datelist", list);
	}

	

	private Page searchdept(Map map, int pageNo, int pageSize) throws Exception {

		String sql = "select * from hr_base_info t  where 1=1 and t.duty_id !=2 "
				+ "/~ and t.dept_id={dept_id} ~/" + " order by t.id";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql, map);
		Page page = dataBaseControl.getPageResultSet(xsql.getXsql(), xsql
				.getAcceptedFilters().values().toArray(), pageNo, pageSize);
		return page;
	}

	public String audit(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.displaymonth(request, response);

		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String sdate = fmt.format(date);

		String snian = sdate.substring(0, 4);
		String yue = sdate.substring(5, 7);

		Map map2 = dataBaseControl
				.getOneResultSet(
						"select to_char(add_months(to_date(?,'yyyy-MM'),-1),'yyyy-MM') kqdate from dual",
						new Object[] { snian + "-" + yue });

		String kqdate = request.getParameter("kqdate") == null ? map2.get(
				"kqdate").toString() : request.getParameter("kqdate");
		String dept_id = request.getParameter("dept_id");

		request.setAttribute("kqdate", kqdate);
		request.setAttribute("dept_id", dept_id);

		Map auditmap = dataBaseControl
				.getOneResultSet(
						"select * from hr_kaoqin_audit t where t.dept_id = ? and t.kaoqin_date = ?",
						new Object[] { dept_id, kqdate });

		request.setAttribute("audit", auditmap.get("state"));
		String nian = kqdate.substring(0, 4);
		int year = Integer.parseInt(nian);
		int days = 0;
		String month = kqdate.substring(5, 7);

		if (month.equals("1") || month.equals("01") || month.equals("3")
				|| month.equals("03") || month.equals("5")
				|| month.equals("05") || month.equals("7")
				|| month.equals("07") || month.equals("8")
				|| month.equals("08") || month.equals("10")
				|| month.equals("12")) {
			days = 31;
		}

		else if (month.equals("4") || month.equals("04") || month.equals("6")
				|| month.equals("06") || month.equals("9")
				|| month.equals("09") || month.equals("11")) {
			days = 30;
		} else {
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)// 闰年
			{
				days = 29;
			} else {
				days = 28;
			}
		}

		List reslist = new ArrayList();
		String sql = "select * from hr_base_info t where t.duty_id =1 and t.hr_type in('2','3')  /~ and t.dept_id={dept_id} ~/  order by t.dept_id ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,
				getParameterMap(request));
		List plist = dataBaseControl.getOutResultSet(xsql.getXsql(), xsql
				.getAcceptedFilters().values().toArray());

		for (int j = 0; j < plist.size(); j++) {
			Map pmap = (Map) plist.toArray()[j];
			Map resmap = new HashMap();
			Map tmap = new HashMap();
			resmap.put("name", pmap.get("name"));
			for (int i = 1; i <= days; i++) {
				Map vacamap = dataBaseControl
						.getOneResultSet(
								"select * from hr_vacation_arrangement t where t.vacation_date = to_date(?,'yyyy-MM-dd')",
								new Object[] { kqdate + "-" + i });
				if (vacamap != null && !vacamap.isEmpty()) {
					continue;
				}

				tmap = dataBaseControl
						.getOneResultSet(
								"select decode(t.am,'出勤','/','带薪假','¤','出差','√','病假','○','事假','+','旷工','×','培训','◇','其他','其','迟到','-','/')am,t.amnote, decode(t.pm,'出勤','/','带薪假','¤','出差','√','病假','○','事假','+','旷工','×','培训','◇','其他','其','迟到','-','/')pm,t.pmnote from hr_kaoqin t where t.p_id = ? and t.attend_date = to_date(?,'yyyy-MM-dd')",
								new Object[] { pmap.get("id"), kqdate + "-" + i });
				if (tmap == null || tmap.isEmpty()) // 没有考勤记录，表示出勤
				{
					resmap.put("s" + i, "/");
					resmap.put("x" + i, "/");
				} else {

					resmap.put("s" + i, tmap.get("am"));
					resmap.put("x" + i, tmap.get("pm"));
					resmap.put("snote" + i, tmap.get("amnote"));
					resmap.put("xnote" + i, tmap.get("pmnote"));
				}
			}
			reslist.add(resmap);

		}

		request.setAttribute("reslist", reslist);
		request.setAttribute("kqdate", kqdate);
		request.setAttribute("nian", nian);
		request.setAttribute("yue", month);
		request.setAttribute("days", days);

		request.setAttribute("imp", "yes");

		return "/hr/kaoqin/hr_kaoqinbaobiao.jsp";
	}

	public String imp(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.displaymonth(request, response);

		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String sdate = fmt.format(date);

		String snian = sdate.substring(0, 4);
		String yue = sdate.substring(5, 7);

		Map map2 = dataBaseControl
				.getOneResultSet(
						"select to_char(add_months(to_date(?,'yyyy-MM'),-1),'yyyy-MM') kqdate from dual",
						new Object[] { snian + "-" + yue });

		String kqdate = request.getParameter("kqdate") == null ? map2.get(
				"kqdate").toString() : request.getParameter("kqdate");
		String dept_id = request.getParameter("dept_id");

		request.setAttribute("kqdate", kqdate);
		request.setAttribute("dept_id", dept_id);

		Map auditmap = dataBaseControl
				.getOneResultSet(
						"select * from hr_kaoqin_audit t where t.dept_id = ? and t.kaoqin_date = ?",
						new Object[] { dept_id, kqdate });

		request.setAttribute("audit", auditmap.get("state"));
		String nian = kqdate.substring(0, 4);
		int year = Integer.parseInt(nian);
		int days = 0;
		String month = kqdate.substring(5, 7);

		if (month.equals("1") || month.equals("01") || month.equals("3")
				|| month.equals("03") || month.equals("5")
				|| month.equals("05") || month.equals("7")
				|| month.equals("07") || month.equals("8")
				|| month.equals("08") || month.equals("10")
				|| month.equals("12")) {
			days = 31;
		}

		else if (month.equals("4") || month.equals("04") || month.equals("6")
				|| month.equals("06") || month.equals("9")
				|| month.equals("09") || month.equals("11")) {
			days = 30;
		} else {
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)// 闰年
			{
				days = 29;
			} else {
				days = 28;
			}
		}

		List reslist = new ArrayList();
		String sql = "select * from hr_base_info t where t.duty_id =1  /~ and t.dept_id={dept_id} ~/  order by t.dept_id ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql,
				getParameterMap(request));
		List plist = dataBaseControl.getOutResultSet(xsql.getXsql(), xsql
				.getAcceptedFilters().values().toArray());

		for (int j = 0; j < plist.size(); j++) {
			Map pmap = (Map) plist.toArray()[j];
			Map resmap = new HashMap();

			for (int i = 1; i <= days; i++) {
				Map tmap = new HashMap();
				Map vacamap = dataBaseControl
						.getOneResultSet(
								"select * from hr_vacation_arrangement t where t.vacation_date = to_date(?,'yyyy-MM-dd')",
								new Object[] { kqdate + "-" + i });
				if (vacamap != null && !vacamap.isEmpty()) {
					continue;
				}

				tmap = dataBaseControl
						.getOneResultSet(
								"select decode(t.am,'出勤','/','带薪假','¤','出差','√','病假','○','事假','+','旷工','×','培训','◇','其他','其','迟到','-','/')am,t.amnote, decode(t.pm,'出勤','/','带薪假','¤','出差','√','病假','○','事假','+','旷工','×','培训','◇','其他','其','迟到','-','/')pm,t.pmnote,m.branchname from hr_kaoqin t,mrbranch m where t.dept_id = m.id and t.p_id = ? and t.attend_date = to_date(?,'yyyy-MM-dd')",
								new Object[] { pmap.get("id"), kqdate + "-" + i });
				if (tmap == null || tmap.isEmpty()) // 没有考勤记录，表示出勤
				{
				} else {

					resmap.put("name", pmap.get("name"));
					resmap.put("s" + i, tmap.get("am"));
					resmap.put("x" + i, tmap.get("pm"));
					resmap.put("snote" + i, tmap.get("amnote"));
					resmap.put("xnote" + i, tmap.get("pmnote"));
					
				}
			}
			if(!resmap.isEmpty())
			{
				reslist.add(resmap);
			}
			

		}

		//request.setAttribute("list1", reslist);

		String dirpath = request.getSession().getServletContext().getRealPath(
				"/")
				+ File.separatorChar
				+ "upFile"
				+ File.separatorChar
				+ "hr" + File.separatorChar + "kaoqinimp";

		File dir = new File(dirpath);
		if (!dir.exists())
			dir.mkdirs();

		Date d = new Date();
		long longtime = d.getTime();
		String dateStr = "" + longtime;

		// 生成excel报表
		InputStream in = null;
		javax.servlet.ServletOutputStream op = response.getOutputStream();

		try {
			XLSTransformer transformer = new XLSTransformer(); // 声明一个XLSTransformer对象
			// 获取java项目编译后根路径
			URL url = this.getClass().getClassLoader().getResource("");
			String xlsTemplateFileName = url.getPath()
					+ "com/web/excel/kaoqinimp.xls";// 得到模版excel
			String xlsFileName = request.getSession().getServletContext()
					.getRealPath("/")
					+ File.separatorChar
					+ "upFile"
					+ File.separatorChar
					+ "hr"
					+ File.separatorChar
					+ "kaoqinimp"
					+ File.separatorChar + dateStr + ".xls";
			Map beans = new HashMap();

			beans.put("list1", reslist);
			

			transformer.transformXLS(xlsTemplateFileName, beans, xlsFileName);
			in = new FileInputStream(new File(request.getSession()
					.getServletContext().getRealPath("/")
					+ File.separatorChar
					+ "upFile"
					+ File.separatorChar
					+ "hr"
					+ File.separatorChar
					+ "kaoqinimp"
					+ File.separatorChar + dateStr + ".xls"));
			int length = 0;
			byte[] buf = new byte[4096];
			String title = new String(("考勤导出.xls").getBytes("GBK"), "ISO8859-1");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ title);// attachment（下载）,inline（在线打开）
			while ((length = in.read(buf)) != -1) {
				op.write(buf, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (op != null) {
					op.flush();
				}
				if (in != null)
					in.close();
			} catch (Exception e) {
			}
		}

		return null;
	}

	public String searchList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String sdate = fmt.format(date);

		String snian = sdate.substring(0, 4);
		String yue = sdate.substring(5, 7);

		Map map2 = dataBaseControl
				.getOneResultSet(
						"select to_char(add_months(to_date(?,'yyyy-MM'),-1),'yyyy-MM') kqdate from dual",
						new Object[] { snian + "-" + yue });
		String kqdate = request.getParameter("kqdate") == null ? map2.get(
				"kqdate").toString() : request.getParameter("kqdate");

		request.setAttribute("kqdate", kqdate);

		this.displaymonth(request, response);
		String page_no = (request.getParameter("pageno") == null || request
				.getParameter("pageno").equals("")) ? "1" : request
				.getParameter("pageno");
		int pageNo = (new Integer(page_no)).intValue();
		int pageSize = 20;

		Map searchMap = getParameterMap(request);
		searchMap.put("kqdate", kqdate);

		Page page = search(searchMap, pageNo, pageSize);

		request.setAttribute("searchMap", searchMap);

		request.setAttribute("record", page.getThisPageElements());
		request.setAttribute("page", page);
		buildDDL(request);
		if (request.getParameter("pageType") != null
				&& !request.getParameter("pageType").equals("")
				&& request.getParameter("pageType").equals("select"))
			return "/hr/absence/hr_kaoqinSelect.jsp";
		return "/hr/kaoqin/hr_hr_kaoqinList.jsp";
	}

	public String searchById(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Page page = search(getParameterMap(request), 1, 1);

		request.setAttribute("record", ((ArrayList) page.getThisPageElements())
				.get(0));
		request.setAttribute("btnDisplay", "display:none");
		buildDDL(request);
		return "/hr/absence/hr_kaoqin_auditDtl.jsp";
	}

	private Page search(Map searchMap, int pageNo, int pageSize)
			throws Exception {
		// Map searchMap=getParameterMap(request);
		String sql = "select t.*,r.name,m.branchname from Hr_kaoqin t,hr_base_info r,mrbranch m where r.id = t.p_id and t.dept_id = m.id and 1=1 "
				+ "/~ and t.id={id} ~/"
				+ "/~ and t.dept_id={dept_id} ~/"
				+ "/~ and t.p_id={p_id} ~/ "
				+ "/~ and t.attend_date>=to_date({attend_date1},'yyyy-MM-dd')       ~/ "
				+ "/~ and t.attend_date<=to_date({attend_date2},'yyyy-MM-dd')       ~/ "
				+ " order by t.id desc ";
		XsqlFilterResult xsql = new XsqlBuilder().generateSql(sql, searchMap);
		Page page = dataBaseControl.getPageResultSet(xsql.getXsql(), xsql
				.getAcceptedFilters().values().toArray(), pageNo, pageSize);
		return page;
	}

	public String add(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if (IsPostBack != null && !IsPostBack.equals("")
				&& IsPostBack.equals("1")) {
			int i = dataBaseControl.insertByBean(getMapObject(request));
			if (i == 1)
				request.setAttribute("operationSign", "closeDG_refreshW();");
		}
		buildDDL(request);
		request.setAttribute("editMod", "add");
		request.setAttribute("IsPostBack", "1");
		return "/hr/absence/hr_kaoqin_auditDtl.jsp";
	}

	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String IsPostBack = request.getParameter("IsPostBack");
		if (IsPostBack != null && !IsPostBack.equals("")
				&& IsPostBack.equals("1")) {
			int i = dataBaseControl.updateByBean(getMapObject(request));
			if (i == 1)
				request.setAttribute("operationSign", "closeDG_refreshW();");
		} else {
			Page page = search(request.getParameterMap(), 1, 1);
			ArrayList<Map> record = (ArrayList) page.getThisPageElements();
			request.setAttribute("record", record.get(0));
		}

		request.setAttribute("editMod", "edit");
		request.setAttribute("IsPostBack", "1");
		buildDDL(request);
		return "/hr/absence/hr_kaoqin_auditDtl.jsp";
	}

	public String delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StringBuffer str = new StringBuffer();
		String[] ids = request.getParameterValues("id");
		for (String e : ids) {
			str.append("" + e + ",");
		}
		String s_id = str.substring(0, str.length() - 1);
		dataBaseControl.executeSql(
				"DELETE FROM Hr_kaoqin_audit WHERE ID in (?)",
				new Object[] { s_id });

		return "/hr/absence/Hr_kaoqin_audit!searchList.do";
	}

	private void buildDDL(HttpServletRequest request) throws Exception {

	}

}