<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시스템 오류 !!!</title>
</head>
<body>
<%
	Exception error =(Exception)request.getAttribute("error");
	String err = error.getMessage();
%>
<p> Error:<%=error %></p>
	<p>
		요청을 처리하는 중에 문제가 발생했습니다. <br> 
		잠시 후에 다시 요청 부탁 드립니다.<br> 
		계속 문제가 생긴다면 걍 다른거 하세요.
	</p>
	>
</body>
</html>