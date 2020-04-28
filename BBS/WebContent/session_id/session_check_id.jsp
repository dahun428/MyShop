<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
request.setCharacterEncoding("utf-8");

String userId = null;

if(session.getAttribute("userId") != null){
	userId = (String) session.getAttribute("userId");	
}
%>
    

    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MyShoppingMall</title>
</head>
<body>
</body>
</html>