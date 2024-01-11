package kr.bit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;

@WebServlet("/memberDelete.do")
public class MemberDeleteController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// http://localhost:8080/MVC01/memberDelete.do?num=7
		int num = Integer.parseInt(request.getParameter("num"));
		
		MemberDAO dao = new MemberDAO();
		int cnt = dao.memberDelete(num);  // cnt가 0보다 크면 삭제 성공
		
		if (cnt > 0) {  // 성공 시 memberList.do로 리다이렉트(되돌아감)
			response.sendRedirect("/MVC03/memberList.do");
		} else {	// 실패 시 예외처리
			throw new ServletException("not delete");
		}
	}
}
