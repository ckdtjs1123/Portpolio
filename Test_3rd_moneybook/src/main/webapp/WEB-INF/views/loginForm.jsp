<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src='<c:url value="/js/jquery-3.4.1.js" />'></script>
<script>
	$(function()
	{
		$('#loginbtn').on('click',login);
	});
	
	function login()
	{
		var id=$('#userid').val();
		var pwd=$('#userpwd').val();
		if(id.length<3||id.length>10)
		{
			alert("아이디는 3~10글자를 입력하세요");
			return false;
		}
		if(pwd.length<3||pwd.length>10)
		{
			alert("비밀번호는 3~10글자를 입력하세요");
			return false;
		}
		$.ajax
		({
			url:'login',
			type:'post',
			data:$('#loginform').serialize(),
			success:function()
			{
				location.href="/moneybook/";
			},
			error:function()
			{
				location.href="/moneybook/";
			}
		})
	}
</script>
<style>
	form
	{
		text-align: center;	
	}
</style>
</head>
<body>
	<h1>[로그인]</h1>
	<form action="/moneybook/member/login" method="post" id="loginform">
		<table>
			<tr>
				<th>
					아이디
				</th>
				<td>
					<input type="text" id="userid" name="userid">
				</td>
			</tr>
			<tr>
				<th>
					비밀번호
				</th>
				<td>
					<input type="password" id="userpwd" name="userpwd">
				</td>
			</tr>
			<tr>
				<th>
					<input type="button" id="loginbtn" value="로그인">
				</th>
				<td>
					<a href="/moneybook/"><input type="button" id="backbtn" value="취소"></a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
