package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;
@WebServlet("/memberInsert.do")
public class MemberInsertController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		// request 정보 한글화
		request.setCharacterEncoding("utf-8");
		
		// 1. 파라미터 수집(VO)
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		// 파라미터 수집(VO) : set을 이용하여 수집하는 것이 일반적이다.
		// MemberVO vo = new MemberVO(id, pass, name, age, email, phone);
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPass(pass);
		vo.setName(name);
		vo.setAge(age);
		vo.setEmail(email);
		vo.setPhone(phone);
		
		// 수집된 데이터를 보기 위해 확인
		// System.out.println(vo);	// vo.toString() 호출	
		// Model과 연동부분
		MemberDAO dao = new MemberDAO();
		int cnt = dao.memberInsert(vo);
		// PrintWriter out = response.getWriter();
		if (cnt>0) {
			// 가입 성공
			// out.println("insert success");  // 이후 회원리스트 보기로 가야함(/MVC01/memberList.do)
			response.sendRedirect("/MVC03/memberList.do");
		} else {
			// 가입 실패 -> 예외처리하여 WAS에게 던지기
			throw new ServletException("not insert");
		}
	}

}
