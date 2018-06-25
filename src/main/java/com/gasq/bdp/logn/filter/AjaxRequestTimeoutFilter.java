package com.gasq.bdp.logn.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AjaxRequestTimeoutFilter
 */
public class AjaxRequestTimeoutFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 判断session里是否有用户信息
		if (req.getSession().getAttribute("user") == null) {
			// 如果是ajax请求响应头会有，x-requested-with；
			if (req.getHeader("x-requested-with") != null
					&& req.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
				res.setStatus(408);// 表示session timeout
			} else {
				chain.doFilter(req, res);
			}
		} else {
			chain.doFilter(req, res);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
