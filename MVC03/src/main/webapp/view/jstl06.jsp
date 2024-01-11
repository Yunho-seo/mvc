<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	// 문자열 타입으로 list 이름을 갖는 ArrayList 만들기
	List<String> list = new ArrayList<String>();
	list.add("Python");
	list.add("Java");
	list.add("Node.js");
	list.add("C++");
	list.add("JQuery");
	
	// 객체 바인딩
	request.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="sw" items="${list}">
	${sw}<br>
</c:forEach>
</body>
</html>