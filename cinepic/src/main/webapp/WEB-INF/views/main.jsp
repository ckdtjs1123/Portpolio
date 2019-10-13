<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="2">
<title>CINEPIC</title>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="resources/css/codingBooster.css">
<style type="text/css">
.jumbotron {
	background-image: url('resources/images/joker.PNG');
	background-size: auto;
	width: 1140px;
	height: 600px;
	text-shadow: black 0.2em 0.2em 0.2em;
	color: white;
	-webkit-background-size: 100% 100%;
	-moz-background-size: 100% 100%;
	-o-background-size: 100% 100%;
}
</style>
<script src="resources/js/jquery-3.4.1.js"></script>
<script src="resources/js/bootstrap.js"></script>
<script>
	window.onload = function () {
		$.ajax({
			url : "/cinepic/search",
			type : "post",
			data : {
				"title" : "",
				"director" : "",
				"actor" : ""
				},
			success : function(result) {
				$(result).each( function(index,item) {
					searchTable.innerHTML
					+=	"<tr>"
					+		"<td><a href='/cinepic/info?mno=" + item.mno + "'><img src="+ item.poster + ">" + item.title + "</a></td>"
					+	"</tr>"
				})			
			},
			error : function() {}
		})
	}

	function search() {
		
		var keyword = document.getElementById("keyword")
		
		if(keyword.value==='') {
			alert("검색어를 입력해주세요");
			return;
		}
		
		$.ajax({
			url : "/cinepic/search",
			type : "post",
			data : {
				"title" : keyword.value,
				"director" : keyword.value,
				"actor" : keyword.value
				},
			success : function(result) {
				searchTable.innerHTML=""
				$(result).each( function(index,item) {
					searchTable.innerHTML
					+=	"<tr>"
					+		"<td><a href='/cinepic/info?mno=" + item.mno + "'>" + item.title + "</a></td>"
					+	"</tr>"
				})			
			},
			error : function() {}
		})
	}
</script>
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
				<form class="navbar-form navbar-left">
					<div class="form-group">
						<input type="text" id="keyword" placeholder="제목/감독/배우 중 하나를 입력해주세요">
					</div>
					<input type="button" value="검색" onclick="search()">
				</form>
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
		<div class="jumbotron">
			<h1 class="text-center">Welcome to CINEPIC</h1>
			<p class="text-center">CINEPIC에서 좋은 영화 많이 추천받고 가세요!!!!</p>
			
		</div>
	</div>
	<hr>
	<div class="container">
			<div class="row">
				<div class="col-xs-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">
								<span class="glyphicon glyphicon-tags"></span>
								&nbsp;&nbsp;최신 영화 추천
							</h3>
						</div>
						
						<table id="searchTable" class="table">
						</table>
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