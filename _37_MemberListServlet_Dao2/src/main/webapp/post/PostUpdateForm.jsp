<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 정보</title>
</head>
<body>
	<h1>게시물 정보</h1>
	<form action='update' method='post'>
		게시물 번호: <input type='text' name='post_id' value='${post.post_id }' readonly><br>
		제목 : <input type='text' name='post_title' value='${post.post_title }'><br>
		이메일: <input type='text' name='email' value='${post.email }'><br>
		내용 : <input type='text' name='post_content' value='${post.post_content }'><br>
		작성일 : ${post.post_cre_date }<br> 
		<input type='submit' value='저장'>
		<input type='button' value='삭제' onclick='location.href="delete?no=${post.post_id }";'> 
		<input type='button' value='취소' onclick='location.href="list"'>
	</form>
</body>
</html>