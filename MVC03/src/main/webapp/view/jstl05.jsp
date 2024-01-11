<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	// 배열 만들기
	// Controller -> jsp
	String[] str = {"사과", "바나나", "포도", "귤", "오렌지"};
	
	// 컨트롤러가 객체 바인딩
	// str 배열이 str이라는 변수에 저장되어 있는 형태
	request.setAttribute("str", str);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 이는 request.getAttribute("str"); 하는 것과 같다. -->
<c:forEach var="f" items="${str}">  <!-- str 배열을 받아옴 -->
	${f}<br>
</c:forEach>
</body>
</html>