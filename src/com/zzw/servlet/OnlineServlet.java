package com.zzw.servlet;

import java.io.IOException;
import java.io.PrintWriter;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.servlet.jsp.JspFactory;
//import javax.servlet.jsp.PageContext;

/**
 * Servlet implementation class OnlineServlet
 */
@WebServlet(name="OnlineServlet", urlPatterns="/online")
public class OnlineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PageContext pageContext=JspFactory.getDefaultFactory().getPageContext(
		//		this, request, response, null, true, 8192, true);
		//HttpSession session=pageContext.getSession();
		HttpSession session=request.getSession(true);
		String user=(String) session.getAttribute("user");
		//if(user==null || user.equals("")) {
		//	RequestDispatcher dispatcher=request.getRequestDispatcher("/user-login.jsp");
		//	dispatcher.forward(request, response);
		//}
		//else {
			response.setContentType("text/html;charset=GBK");
			PrintWriter out=response.getWriter();
			out.println("<html><head><title>online</title></head><body>");
			out.println("Ç×°®µÄ"+user+"£¬»¶Ó­Äú£¡");
			out.println("</body></html>");
		//}
	}

}
