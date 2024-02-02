package kr.inflearn.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.inflearn.model.BoardVO;
import kr.inflearn.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@RequestMapping("/list.do")
	public String list(Model model) {  // 객체바인딩용 model
		List<BoardVO> list = service.getList();
		model.addAttribute("list", list);
		
		return "boardList";  // boardList.jsp
	}
	
	@RequestMapping(value="/register.do", method=RequestMethod.GET)
	// @GetMapping("/register.do")
	public String registerGET() {
		
		return "register";  // register.jsp (게시물 등록화면)
	}
	
	@RequestMapping(value="/register.do", method=RequestMethod.POST)
	// @PostMapping("/register.do")
	public String registerPOST(BoardVO board) {   // 게시물 등록
		service.register(board);	 // 게시물 등록
		return "redirect:/list.do";  // 게시물 보기
	}
	
	@RequestMapping("/get.do")
	public String get(@RequestParam("bno") int bno, Model model) {
		BoardVO board = service.get(bno, "get");
		model.addAttribute("board", board);
		return "get";  // get.jsp (게시물 상세보기)
	}
	
	@RequestMapping(value="/modify.do", method=RequestMethod.GET)
	public String modifyGET(@RequestParam("bno") int bno, Model model) {
		BoardVO board = service.get(bno, "modify");
		model.addAttribute("board", board);
		return "modify";  // modify.jsp (게시물 수정화면)
	}
	
	@RequestMapping(value="/modify.do", method=RequestMethod.POST)
	public String modifyPOST(BoardVO board) {	
		service.modify(board);
		return "redirect:/list.do";
	}
	
	@RequestMapping("/remove.do")
	public String remove(@RequestParam("bno") int bno) {
		service.remove(bno);
		return "redirect:/list.do";
	}
}

/*
	/list.do ------> GET  --> list()
	/register.do --> POST --> register()
	/read.do ------> GET ---> read()
	/modify.do ----> POST --> modify()
	/remove.do ----> GET ---> remove()
*/