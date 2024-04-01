<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록</title>
</head>
<body>
	 
	<jsp:include page="/Header.jsp" />

	<h1>게시물 목록</h1>
	<p><a href='add'>신규 게시물</a></p>
	
	<c:forEach var='post' items="${posts }">
	

		${post.post_id },
		${post.post_title },
		${post.email },
		${post.post_content  },
		${post.post_cre_date }
		<a href='update?post_id=${post.post_id }'>[수정]</a>
		<a href='delete?post_id=${post.post_id }'>[삭제]</a><br>
	</c:forEach> 
	
	
	<jsp:include page="/Tail.jsp" />
</body>
</html>
