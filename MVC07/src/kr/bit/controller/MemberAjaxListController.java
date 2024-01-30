package kr.bit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberAjaxListController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MemberDAO dao = new MemberDAO();
		List<MemberVO> list = dao.memberList();
		
		// ArrayList를 JSON 형태로 변환하는 Gson API
		Gson g = new Gson();
		String json = g.toJson(list);
		System.out.println(json);  // [{ }, { }, { } ... ]
		// $.ajax()로 왔던 함수 쪽으로 응답 --> json 형태로 응답
		response.setContentType("text/json;charset=euc-kr");  // json 형식으로 보내기
		response.getWriter().print(json);
		
		return null;
	}

}
