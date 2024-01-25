package kr.bit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberListController implements Controller{
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	
	// POJO가 해야할 일의 범위
	// 1. 모델 연동하기 (Model)
	MemberDAO dao = new MemberDAO();
	List<MemberVO> list = dao.memberList();  // DB 연동하여 객체가져오기
	
	// 2. 객체 바인딩
	request.setAttribute("list", list);		 // 객체 바인딩
	
	// member/memberList.jsp
	// 다음 페이지
	// 3. 다음 페이지 정보(뷰, View)
	
	return "memberList";
	// return "/WEB-INF/member/memberList.jsp";  // 객체 바인딩 후 페이지 바뀜
	}
}
