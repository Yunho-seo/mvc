package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberLogoutController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 세션을 가져와 제거
		// 세션을 제거하는 방법?
		
		// 1. invalidate() 사용하여 강제로 제거
		String ctx = request.getContextPath();  // MVC06
		request.getSession().invalidate();
		
		// 2. 브라우저를 종료(세션ID 값이 브라우저 쿠키에 저장되어 있어서)
		// 3. 세션이 종료될때까지 기다리기 (로그인 후 아무 작업도 하지 않는다) (세션 타임아웃, 기본이 30분=1800초)
		
		return "redirect:" + ctx + "/memberList.do";
	}

}
