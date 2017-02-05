package com.zzw.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ApplicationServlet
 */
@WebServlet(name="ApplicationServlet", urlPatterns="/application")
public class ApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out=response.getWriter();
		out.println("<html><head><title>application servlet</title></head><body>");
		Integer count=(Integer) getServletContext().getAttribute("count");
		if(count==null)
			count=new Integer(0);
		out.println("µÚ"+(++count)+"´Î·ÃÎÊ<br></body></html>");
		getServletContext().setAttribute("count", count);
	}

}