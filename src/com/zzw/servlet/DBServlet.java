package com.zzw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBServlet
 */
@WebServlet(
		urlPatterns = { "/user" }, 
		initParams = { 
				@WebInitParam(name = "driver", value = "com.mysql.jdbc.Driver"), 
				@WebInitParam(name = "url", value = "jdbc:mysql://localhost:3306/cool_dictionary"), 
				@WebInitParam(name = "user", value = "root"), 
				@WebInitParam(name = "password", value = "262623"), 
				@WebInitParam(name = "useUnicode", value = "true"), 
				@WebInitParam(name = "characterEncoding", value = "UTF-8"), 
				@WebInitParam(name = "useSSL", value = "true")
		})
public class DBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Properties prop;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		prop=new Properties();
		Enumeration<String> names= config.getInitParameterNames();
		while(names.hasMoreElements()) {
			String name=names.nextElement();
			prop.put(name, config.getInitParameter(name));
		}
		
		try {
			Class.forName(prop.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out=response.getWriter();
		out.println("<html><head><title>user info</title></head><body>");
		out.println("<table border=\"1\" width=\"500\">");
		
		String url=prop.getProperty("url");
		try(Connection conn=DriverManager.getConnection(url, prop)) {
			Statement state=conn.createStatement();
			ResultSet result=state.executeQuery("select * from user order by id;");
			while(result.next()) {
				out.println("<tr>");
				out.println("<td>"+result.getInt(1)+"</td>");
				out.println("<td>"+result.getString(2)+"</td>");
				out.println("<td>"+result.getString(3)+"</td>");
				out.println("<td>"+result.getBoolean(4)+"</td>");
				out.println("<td>"+result.getString(5)+"</td>");
				out.println("</tr>");
			}
			out.println("</table></body></html>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
