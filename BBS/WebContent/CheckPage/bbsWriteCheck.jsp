<%@page import="java.io.PrintWriter"%>
<%@page import="com.MyshoppingMall.bbs.util.UserCheckFunction"%>
<%@page import="com.MyshoppingMall.bbs.dao.UserDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

  
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="../View_page_file/headerPage.jsp"%>
<c:choose>
	<c:when test="${isSuccess == BBSCheckFunction.BBS_WRITE_SUCCESS }">
		<script>
			alert('글이 성공적으로 작성되었습니다.');
			location.href ="BBSviewPage.do";
		</script>
	</c:when>
	<c:when test="${isSuccess == BBSCheckFunction.BBS_DATABASE_ERROR }">
		<script>
			alert('유효하지 않은 값입니다. 다시 시도해주세요.');
			history.go(-1);
		</script>
	</c:when>
</c:choose>


</body>
</html>