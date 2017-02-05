<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration" %>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>get method</title>
</head>
<body>
<%--
	Enumeration<String> names=request.getParameterNames();
	while(names.hasMoreElements()) {
		String name=names.nextElement();
		out.println(name+"="+request.getParameter(name)+"<br>");
	}
--%>
<%
	String rawQuery=request.getQueryString();
	out.println("raw query string="+rawQuery+"<br>");
	String decodeQuery=java.net.URLDecoder.decode(rawQuery, "UTF-8");
	out.println("decoder query string="+decodeQuery+"<br>");
%>
</body>
</html>