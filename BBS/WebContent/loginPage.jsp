<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>    
<%
request.setCharacterEncoding("utf-8");

String userId = null;

if(session.getAttribute("userId") != null){
	userId = (String) session.getAttribute("userId");	
}
if(userId != null){
	PrintWriter writer = response.getWriter();
	writer.println("<script>");
	writer.println("alert('이미 로그인 되어있습니다.');");
	writer.println("location.href = 'index.jsp';");
	writer.println("</script>");
}
%>    

    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>MyShoppingMall</title>
<link href="css/bootstrap.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/79413e27e1.js"></script>
</head>
<body>
<%@include file="View_page_file/headerPage.jsp" %>

<div class="container">
	<div class="row justify-content-md-center" style="padding-top:20px;">
		<div class="col col-lg-2"></div>
		<div class="col-md-auto">
			<div class="jumbotron">
				<form action="login.do" method="post">
					<h3 style="text-align:center">로그인화면</h3>
					<div class="form-group">
						<input type="text" class="form-control"  placeholder="아이디" name="userId" maxlength="20"/>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="비밀번호" name="userPassword" maxlength="20"/>
					</div>
						<input type="submit" class="btn btn-dark form-control" value="로그인"/>
				
				</form>
			</div>
		</div>
		<div class="col col-lg-2"></div>
	</div>
</div>

<%@include file="View_page_file/footerPage.jsp" %>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>