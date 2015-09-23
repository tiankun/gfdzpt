/*
 * <p> Company: 官房电子科技有限公司</p>
 * <p> Created on 2008-7-30</p>
 * <p> @author ShenChang zou</p>
 */
package com.sysFrams.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * 对web请求内容进行编码，解决乱码问题
 */
public class EncodingFilter implements Filter {

	protected String encoding;

	protected FilterConfig filterConfig;

	protected boolean ignore;

	/**
	 * Request.java 对 HttpServletRequestWrapper 进行扩充, 不影响原来的功能并能提供所有的
	 * HttpServletRequest 接口中的功能. 它可以统一的对默认设置下的中文问题进行解决而只需要用新的 Request 对象替换页面中的
	 * request 对象即可.
	 */

	class Request extends HttpServletRequestWrapper {

		public Request(HttpServletRequest request) {
			super(request);
		}

		/**
		 * 转换由表单读取的数据的内码. 从 ISO 字符转到指定编码.
		 */
		public String toChi(String input) {
			try {
				byte[] bytes = input.getBytes("ISO8859-1");
				return new String(bytes, encoding==null?"GBK":encoding);
			} catch (Exception ex) {
			}
			return null;
		}

		/**
		 * Return the HttpServletRequest holded by this object.
		 */
		private HttpServletRequest getHttpServletRequest() {
			return (HttpServletRequest) super.getRequest();
		}

		/**
		 * 读取参数 -- 修正了中文问题.
		 */
		public String getParameter(String name) {
			return toChi(getHttpServletRequest().getParameter(name));
		}

		/**
		 * 读取参数列表 - 修正了中文问题.
		 */
		public String[] getParameterValues(String name) {
			String values[] = getHttpServletRequest().getParameterValues(name);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					values[i] = toChi(values[i]);
				}
			}
			return values;
		}
	}

	public EncodingFilter() {
		encoding = null;
		filterConfig = null;
		ignore = true;
	}

	public void destroy() {
		encoding = null;
		filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		//httpResponse.setHeader("Cache-Control", "no-cache"); //设置次参数将没有页面缓存
		httpResponse.setHeader("Pragma", "no-cache");
		httpResponse.setDateHeader("Expires", 0);
		if (ignore || httpRequest.getCharacterEncoding() == null) {
			if (encoding != null) {
				if (httpRequest.getMethod().equals("POST")) {
					httpRequest.setCharacterEncoding(encoding);
				} else {
					httpRequest = new Request(httpRequest);
				}
				httpResponse.setCharacterEncoding(encoding);
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterconfig) throws ServletException {
		filterConfig = filterconfig;
		encoding = filterconfig.getInitParameter("encoding");
		String ignoreConfig = filterconfig.getInitParameter("ignore");
		if (ignoreConfig == null) {
			ignore = true;
		} else if (ignoreConfig.equalsIgnoreCase("true")) {
			ignore = true;
		} else if (ignoreConfig.equalsIgnoreCase("yes")) {
			ignore = true;
		} else {
			ignore = false;
		}
	}	
}