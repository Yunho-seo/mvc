package kr.inflearn.service;

import java.util.List;

import kr.inflearn.model.BoardVO;

public interface BoardService {
	public List<BoardVO> getList();  	  	   // 리스트 보기
	
	public void register(BoardVO board);  	   // 글 등록
	
	public BoardVO get(int bno, String mode);  // 글 상세 보기
	
	public int remove(int bno);			  	   // 글 삭제
	
	public int modify(BoardVO board);	  	   // 글 수정
}
