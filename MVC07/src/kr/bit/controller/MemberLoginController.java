package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberLoginController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ctx = request.getContextPath();
		
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		
		MemberVO vo = new MemberVO();
		vo.setId(user_id);
		vo.setPass(password);
		
		MemberDAO dao = new MemberDAO();
		String user_name = dao.memberLogin(vo);
		// user_name에 값이 있으면 인증에 성공, 값이 없으면 실패
		if (user_name != null && !"".equals(user_name)) {  // NULL 체크
			// 인증 성공 - 인증된 사실을 모든 웹사이트에서 계속 알아야함 (세션을 사용)
			// 유저 아이디를 setAttribute로 객체 바인딩 
			// -> 다른 페이지들이 회원 인증에 성공한 사실을 알아야 함
			
			HttpSession session = request.getSession();   // 세션 가져옴(없으면 생성)
			session.setAttribute("userId", user_id);      // user_id를 객체바인딩
			session.setAttribute("userName", user_name);  // user_name을 객체바인딩
		} else {
			// 인증 실패
			// 실패 시 두개의 값(user_id, user_name)을 NULL 처리 후 메시지 출력
			request.getSession().setAttribute("userID", "");
			request.getSession().setAttribute("userName", "");
			request.getSession().setAttribute("msg", "사용자 정보가 올바르지 않습니다.");
		}
		return "redirect:" + ctx + "/memberList.do";
	}
}