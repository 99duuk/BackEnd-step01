<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 등록</title>
</head>
<body>

	<jsp:include page="/Header.jsp" />

	<h1>게시물 등록</h1>
	<form action='add' method='post'>
		제목 : <input type='text' name='post_title'><br> 
		작성자 : <input type='text' name='email' value='${member.email }'><br>
		내용 : <input type='text' name='post_content'><br> 
		<input type='submit' value='추가'>
		<input type='reset' value='지우기'> 
		<input type='button' value='취소' onclick='location.href="list"'>
	</form>

	<jsp:include page="/Tail.jsp" />
</body>
</html>
