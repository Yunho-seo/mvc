<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "kr.bit.model.*" %>
<%
	request.setCharacterEncoding("utf-8");
	// 파라미터 수집 (VO)
	int num = Integer.parseInt(request.getParameter("num"));
	int age = Integer.parseInt(request.getParameter("age"));
	String email = request.getParameter("email");
	String phone = request.getParameter("phone");
	
	// MemberVO 클래스의 객체 생성 후 객체에 수집한 파라미터 값 설정 
	MemberVO vo = new MemberVO();
	vo.setNum(num);
	vo.setAge(age);
	vo.setEmail(email);
	vo.setPhone(phone);
	
	MemberDAO dao = new MemberDAO();
	int cnt = dao.memberUpdate(vo);
	
	if (cnt>0) {
		// 수정 성공
		response.sendRedirect("memberList.jsp");
	} else {
		// 수정 실패 -> 예외처리하여 WAS에게 던지기
		throw new ServletException("not update");
	}
%>