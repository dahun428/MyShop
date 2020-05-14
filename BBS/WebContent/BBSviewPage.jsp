<%@page import="com.MyshoppingMall.bbs.util.BBSCheckFunction"%>
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
		writer.println("location.href='loginPage.jsp';");
		writer.println("</script>");
	}
	
	%>
	<%@include file="../CheckPage/bbsViewCheck.jsp"%>
	<%@include file="../View_page_file/navPage.jsp"%>
	
	<div class="container">
		<div style="margin: 3rem;">

			<table class="table table-stripted"
				style="text-align: center; border: 1px solid #dddddd;">
				<thead>
					<tr>
						<th colspan="4" style="background-color: #eeeeee; text-align: center;">게시판 글보기</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width:20%; background-color: #eeeeee;">제목</td>
						<td><c:out value="${bbs.bbsTitle }"/></td>
						<td style="background-color: #eeeeee;">게시글 번호</td>
					<td>
						<c:out value="${bbs.bbsId }"/>
					</td>
					</tr>
					<tr>
						<td style="background-color: #eeeeee;">작성자</td>
						<td>${bbs.user.userId }</td>
						<td style="background-color: #eeeeee;">작성일자</td>
						<td>${bbs.bbsDate }</td>
					</tr>
					<tr>
						<td colspan="4" style="background-color: #eeeeee;">내용</td>
					</tr>
					<tr>
						<td colspan="4" style="min-height:200px; text-align:left;"><c:out value="${bbs.bbsContent }"/></td>
					</tr>
				</tbody>
			</table>
			
			<a href="BBSmainPage.do" class="btn btn-primary">목록</a>
			<c:if test="${userId eq bbs.user.userId }">
				<a href="BBSupdatePage.do?bbsId=${bbs.bbsId }" class="btn btn-primary">수정</a>
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#delete_modal">삭제</button>
			</c:if>
			<div class="modal fade" id="delete_modal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title" id="myModalLabel">해당 글을 삭제하시겠습니까?</h4>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
							<form action="BBSdelet.do" method="post">
								<div class="jumbotron">
									<div class="form-group">
										<input type="text" class="form-control" placeholder="아이디"
										name="userId" maxlength="20" />
									</div>
									<div class="form-group">
										<input type="password" class="form-control" placeholder="비밀번호"
										name="userPassword" maxlength="20" />
									</div>
								</div>
								 <div class="modal-footer">
									<input type="hidden" name="bbsId" value="${bbs.bbsId }" />
									<button type="button" class="btn btn-default"
									data-dismiss="modal">취소</button>
									<button type="submit" class="btn btn-primary">삭제</button>
								</div>
							</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../View_page_file/footerPage.jsp"%>

	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>