<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "kr.bit.model.*" %>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	MemberDAO dao = new MemberDAO();
	MemberVO vo = dao.memberContent(num);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css'>
<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js'></script>
<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js'></script>
</head>
<body>
<form action="memberUpdate.jsp" method="post">  <!-- 수정하기 버튼 누르면 memberUpdate.jsp 페이지로 action -->
<input type="hidden" name="num" value="<%=vo.getNum()%>"/>  <!-- 수정하려는 회원의 번호 hidden으로 넘기기 -->
<table class='table table-bordered'>
<% if(vo!=null) { %>
	<tr>
		<td colspan="2"><%=vo.getName()%> 회원의 상세보기</td>
	</tr>
	<tr>
		<td>번호</td>
		<td><%=vo.getNum()%></td>
	</tr>
	<tr>
		<td>아이디</td>
		<td><%=vo.getId()%></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><%=vo.getPass()%></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><%=vo.getName()%></td>
	</tr>
	<tr>
		<td>나이</td>
		<td><input type='text' name='age' value="<%=vo.getAge()%>"/></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type='text' name='email' value="<%=vo.getEmail()%>"/></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><input type='text' name='phone' value="<%=vo.getPhone()%>"/></td>
	</tr>
	<% } else { %>
	<tr>
		<td>일치하는 회원이 없습니다.</td>
	</tr>
	<% } %>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="수정하기" class='btn btn-primary'/>
			<input type="reset" value="취소" class='btn btn-warning'/>
			<input type="button" value="리스트" onclick="location.href='memberList.jsp'" class='btn'/>
		</td>
	</tr>
</table>
</form>
</body>
</html>