<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>文件上传</title>
</head>
<body>
<form method="post" action="upload" enctype="multipart/form-data">
	选择文件: <input type="file" id="file" name="file"><br>
	<input type="submit" value="上传"><br>
</form>
</body>
</html>