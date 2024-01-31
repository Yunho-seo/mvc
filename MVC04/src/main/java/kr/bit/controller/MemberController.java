package kr.bit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

// 6개의 POJO가 해야할 일들을 --> 1개의 POJO로 통합
public class MemberController {  // implements Controller -- (X)
	
	// MemberContentController
	@RequestMapping("/memberContent.do")  // 메소드 단위 매핑
	public String memberContent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int num = Integer.parseInt(request.getParameter("num"));
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.memberContent(num);
		
		request.setAttribute("vo", vo);  // 객체 바인딩
		
		return "memberContent";
		// return "/WEB-INF/member/memberContent.jsp";
	}
	
	// MemberDeleteController
	@RequestMapping("/memberDelete.do")
	public String memberDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ctx = request.getContextPath();  // /MVC04
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		MemberDAO dao = new MemberDAO();
		int cnt = dao.memberDelete(num);
		
		String nextPage = null;
		if (cnt > 0) {
			nextPage = "redirect:"+ctx+"/memberList.do";
		} else {
			throw new ServletException("not delete");
		}
		return nextPage;
	}
	
	// MemberInsertController
	@RequestMapping("/memberInsert.do")
	public String memberInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ctx = request.getContextPath();
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPass(pass);
		vo.setName(name);
		vo.setAge(age);
		vo.setEmail(email);
		vo.setPhone(phone);
		
		MemberDAO dao = new MemberDAO();
		int cnt = dao.memberInsert(vo);
		// PrintWriter out = response.getWriter();
		String nextPage = null;
		if (cnt>0) {
			nextPage="redirect:"+ctx+"/memberList.do";
		} else {
			throw new ServletException("not insert");
		}
		
		return nextPage;
	}
	
	// MemberListController
	@RequestMapping("/memberList.do")
	public String memberList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MemberDAO dao = new MemberDAO();
		List<MemberVO> list = dao.memberList();
		
		request.setAttribute("list", list);
		
		return "memberList";
	}
	
	// MemberRegisterController
	@RequestMapping("/memberRegister.do")
	public String memberRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		return "memberRegister";
	}
	
	// MemberUpdateController
	@RequestMapping("/memberUpdate.do")
	public String memberUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ctx = request.getContextPath();
		
		int num = Integer.parseInt(request.getParameter("num"));
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		MemberVO vo = new MemberVO();
		vo.setNum(num);
		vo.setAge(age);
		vo.setEmail(email);
		vo.setPhone(phone);
		
		MemberDAO dao = new MemberDAO();
		int cnt = dao.memberUpdate(vo);
		
		String nextPage = null;
		if (cnt > 0) {
			nextPage="redirect:"+ctx+"/memberList.do";
		} else {
			throw new ServletException("not update");
		}
		
		return nextPage;
	}
}
