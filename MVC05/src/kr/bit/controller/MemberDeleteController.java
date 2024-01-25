package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;

public class MemberDeleteController implements Controller{
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ctx = request.getContextPath();  // /MVC04
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		MemberDAO dao = new MemberDAO();
		int cnt = dao.memberDelete(num);  // cnt가 0보다 크면 삭제 성공
		
		String nextPage = null;
		if (cnt > 0) {  // 성공 시 memberList.do로 리다이렉트(되돌아감)
			nextPage = "redirect:"+ctx+"/memberList.do";
		} else {	// 실패 시 예외처리
			throw new ServletException("not delete");
		}
		return nextPage;
	}

}
