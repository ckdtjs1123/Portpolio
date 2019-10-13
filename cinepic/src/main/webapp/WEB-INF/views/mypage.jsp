<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<title>CINEPIC-리뷰</title>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="resources/css/codingBooster.css">
<!-- Scripts -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
<script src="resources/js/mypage.js"></script>
<style type="text/css">
.panel-login {
	border-color: #ccc;
	-webkit-box-shadow: 0px 2px 3px 0px rgba(0, 0, 0, 0.2);
	-moz-box-shadow: 0px 2px 3px 0px rgba(0, 0, 0, 0.2);
	box-shadow: 0px 2px 3px 0px rgba(0, 0, 0, 0.2);
}

.panel-login>.panel-heading {
	color: #00415d;
	background-color: #fff;
	border-color: #fff;
	text-align: center;
}

.panel-login>.panel-heading a {
	text-decoration: none;
	color: #666;
	font-weight: bold;
	font-size: 15px;
	-webkit-transition: all 0.1s linear;
	-moz-transition: all 0.1s linear;
	transition: all 0.1s linear;
}

.panel-login>.panel-heading a.active {
	color: #029f5b;
	font-size: 18px;
}

.panel-login>.panel-heading hr {
	margin-top: 10px;
	margin-bottom: 0px;
	clear: both;
	border: 0;
	height: 1px;
	background-image: -webkit-linear-gradient(left, rgba(0, 0, 0, 0),
		rgba(0, 0, 0, 0.15), rgba(0, 0, 0, 0));
	background-image: -moz-linear-gradient(left, rgba(0, 0, 0, 0),
		rgba(0, 0, 0, 0.15), rgba(0, 0, 0, 0));
	background-image: -ms-linear-gradient(left, rgba(0, 0, 0, 0),
		rgba(0, 0, 0, 0.15), rgba(0, 0, 0, 0));
	background-image: -o-linear-gradient(left, rgba(0, 0, 0, 0),
		rgba(0, 0, 0, 0.15), rgba(0, 0, 0, 0));
}

.panel-login input[type="text"], .panel-login input[type="email"],
	.panel-login input[type="password"] {
	height: 45px;
	border: 1px solid #ddd;
	font-size: 16px;
	-webkit-transition: all 0.1s linear;
	-moz-transition: all 0.1s linear;
	transition: all 0.1s linear;
}

.panel-login input:hover, .panel-login input:focus {
	outline: none;
	-webkit-box-shadow: none;
	-moz-box-shadow: none;
	box-shadow: none;
	border-color: #ccc;
}

.btn-login {
	background-color: #59B2E0;
	outline: none;
	color: #fff;
	font-size: 14px;
	height: auto;
	font-weight: normal;
	padding: 14px 0;
	text-transform: uppercase;
	border-color: #59B2E6;
}

.btn-login:hover, .btn-login:focus {
	color: #fff;
	background-color: #53A3CD;
	border-color: #53A3CD;
}

.forgot-password {
	text-decoration: underline;
	color: #888;
}

.forgot-password:hover, .forgot-password:focus {
	text-decoration: underline;
	color: #666;
}

.btn-register {
	background-color: #1CB94E;
	outline: none;
	color: #fff;
	font-size: 14px;
	height: auto;
	font-weight: normal;
	padding: 14px 0;
	text-transform: uppercase;
	border-color: #1CB94A;
}

.btn-register:hover, .btn-register:focus {
	color: #fff;
	background-color: #1CA347;
	border-color: #1CA347;
}
</style>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/cinepic/">CINEPIC</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">소개<span
							class="sr-only"></span></a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">접속하기<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<c:if test="${sessionScope.loginId == null }">
								<li><a href="/cinepic/login">로그인</a></li>
								<li><a href="/cinepic/signup">회원가입</a></li>
								<li><a href="/cinepic/find">계정찾기</a></li>
							</c:if>
							<c:if test="${sessionScope.loginId != null }">	
								<li><a href="/cinepic/mypage">${sessionScope.loginId}님 리뷰 </a></li>
								<li><a href="/cinepic/account">계정관리</a> <br></li>
								<li><a href="/cinepic/logout">로그아웃</a> <br></li>
							</c:if>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
			<div class="row">
				<div class="col-xs-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">
								<span class="glyphicon glyphicon-tags"></span>
								&nbsp;&nbsp;<h4>내가 남긴 리뷰</h4>
							</h3>
						</div>
						<input type="hidden" value="${sessionScope.loginId }" id="id">
						<table id="reviewTable">
						</table>
						<div class="col-sm-2 col-sm-offset-10">
							<input type="button" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="삭제" onclick="deleteReview()">
						</div>
					</div>
				</div>
			</div>
		</div>
	
		<footer style="background-color: #000000; color: #ffffff;">
		<div class="container">
			<br>
			<div class="row">
				<div class="col-sm-2" style="text-align: center;">
					<h5>Copyright &copy; 2019</h5>
					<h5>popteam</h5>
				</div>
				<div class="col-sm-4">
					<h4>사이트 소개</h4>
					<p>SC IT MASTER 37기 pop team 입니다. 이 사이트는 자신이 보고싶은 영화의  추천점수와 평가를 통해서 영화관람에 실패하지 않도록 하는 것이 주요 목적입니다.</p>
				</div>
				
				<div class="col-sm-2">
					<h4 style="text-align: center;">
						<span class="glyphicon glyphicon-ok"></span>&nbsp;by popteam
					</h4>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>