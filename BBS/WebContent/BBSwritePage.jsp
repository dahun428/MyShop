<%@page import="com.MyshoppingMall.bbs.vo.Bbs"%>
<%@page import="java.util.List"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

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
	<%@include file="../View_page_file/headerPage.jsp"%>
	<%
	request.setCharacterEncoding("utf-8");
	
	if (userId == null) {
		PrintWriter writer = response.getWriter();
		writer.println("<script>");
		writer.println("alert('로그인이 필요한 페이지입니다.');");
		writer.println("history.go(-1);");
		writer.println("</script>");
	}
	%>

	<%@include file="../View_page_file/navPage.jsp"%>

	<div class="container">
		<div style="margin: 3rem;">
			<form action="BBSwrite.do" method="post" name="BBSwriteForm">
				<table class="table table-stripted"
					style="text-align: center; border: 1px solid #dddddd;">
					<thead>
						<tr>
							<th style="background-color: #eeeeee; text-align: center;">게시판
								글쓰기 양식</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="form-control"
								placeholder="글제목" name="bbsTitle" maxlength="50" /></td>
						</tr>
						<tr>
							<td><textarea class="form-control" placeholder="글내용"
									name="bbsContent" maxlength="2048" style="height: 350px;" /></textarea></td>
						</tr>
					</tbody>
				</table>
				<input type="button" class="btn btn-dark pull-right" value="글쓰기" onclick="bbsInfoConfirm();"/>
			</form>
		</div>
	</div>
	<%@include file="../View_page_file/footerPage.jsp"%>

	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>