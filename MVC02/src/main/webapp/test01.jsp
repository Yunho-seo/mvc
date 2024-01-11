<%-- 지시자 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- 선언문 --%>
<%-- 두수 사이의 총합을 구하는 메서드 --%>
<%!
	public int hap(int s, int e) {
		int sum = 0;
		for (int i=s; i<=e; i++) {
			sum += i;
		}
		return sum;
	}

%>

<%-- 스크립트릿 (소스코드) --%>
<%
	int sum = 0;
	for (int i=1; i<=100; i++) {
		sum += i;
		
	}
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
<tr>
	<td>1~100까지의 총합</td>
	<td><%= sum %></td>
</tr>
<tr>
	<td>55~350까지의 총합</td>
	<%-- 출력식 --%>
	<td><%= hap(55, 350) %></td>
</tr>
</table>
</body>
</html>