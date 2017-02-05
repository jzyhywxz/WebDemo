<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>application jsp page</title>
</head>
<body>
<%
	Integer count=(Integer) application.getAttribute("count");
	if(count==null)
		count=new Integer(0);
	out.println("第"+(++count)+"次访问");
	application.setAttribute("count", count);
%>
</body>
</html>