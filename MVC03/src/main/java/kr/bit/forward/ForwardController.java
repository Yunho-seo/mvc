package kr.bit.forward;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberVO;
@WebServlet("/fc.do")
public class ForwardController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = "park";
		int age = 45;
		String email = "aaa@empal.com";
		
		// 3개의 정보를 VO로 묶고, jsp로 넘겨주기
		MemberVO vo = new MemberVO();
		vo.setName(name);
		vo.setAge(age);
		vo.setEmail(email);
		
		// forward.jsp
		// 객체 바인딩
		request.setAttribute("vo", vo);
		
		// forwarding (요청 의뢰 객체 만들어야 함)
		// 클라이언트의 요청을 의뢰할 jsp 페이지를 지정 (-> forward.jsp)
		RequestDispatcher rd =
				request.getRequestDispatcher("view/forward.jsp");
		rd.forward(request, response);  // 기존 서블릿의 요청 응답 객체를 받아옴
		
	}

}
