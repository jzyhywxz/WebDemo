package com.zzw.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zzw.util.LoginHelper;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="LoginServlet", urlPatterns="/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("GBK");
		String error=null;
		RequestDispatcher dispatcher;

		HttpSession session=request.getSession(true);
		String user=request.getParameter("user");
		String password=request.getParameter("password");
		try {
			switch(LoginHelper.login(session, user, password)) {
			case LOG_IN_SUCCESS:
				dispatcher=request.getRequestDispatcher("/welcome.jsp");
				dispatcher.forward(request, response);
				break;
			case PASSWORD_ERROR:
				error="密码错误！";
				break;
			case USER_NOT_EXIST:
				error="用户不存在！";
				break;
			case USER_IS_EMPTY:
				error="用户名不能为空！";
				break;
			case PASSWORD_IS_EMPTY:
				error="密码不能为空！";
				break;
			default: break;
			}
		} catch (SQLException e) {
			error=e.getMessage();
		} finally {
			if(error!=null) {
				request.setAttribute("error", error);
				dispatcher=request.getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
