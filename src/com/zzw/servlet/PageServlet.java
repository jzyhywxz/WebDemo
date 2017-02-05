package com.zzw.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

/**
 * Servlet implementation class PageServlet
 */
@WebServlet(urlPatterns="/page")
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageContext pageContext=JspFactory.getDefaultFactory().getPageContext(
				this, request, response, null, true, 8192, true);
		pageContext.setAttribute("application", "application", PageContext.APPLICATION_SCOPE);
		pageContext.setAttribute("session", "session", PageContext.SESSION_SCOPE);
		pageContext.setAttribute("request", "request", PageContext.REQUEST_SCOPE);
		pageContext.setAttribute("page", "page");
		
		response.setContentType("text/html;charset=GBK");
		PrintWriter out=response.getWriter();
		out.println("<html><head><title>scope</title></head><body>");
		
		out.println("scope of application="+pageContext.getAttributesScope("application")+"<br>");
		out.println("scope of session="+pageContext.getAttributesScope("session")+"<br>");
		out.println("scope of request="+pageContext.getAttributesScope("request")+"<br>");
		out.println("scope of page="+pageContext.getAttributesScope("page")+"<br>");
		
		out.println("</body></html>");
	}

}
