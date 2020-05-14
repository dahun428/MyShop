<%@page import="com.MyshoppingMall.bbs.util.BBSCheckFunction"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.MyshoppingMall.bbs.util.UserCheckFunction"%>
<%@page import="com.MyshoppingMall.bbs.dao.UserDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<%
  int isSuccess = 0;
	if(request.getParameter("isSuccess") != null){
		isSuccess =	Integer.parseInt(request.getParameter("isSuccess").trim());
	}
	if(isSuccess == BBSCheckFunction.BBS_NO_ARTICLE){
		PrintWriter writer = response.getWriter();
		writer.println("<script>");
		writer.println("alert('해당 글이 존재하지 않습니다.');");
		writer.println("location.href='BBSmainPage.do';");
		writer.println("</script>");
	} else if(isSuccess == BBSCheckFunction.BBS_DATABASE_ERROR) {
		PrintWriter writer = response.getWriter();
		writer.println("<script>");
		writer.println("alert('유효하지 않은 값입니다.');");
		writer.println("location.href='BBSmainPage.do';");
		writer.println("</script>");
	} else if (isSuccess == BBSCheckFunction.BBS_WRITE_FAIL){
		PrintWriter writer = response.getWriter();
		writer.println("<script>");
		writer.println("alert('해당 시도를 실패하였습니다.');");
		writer.println("location.href='BBSmainPage.do';");
		writer.println("</script>");
	} else if (isSuccess == BBSCheckFunction.BBS_WRITE_SUCCESS){
		PrintWriter writer = response.getWriter();
		writer.println("<script>");
		writer.println("alert('완료되었습니다.');");
		writer.println("</script>");
	}
  %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	
</body>
</html>