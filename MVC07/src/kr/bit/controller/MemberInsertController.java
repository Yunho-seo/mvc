package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberInsertController implements Controller {
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ctx = request.getContextPath();
		
		// insert POJO가 프런트 컨트롤러의 일을 대신 함
		// 파라미터를 받을 수 있고, request가 넘어왔으니 똑같이 request로 받을 수 있음
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
				
		// 파라미터 수집 (VO)
		MemberVO vo = new MemberVO();
		
		if (request.getParameter("mode").equals("fadd")) {
			String filename = request.getParameter("filename");
			vo.setFilename(filename);
		}
	
		vo.setId(id);
		vo.setPass(pass);
		vo.setName(name);
		vo.setAge(age);
		vo.setEmail(email);
		vo.setPhone(phone);
		
		MemberDAO dao = new MemberDAO();
		
		int cnt = -1;
		if (request.getParameter("mode").equals("fadd")) {
			cnt = dao.memberInsertFile(vo);  // 파일명을 저장해야 하는 경우
		} else {
			cnt = dao.memberInsert(vo);  // 파일명을 저장할 필요가 없는 경우
		}
		
		String nextPage = null;
		if (cnt>0) {
			// 가입 성공
			nextPage="redirect:"+ctx+"/memberList.do";
		} else {
			// 가입 실패
			throw new ServletException("not insert");
		}
		
		return nextPage;
	}
}
