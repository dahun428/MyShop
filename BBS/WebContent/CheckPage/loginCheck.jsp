<%@page import="java.io.PrintWriter"%>
<%@page import="com.MyshoppingMall.bbs.util.LoginCheckFunction"%>
<%@page import="com.MyshoppingMall.bbs.dao.UserDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%
request.setCharacterEncoding("utf-8");

UserDAO userDao = new UserDAO();

String userId = null;

if(session.getAttribute("login") != null){
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
<title>Insert title here</title>
</head>
<body>
<c:choose>
	<c:when test="${login == LoginCheckFunction.NO_EXIST_ID }">
		<script>
			alert('아이디가 존재하지 않습니다.');
			history.go(-1);
		</script>
	</c:when>
	<c:when test="${login == LoginCheckFunction.NO_EQUALS_PASSWORD }">
		<script>
			alert('비밀번호가 틀립니다.');
			history.go(-1);
		</script>
	</c:when>
	<c:when test="${login == LoginCheckFunction.SUCCESS_LOGIN }">
		<%
			userId = (String) request.getAttribute("userId");
			session.setAttribute("userId", userId);
		%>
		<script>
			location.href ="index.jsp";
		</script>
	
	</c:when>
	<c:otherwise>
		<script>
			alert('존재하지 않는 회원입니다.');
			history.go(-1);
		</script>
	</c:otherwise>

</c:choose>


</body>
</html>