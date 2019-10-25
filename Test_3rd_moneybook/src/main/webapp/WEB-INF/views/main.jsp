<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면</title>
<script src='<c:url value="/js/jquery-3.4.1.js" />'></script>
<script>
	function check()
	{
		
	}
</script>
</head>
<body>
	<h1>[일일 가계부]</h1>
	<c:choose>	
		<c:when test="${sessionScope.userid!=null }">
			<h5>${sessionScope.userid}님 환영합니다!</h5>
			<a id="mymy" href="/moneybook/my/moneybook">내 가계부</a> <br>
			<a href="/moneybook/member/logout">로그아웃</a>	
		 </c:when>
		<c:otherwise>
			<a href="/moneybook/member/signupForm">회원가입</a> <br>
			<a href="/moneybook/member/loginForm">로그인</a>
	 	</c:otherwise>
	</c:choose>
</body>
</html>
