<%@page import="com.MyshoppingMall.bbs.util.UserCheckFunction"%>
<%@page import="java.io.PrintWriter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%
request.setCharacterEncoding("utf-8");
%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<c:choose>
	<c:when test="${userExist == UserCheckFunction.EXIST_USER }">
		<script>
			alert('이미 존재하는 회원입니다.');
			history.go(-1);
		</script>
	</c:when>
	<c:when test="${userExist == UserCheckFunction.NON_EXIST_USER }">
		<script>
			alert('회원가입이 완료되었습니다.');
			location.href='index.do';
		</script>
	</c:when>
	
</c:choose>


</body>
</html>