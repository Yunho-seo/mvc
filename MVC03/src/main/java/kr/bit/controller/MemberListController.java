package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;
@WebServlet("/memberList.do")
public class MemberListController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 1. 클라이언트의 요청 받기 (memberList.do)
		// 2. 회원 전체 리스트 가져오기 (Model 연동)
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberVO> list = dao.memberList();
		
		// 객체 바인딩 (request 바인딩)
		request.setAttribute("list", list);
		// forward 기법 (값을 페이지를 전환하며 넘겨야 하기에)
		RequestDispatcher rd = request.getRequestDispatcher("member/memberList.jsp");
		rd.forward(request, response);  // memberList.jsp
	}
}