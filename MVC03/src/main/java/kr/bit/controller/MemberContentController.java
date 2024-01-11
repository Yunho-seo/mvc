package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

@WebServlet("/memberContent.do")
public class MemberContentController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// http://localhost:8080/MVC03/memberContent.do?num=10
		int num = Integer.parseInt(request.getParameter("num"));
		
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.memberContent(num);  // vo : 회원 정보
		
		// 객체 바인딩
		request.setAttribute("vo", vo);
		RequestDispatcher rd = request.getRequestDispatcher("member/memberContent.jsp");
		rd.forward(request, response);  // member/memberContent.jsp
	}

}
