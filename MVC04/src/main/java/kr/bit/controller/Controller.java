// package kr.bit.controller;

// import java.io.IOException;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// public interface Controller {
	// requestHandler 메소드를 만든 이유?
	// 모든 POJO가 가지고 있어야하는 메소드이다.
	
	// void가 아닌 리턴 타입이 String으로 바뀌게 됨
// 	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
// 		throws ServletException, IOException;
// }

package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 추상 메서드
public interface Controller {
	
	public String memberContent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
	
	public String memberDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
	
	public String memberInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
	
	public String memberList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
	
	public String memberUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
	
	public String memberRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
}
