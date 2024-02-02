package kr.inflearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.inflearn.mapper.BoardMapper;
import kr.inflearn.model.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper mapper;  // DI(의존성 주입)
	
	@Override
	public List<BoardVO> getList() {
		
		return mapper.getList();  // 리스트 타입 넘겨주기
	}

	@Override
	public void register(BoardVO board) {
		mapper.insert(board);
	}

	@Override
	public BoardVO get(int bno, String mode) {
		if(mode.equals("get")) {
			mapper.count(bno);
		}
		return mapper.read(bno);
	}

	@Override
	public int remove(int bno) {
		
		return mapper.delete(bno);
	}

	@Override
	public int modify(BoardVO board) {
		
		return mapper.update(board);
	}

}