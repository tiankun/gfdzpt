/*
 * <p> Company: 官房电子科技有限公司</p>
 * <p> Created on 2010-10-29</p>
 * <p> @author ShenChang zou</p>
 */
package com.sysFrams.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.phprpc.PHPRPC_Server;

import com.sysFrams.util.PhprpcJutil;

public class PhprpcAction
	extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PhprpcJutil phprpcJutil = new PhprpcJutil();     
		PHPRPC_Server phprpc_server = new PHPRPC_Server(); 
		phprpc_server.add(phprpcJutil);   
		phprpc_server.start(request, response);  
	}

}
