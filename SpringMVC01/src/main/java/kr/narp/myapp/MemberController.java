package kr.narp.myapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

// @ : 어노테이션(전처리) -> 클래스를 Spring Container에서 관리해줌
@Controller
public class MemberController {
	
	@Autowired
	private MemberDAO dao;  // DI(의존성 주입)기법으로 컨테이너에서 데이터 주입받기
	
	@RequestMapping("/memberList.do")
	public String memberList(Model model) {  // HttpServletRequest
		List<MemberVO> list = dao.memberList();
		// 객체 바인딩
		// 조회된 리스트를 model에 list라는 이름으로 추가 -> View에서 데이터에 접근가능
		model.addAttribute("list", list);  
		return "memberList";
	}
	
	@RequestMapping("/memberInsert.do")
	public String memberInsert(MemberVO vo) {  // vo에서 파라미터 수집 완료
		int cnt = dao.memberInsert(vo);
		
		return "redirect:/memberList.do";
	}
	
	@RequestMapping("/memberRegister.do")
	public String memberRegister() {

		return "memberRegister";
	}
	
	@RequestMapping("/memberDelete.do")
	public String memberDelete(int num) {  // 파라미터 수집 : num
		int cnt = dao.memberDelete(num);
		
		return "redirect:/memberList.do";
	}
	
	@RequestMapping("/memberContent.do")
	public String memberContent(int num, Model model) {  // 객체바인딩 - Model
		MemberVO vo = dao.memberContent(num);
		// 객체 바인딩
		model.addAttribute("vo", vo);
		return "memberContent";
	}
	
	@RequestMapping("/memberUpdate.do")
	public String memberUpdate(MemberVO vo) {
		int cnt = dao.memberUpdate(vo);
		
		return "redirect:/memberList.do";
	}
}
