<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>登录页</title>
</head>
<body>
	<span style="color:red;font-weight:bold">
	<%
		if(request.getAttribute("error")!=null)
			out.println(request.getAttribute("error")+"<br>");
	%>
	</span>
	<form id="login" method="post" action="login">
		用户名：<input type="text" name="user"><br>
		密&nbsp;&nbsp;码：<input type="text" name="password"><br>
		<input type="submit" value="登录">
	</form>
</body>
</html>