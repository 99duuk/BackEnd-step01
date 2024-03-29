<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 
	저장소
	ServletContext application : App 동작시 생성 ~ App 종료시 소멸, 전역공간
	HttpSession session : 브라우저가 서버에 session이 형성되면 생성되는 공간, 타임아웃 소멸, 로그인 정보
	HttpServletRequest request : 브라우저 -> 서버 요청 전달 되면 생성, 응답하 소멸 
	PageContext pageContext : jsp 페이지 내에서만 

	EL은 객체를 찾을 때 다음 순서로 찾는다 .
	PageContext -> request -> session -> application
	
	현재는 request에 member객체가 저장되어있으므로
	아래 EL은 member를 바로 꺼내 사용한다.
	
	${member.no}에서 no를 '프로퍼티'라고 부른다. 
	이것은 member.getNo()함수를 호출하는데 get은 제거하고 N은 소문자로 바꾸는 규칙이다. 
	결국 ${member.no}는 member.getNo()와 같다.
	
	
	만약 request공간과 session 공간에 동일한 nameList 객체가 존재할 때 
	만약 session에 존재하는 nameList.getCount()를 꺼내고 싶을 때는 ? 
	${nameList.count} => request에 있는 것을 꺼낸다. 
	${sessionScope.nameList.count} => session에 있는 것을 꺼낸다. 
 -->


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
</head>
<body>
	<h1>회원정보</h1>
	<form action='update' method='post'>
		번호: <input type='text' name='no' value='${member.no }' readonly><br>
		이름: <input type='text' name='name' value='${member.name }'><br>
		이메일: <input type='text' name='email' value='${member.email }'><br>
		가입일: ${member.createdDate }<br> <input type='submit' value='저장'>
		<input type='button' value='삭제'
			onclick='location.href="delete?no=${member.no }";'> <input
			type='button' value='취소' onclick='location.href="list"'>
	</form>
</body>
</html>