<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.zzw.util.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>user info</title>
</head>
<body>
<%
	//Properties prop=new Properties();
	//Enumeration<String> names=application.getInitParameterNames();
	//while(names.hasMoreElements()) {
	//	String name=names.nextElement();
	//	prop.put(name, application.getInitParameter(name));
	//}
	
	//if(prop.getProperty("name")!=null)
	//	out.println("application can obtain name<br>");
	//else
	//	out.println("application cannot obtain name<br>");

	String name=config.getInitParameter("name");
	
	DBCPManager manager=(DBCPManager) application.getAttribute("manager");
	if(manager==null)
		System.out.println("manager==null");
	Connection conn=manager.connect();
	//Class.forName(prop.getProperty("driver"));
	//config.getServletContext();
	//Connection conn=DriverManager.getConnection(prop.getProperty("url"), prop);
	Statement state=conn.createStatement();
	ResultSet result=state.executeQuery("select * from user where name='"+name+"';");
%>
	<table border="1" width="500">
<%
	while(result.next()) {
%>
		<tr>
			<td><%=result.getInt(1)%></td>
			<td><%=result.getString(2)%></td>
			<td><%=result.getString(3)%></td>
			<td><%=result.getBoolean(4)%></td>
			<td><%=result.getString(5)%></td>
		</tr>
<%
	}
	manager.disconnect(conn);
%>
	</table>
</body>
</html>