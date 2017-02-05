package com.zzw.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet(urlPatterns="/user-login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user=request.getParameter("user");
		String password=request.getParameter("password");
		
		if(user!=null && password!=null && user.equals("admin") && password.equals("admin")) {
			request.getSession(true).setAttribute("user", user);
			RequestDispatcher dispatcher=request.getRequestDispatcher("/online");
			dispatcher.forward(request, response);
		}
		else {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/user-login.jsp");
			dispatcher.forward(request, response);
		}
	}

}
