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
public class MemberDAO {
	private static SqlSessionFactory sqlSessionFactory;  // mybatis에서의 커넥션 풀
	
	// 초기화 블록-프로그램 실행 시 1번만 실행되는 코드 영역 
	// SQLSessionFactory 객체 만들기 (커넥션 풀 만들기)
	// config.xml 파일 리딩(읽어오기) 후 메모리 객체 만들기 (config 파일을 IO로 연결)
	
	// 커넥션 풀을 가지고 있는 sqlSessionFactory 객체 만드는 방법
	// 1. static 초기화 블록을 이용해서 mybatis 환경설정 파일을 IO로 연결
	// 2. build()라는 메소드로 연결을 해주면 IO를 이용하여 XML 데이터 리딩
	// 3. 리딩 후 메모리에 커넥션 풀을 만들고 그 객체를 SQL세션팩토리에 넣어줌
	
	static {
		try {
			String resource = "kr/bit/mybatis/config.xml";
			// InputStream (읽기)
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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