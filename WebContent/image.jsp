<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%
	String basePath=request.getScheme()+"://"+
			request.getServerName()+":"+
			request.getServerPort()+
			request.getContextPath()+"/";
%>
<head>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<base href="<%=basePath%>">
<title>image</title>
</head>
<body>
	<img alt="butterfly" src="image">
</body>
</html>