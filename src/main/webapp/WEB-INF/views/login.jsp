<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/latest/css/bootstrap.min.css" rel="stylesheet">
<style>
body {
	background: #f8f8f8;
}

#layout1 {
	width: 100%;
}

#container1 {
	width: 1280px;
	margin: auto;
	text-align: center;
	margin-top: 300px;
}

#layout2 {
	width: 100%;
}

#container2 {
	width: 1280px;
	margin: auto;
	text-align: center;
	margin-top: 20px;
}

#box2 {
	width: 500px;
	margin: auto;
	text-align: center;
}

#input_id {
	padding: 25px 10px;
}

#input_login {
	margin-top: 20px;
}


</style>

<script>
	
	function fn_login() {
		
		var n_RegExp = /^[가-힣]{2,15}$/; //이름 유효성검사 정규식
		
		var id = document.getElementById('input_id').value;
		
		if (id == "" || id == null) {
			alert("UserName을 입력해주세요!")
			return false;
		}
		
		if(!n_RegExp.test(id)){
	          alert("UserName은 특수문자,영어,숫자는 사용할 수 없습니다. 한글만 입력하여주세요.");
	          return false;
	      }
				
		document.getElementById('login_form').submit();
	}
	
</script>

<title>Simple Chat</title>
</head>

<div id="layout1">
	<div id="container1">
		<h1><i>Simple Chat</i></h1>
		<P>${serverTime}</P>
	</div>
</div>

<div id="layout2">
	<div id="container2">
		<div id="box2">
			<form id="login_form" method="post" action="loginProcess.do">
				<div>
					<input type="text" name="id" id="input_id" class="form-control" placeholder="Username" autofocus>
				</div>
				<div>
					<input type="button" id="input_login" value="접속" class="form-control btn btn-primary" onclick="fn_login();">
				</div>
			</form>
		</div>
	</div>
</div>
</html>
