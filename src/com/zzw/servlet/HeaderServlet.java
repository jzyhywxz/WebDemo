package com.zzw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HeaderServlet
 */
@WebServlet(urlPatterns="/header")
public class HeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out=response.getWriter();
		out.println("<html><head><title>header and its parameters</title></head><body>");
		
		out.println("<h2>request header</h2>");
		Enumeration<String> names=request.getHeaderNames();
		while(names.hasMoreElements()) {
			String name=names.nextElement();
			out.println(name+"="+request.getHeader(name)+"<br>");
		}
		
		out.println("<h2>request parameters</h2>");
		request.setCharacterEncoding("gb2312");
		Enumeration<String> params=request.getParameterNames();
		while(params.hasMoreElements()) {
			String param=params.nextElement();
			out.println(param+"="+request.getParameter(param)+"<br>");
		}
		
		out.print("</body></html>");
	}

}
