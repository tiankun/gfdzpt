package com.web.sysAdmin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sysFrams.util.CnToSpell;

public class CnToSpAction {
	public void getFirst(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String cnString = request.getParameter("cnString")==null?"":request.getParameter("cnString");
		
		String first = CnToSpell.getFirstLetter(cnString);
		PrintWriter writer =response.getWriter();
		writer.write(first);
		writer.flush();
		writer.close();
	}
}
