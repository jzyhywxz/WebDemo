<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://zzw.com/mytaglib" prefix="mytag"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>自定义标签</title>
</head>
<body>
	当前时间<mytag:datetime/><hr>
	
	发送邮件<mytag:email date="<%=new Date()%>" message="hello, client" to="client" from="server"/><hr>
	
	页面片段<mytag:fragment>
		      <jsp:attribute name="fragment">
		          <mytag:datetime/>
		      </jsp:attribute>
		  </mytag:fragment><hr>
		  
<%
	ArrayList<String> list=new ArrayList<String>();
	for(int i=0; i<10; i++)
		list.add("item "+i);
	pageContext.setAttribute("list", list);
%>
	迭代器<br><mytag:iterator item="item" collection="list">
		${pageScope.item}<br>
	</mytag:iterator><hr>
	
	动态属性<mytag:dynamic user="zzw" password="******" action="signin"/><hr>
	
<%
	HashMap<String, Object> map=new HashMap<String, Object>();
	map.put("name", "张三");
	map.put("old", 22);
	map.put("sex", "male");
	map.put("date", new Date());
%>
	TAG迭代器<br>
	<tags:iterator bgcolor="#7f00ff" cellcolor="#ffff00" collection="<%=map%>">
		键值对为：
	</tags:iterator>
	结束迭代&nbsp;${end}
</body>
</html>