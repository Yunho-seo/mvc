<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "kr.bit.model.*" %>
<%
	// 특정 메모리 공간에 있는 정보를 가져와야 한다.
	// MemberVO로 캐스팅
	// setAttribute의 첫번째 인자는 String 타입, 두번째 인자는 Object 타입
	// Attribute도 return 타입이 object 타입이다.
	// 그치만 객체를 가져올 때는 object 타입이 아닌 MemberVO 타입이 필요하다
	// 타입을 맞춰주어야 하기 때문에 다운캐스팅을 진행한다.
	MemberVO vo = (MemberVO)request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Controller에서 받은 데이터 출력
이름 : <%=vo.getName()%>
나이 : <%=vo.getAge()%>
이메일 : <%=vo.getEmail()%>
</body>
</html>