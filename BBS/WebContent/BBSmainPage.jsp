<%@page import="com.MyshoppingMall.bbs.vo.Bbs"%>
<%@page import="java.util.List"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
	request.setCharacterEncoding("utf-8");
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
	<%@include file="../View_page_file/headerPage.jsp"%>
	<%@include file="../View_page_file/navPage.jsp"%>

	<div class="container">
		<div class="row" style="margin-top: 3rem;">
			<table class="table table-stripted"
				style="text-align: center; border: 1px solid #dddddd; margin: 10px;">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">번호</th>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">작성자</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${bbsList.hasNoBbs() }">
						<tr>
							<td>게시글이 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach items="${bbsList.content }" var="bbs">
						<tr>
							<td>${bbs.bbsNo}</td>
							<td><c:out value="${bbs.bbsTitle }" /></td>
							<td>${bbs.user.userId }</td>
							<td>${bbs.bbsDate }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row">
			<div class="col-md-4 pull-md-4-right ml-md-auto">
				<a href="BBSwritePage.jsp" class="btn btn-primary">글쓰기</a>
			</div>
			<div class="col-md-4 ml-md-auto">
				<ul class="pagination">
					<c:if test="${bbsList.hasBbs() }">
						<c:if test="${bbsList.startPage >5 }">
							<a href="BBSmainPage.do?pageNo=${bbsList.startPage-5}">[이전]</a>
						</c:if>
						<c:forEach var="pNo" begin="${bbsList.startPage }"
							end="${bbsList.endPage }">
							<c:choose>
								<c:when test="${pNo == bbsList.currentPage }">
									<li class="page-item active" aria-current="page"><a
										class="page-link" href="BBSmainPage.do?pageNo=${pNo }">${pNo }<span
											class="sr-only">(current)</span>
									</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										href="BBSmainPage.do?pageNo=${pNo }">${pNo }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${bbsList.endPage < bbsList.totalPage }">
							<a href="BBSmainPage.do?pageNo=${bbsList.startPage+5 }">[다음]</a>
						</c:if>
					</c:if>
				</ul>
			</div>
			<div class="col-md-4 ml-md-auto">
				<form class="form-inline my-2 my-lg-0" method="get" name="bbsSearchForm">
					<select class="custom-select my-1 mr-sm-2"
						id="inlineFormCustomSelectPref">
						<option selected value="bbsTitle">제목</option>
						<option value="bbsContent">내용</option>
						<option value="bbsWriter">작성자</option>
					</select> <input class="form-control mr-sm-2" type="search"
						placeholder="검색어를 입력하세요" aria-label="Search" style="width: 100px;">
					<button class="btn btn-primary my-2 my-sm-0" type="submit">Search</button>
				</form>
			</div>
		</div>
	</div>
	<%@include file="../View_page_file/footerPage.jsp"%>

	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>