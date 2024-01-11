package kr.bit.forward;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/rc.do")
public class RedirectController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 1. forward 실습
		String name = "park";
		int age = 45;
		String email = "aaa@empal.com";
		// View page (result.jsp)로 data를 전달하여 View page에서 출력
		// Controller에서 View로 페이지 전환하는 방법
		// 1) redirect 기법
		// request.setAttribute("data", data);  // 객체 바인딩 (집어넣기) (request 바인딩)
		response.sendRedirect("view/result.jsp?name="+name+"&age="+age+"&email="+email);
		
		// 2) forward 기법
	}

}
