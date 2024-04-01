<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="spms.vo.Member"%>

<jsp:useBean id="member" scope="session" class="spms.vo.Member" />

<div
	style="background-color: #00008b; color: white; height: 20px; padding: 5px;">
	SPMS(Simple Project Management System)


	<span style="float: right"><a
		style="color: yellow;"
		href="<%=request.getContextPath()%>/post/list">게시물</a> </span>
		
	<span style="float: right"><a
		style="color: red;"
		href="<%=request.getContextPath()%>/member/list">회원 목록</a> </span>	

	<%
if (member.getEmail() != null) {
%>

	<span style="float: right"><%=member.getName()%> <a
		style="color: white;"
		href="<%=request.getContextPath()%>/auth/logout">로그아웃</a> </span>

	<%
	} else {
	%>

	<span style="float: right"><a style="color: pink;" href='<%=request.getContextPath()%>/member/add'>신규 회원</a></span>
	
	<span style="float: right"><a style="color: white;"
		href="<%=request.getContextPath()%>/auth/login">로그인</a> </span>
	<%
	}
	%>
</div>