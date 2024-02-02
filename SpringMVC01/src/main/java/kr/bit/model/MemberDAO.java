package kr.bit.model;
import java.io.InputStream;

// JDBC -> mybatis
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//@ : 어노테이션(전처리) -> Spring Container에서 관리
@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;  
	
	// 회원 전체 리스트 보기
	public List<MemberVO> memberList() {
		// 이미 만들어져있는 커넥션을 sqlSessionFactory에서 꺼내오기
		// [Connection + Statement] => sqlSession
		SqlSession session = sqlSessionFactory.openSession();
		List<MemberVO> list = session.selectList("memberList");
		session.close();  // 클로즈 시 커넥션 풀에 세션을 반납
		return list;
	}
		
	// 회원 가입
	public int memberInsert(MemberVO vo) {
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.insert("memberInsert", vo);
		session.commit();
		session.close();  // 반납
		return cnt;
	}
	
	// 회원 삭제
	public int memberDelete(int num) {
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.delete("memberDelete", num);  // (String, Object형)
		session.commit();
		session.close();
		return cnt;
	}
	
	// 회원 상세보기
	public MemberVO memberContent(int num) {
		SqlSession session = sqlSessionFactory.openSession();
		// selectOne() : 해당하는 회원정보만 가져와 다시 MemberVO로 묶어 리턴해주는 메소드
		MemberVO vo = session.selectOne("memberContent", num);
		session.close();
		return vo;
	}
	
	// 회원 수정
	public int memberUpdate(MemberVO vo) {
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.update("memberUpdate", vo);
		session.commit();
		session.close();
		return cnt;
	}
}