package com.zzw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieServlet
 */
@WebServlet(urlPatterns="/cookie")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies=request.getCookies();
		if(cookies!=null && cookies.length>0) {
			response.setContentType("text/html;charset=GBK");
			PrintWriter out=response.getWriter();
			out.println("<html><head><title>cookie</title></head><body>");
			
			for(Cookie cookie : cookies)
				out.println(cookie.getName()+"="+URLDecoder.decode(cookie.getValue(), "GBK")+"<br>");
			
			out.println("</body></html>");
		}
	}

}
