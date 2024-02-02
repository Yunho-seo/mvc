package kr.narp.myapp1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.bit.mapper.MemberMapper;
import kr.bit.model.MemberVO;

// @ : 어노테이션(전처리) -> 클래스를 Spring Container에서 관리해줌
@Controller
public class MemberController {
	
	@Autowired
	//private MemberDAO dao;  // DI(의존성 주입)기법으로 컨테이너에서 데이터 주입받기
	private MemberMapper memberMapper;
	
	@RequestMapping("/memberList.do")
	public String memberList(Model model) {  // HttpServletRequest
		List<MemberVO> list = memberMapper.memberList();
		// 객체 바인딩
		// 조회된 리스트를 model에 list라는 이름으로 추가 -> View에서 데이터에 접근가능
		model.addAttribute("list", list);  
		return "memberList";
	}
	
	@RequestMapping("/memberInsert.do")
	public String memberInsert(MemberVO vo) {  // vo에서 파라미터 수집 완료
		int cnt = memberMapper.memberInsert(vo);
		
		return "redirect:/memberList.do";
	}
	
	@RequestMapping("/memberRegister.do")
	public String memberRegister() {

		return "memberRegister";
	}
	
	@RequestMapping("/memberDelete.do")
	public String memberDelete(int num) {  // 파라미터 수집 : num
		int cnt = memberMapper.memberDelete(num);
		
		return "redirect:/memberList.do";
	}
	
	@RequestMapping("/memberContent.do")
	public String memberContent(int num, Model model) {  // 객체바인딩 - Model
		MemberVO vo = memberMapper.memberContent(num);
		// 객체 바인딩
		model.addAttribute("vo", vo);
		return "memberContent";
	}
	
	@RequestMapping("/memberUpdate.do")
	public String memberUpdate(MemberVO vo) {
		int cnt = memberMapper.memberUpdate(vo);
		
		return "redirect:/memberList.do";
	}
	
	@RequestMapping("/memberAjaxList.do")
	public @ResponseBody List<MemberVO> memberAjaxList() {
		List<MemberVO> list = memberMapper.memberList();
		// $.ajax() -> callback 함수로 응답 -> JSON
		
		// 바로 요청한 Ajax쪽으로 응답하려면 JSON으로 리턴하는게 일반적
		// @ResponseBody를 사용하여 곧바로 객체를 JSON으로 변환하여 리턴
		return list;  // Object -> JSON : @ResponseBody -> API(jackson-databind, jackson-mapper-asl)
	}
	
	@RequestMapping("/form.do")
	public String form() {
		
		return "uploadForm";
	}
}
