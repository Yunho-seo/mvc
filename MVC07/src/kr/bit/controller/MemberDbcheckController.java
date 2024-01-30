package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;

public class MemberDbcheckController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// $.ajax();
		String id = request.getParameter("id");  // {"id" : id}
		
		// DB 작업 (중복 여부 검사)
		MemberDAO dao = new MemberDAO();
		String dbDouble = dao.memberDbcheck(id);  // YES(중복) or NO(중복X)
		
		// 이 부분이 ajax() 함수에 만들어놓은 콜백 함수(dbCheck)로 응답이 된다.
		response.getWriter().print(dbDouble);
		
		return null;  // 페이지 화면이 바뀌지 않도록 null 처리
	}
}