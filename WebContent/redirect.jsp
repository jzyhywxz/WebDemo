<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>redirect</title>
</head>
<body>
<%
	String user=request.getParameter("user");
	Cookie cookie=new Cookie("user", java.net.URLEncoder.encode(user, "GBK"));
	cookie.setMaxAge(10*60);
	response.addCookie(cookie);
	response.sendRedirect("cookie");
%>
</body>
</html>