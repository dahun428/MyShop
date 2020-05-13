<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>    

    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>MyShoppingMall</title>
<link href="css/bootstrap.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/79413e27e1.js"></script>
<script src="js/check_js_function.js"></script>
</head>
<body>
<%@include file="../View_page_file/headerPage.jsp" %>
<%
request.setCharacterEncoding("utf-8");

if(userId != null){
	PrintWriter writer = response.getWriter();
	writer.println("<script>");
	writer.println("alert('이미 로그인 되어있습니다.');");
	writer.println("location.href = 'index.jsp';");
	writer.println("</script>");
}
%>    

<div class="container">
	<div class="row justify-content-md-center" style="padding-top:20px;">
		<div class="col col-lg-2"></div>
		<div class="col-md-auto">
			<div class="jumbotron">
				<form action="join.do" method="post" name="join_form">
					<h3 style="text-align:center;">회원가입 화면</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디" name="userId" maxlength="20"/>
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호" name="userPassword" maxlength="20"/>
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호 확인" name="userPassword_chk" maxlength="20"/>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이름" name="userName" maxlength="20"/>
					</div>
					<div class="form-group" style="text-align:center;">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-dark active">
								<input type="radio" name="userGender" autocomplete="off" value="남자" checked/>남자
							</label>
							<label class="btn btn-dark">
								<input type="radio" name="userGender" autocomplete="off" value="여자" />여자
							</label>				
						</div>
						<div class="form-group">
							<input type="email" class="form-control" placeholder="이메일" name="userEmail" maxlength="20"/>
						</div>
					</div>
					<input type="button" class="btn btn-dark form-control" value="회원가입" onclick="infoConfirm();"/>
				</form>
			</div>
		</div>
		<div class="col col-lg-2"></div>
	</div>
</div>
<%@include file="../View_page_file/footerPage.jsp" %>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>