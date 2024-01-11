<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "kr.bit.model.*" %>
<%
	request.setCharacterEncoding("utf-8");	// 한글화
	// 파라미터 수집(VO)
	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
	String name = request.getParameter("name");
	int age = Integer.parseInt(request.getParameter("age"));
	String email = request.getParameter("email");
	String phone = request.getParameter("phone");
	
	// MemberVO vo = new MemberVO(id, pass, name, age, email, phone);
	MemberVO vo = new MemberVO();
	vo.setId(id);
	vo.setPass(pass);
	vo.setName(name);
	vo.setAge(age);
	vo.setEmail(email);
	vo.setPhone(phone);
	
	// 수집된 데이터를 보기 위해 확인
	MemberDAO dao = new MemberDAO();
	int cnt = dao.memberInsert(vo);
	if (cnt > 0) {
		// 가입 성공
		response.sendRedirect("memberList.jsp");
	} else {
		// 가입 실패 -> 예외처리하여 WAS에게 던지기
		throw new ServletException("not insert");
	}

%>