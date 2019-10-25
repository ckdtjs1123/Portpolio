<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src='<c:url value="/js/jquery-3.4.1.js" />'></script>
<script>
	$(function()
	{
		$('#signupbtn').on('click',signup);
		$('#backbtn').on('click',backmain);
		
	})
		
	function signup()
	{
		var id = $('#userid').val();
		var pwd = $('#userpwd').val();
		var rpwd = $('#chkuserpwd').val();
		
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
		if(pwd!=rpwd)
		{
			alert("비밀번호가 일치하지 않습니다");
			return false;
		}
		$.ajax
		({
			url:'signup',
			type:'post',
			data:$('#signupform').serialize(),
			success:function()
			{
				location.href="/moneybook/member/loginForm";
			},
			error:function()
			{
				alert("회원가입에 실패하였습니다");
				location.href="/moneybook/member/signupForm";
			},
		})
		
		
		/* $('#signupform').submit(); */
		
	}
	
	function backmain()
	{
		href="/";
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
	<h1>[회원가입]</h1>
	<form id="signupform" action="/moneybook/member/signup" method="post">
		<table>
			<tr>
				<th>
					아이디
				</th>
				<td>
					<input type="text" name="userid" id="userid" placeholder="3~10자 사이">
				</td>
			</tr>
			<tr>
				<th>
					비밀번호
				</th>
				<td>
					<input type="password" name="userpwd" id="userpwd" placeholder="3~10자 사이">
				</td>
			</tr>
			<tr>
				<th>
					비밀번호 확인
				</th>
				<td>
					<input type="password" id="chkuserpwd">
				</td>
			</tr>
			<tr>
				<td>
					<input type="button" value="가입" id="signupbtn" >
				</td>
				<td>
					<a href="/moneybook/"><input type="button" value="취소" id="backbtn"></a>
				</td>		
		</table>
	</form>
</body>
</html>
