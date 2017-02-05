<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>欢迎页</title>
</head>
<body>
	<%
		String name=(String) session.getAttribute("name");
		boolean online=(Boolean) session.getAttribute("online");
		String date=(String) session.getAttribute("date");
		
		out.print("亲爱的"+name+"，欢迎您！");
		
		if(online)
			out.println("您的账号于"+date+"在别处登录，如果不是您所为，建议您修改密码。");
		else
			out.println("上次登录时间为"+date+"。");
	%>
</body>
</html>