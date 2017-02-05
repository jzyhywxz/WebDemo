package com.zzw.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class UserLoginFilter
 */
@WebFilter(
		initParams = { 
				@WebInitParam(name = "encoding", value = "GBK"), 
				@WebInitParam(name = "loginPage", value = "/user-login.jsp")
		}, 
		//servletNames = { "OnlineServlet" }, 
		urlPatterns = { "/online" })
public class UserLoginFilter implements Filter {
	private String encoding;
	private String loginPage;

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		encoding=fConfig.getInitParameter("encoding");
		loginPage=fConfig.getInitParameter("loginPage");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		request.setCharacterEncoding(encoding);
		HttpSession session=((HttpServletRequest)request).getSession(true);
		String user=(String) session.getAttribute("user");
		if(user==null || user.equals("")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher(loginPage);
			dispatcher.forward(request, response);
		}
		else
			// pass the request along the filter chain
			chain.doFilter(request, response);
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
