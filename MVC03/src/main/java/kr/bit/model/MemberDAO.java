package kr.bit.model;
// JDBC 프로그램, 데이터 접근 객체 (DAO)
// 최근에는 JDBC -> myBatis, JPA

import java.sql.*;
import java.util.ArrayList;

public class MemberDAO {
	private Connection conn;		// JAVA와 DB를 연결하는 객체
	private PreparedStatement ps;	// 자바에서 쿼리문 실행하는 객체
	private ResultSet rs;			// 쿼리실행 결과를 받아 저장하는 객체
	
	// DB 연동을 위한 DB 연결객체 생성
	public void getConnect() {
		// 데이터베이스 접속 URL (벤더마다 URL이 다름)
		String URL = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimeZone=UTC";
		String user = "root";
		String password = "admin12345";
		
		// MySQL Driver Loading (로딩 해야 객체생성 가능)
		try {	// 동적 로딩, 클래스가 없을 경우에 에러 메시지 출력 
				// 동적 로딩 (실행 지점에서 객체를 생성하는 방법)
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, user, password);  // 로딩 후 접속시도
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	// 회원저장 동작
	public int memberInsert(MemberVO vo) {
		// '?'의 의미는 파라미터
		String SQL = "insert into member(id, pass, name, age, email, phone)"
				+ "values (?, ?, ?, ?, ?, ?)";
		getConnect();
		int cnt = -1;
		// SQL 문장을 전송하는 객체 (preparedStatement) 생성
		try {
			ps = conn.prepareStatement(SQL);  // 프리컴파일 (세팅, 속도 빠르게)
			ps.setString(1, vo.getId());  // 1번째 물음표인 vo의 getId를 빼내어 설정
			ps.setString(2, vo.getPass());
			ps.setString(3, vo.getName());
			ps.setInt(4, vo.getAge());
			ps.setString(5, vo.getEmail());
			ps.setString(6, vo.getPhone());
			
			// 실행 성공시 1, 실패시 0의 값이 cnt(count)에 넘어오게 된다.
			cnt = ps.executeUpdate();  // DB에 전송 (실행)
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;  // 1 or 0
	}
	
	// 회원(VO 구조) 전체 리스트(ArrayList) 가져오기
	public ArrayList<MemberVO> memberList() {
		String SQL = "select * from member";
		getConnect();
		// ArrayList에 회원을 넣어야 함, Generic으로 VO 만들기
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		try {
			ps = conn.prepareStatement(SQL);	// 프리컴파일
			rs = ps.executeQuery();  // rs => 변수의 의미가 커서, 결과 집합을 가지는 커서
			
			while(rs.next()) {  // 하나의 레코드 -> VO로 묶고 ArrayList에 담기
				int num = rs.getInt("num");
				String id = rs.getString("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				
				// add()로 묶기 -> ArrayList에 담기
				MemberVO vo = new MemberVO(num, id, pass, name, age, email, phone);
				list.add(vo);
			}					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;	
	}
	
	public int memberDelete(int num) {
		String SQL = "delete from member where num=?";  // ? : num
		getConnect();
		int cnt = -1;	// 초기값 임의설정
		try {
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, num);  		// ? 값 설정
			
			cnt = ps.executeUpdate();	// 삭제 성공 시 개수가 하나 줄고, 남은데이터 수를 리턴해야 함
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;
	}
	
	public MemberVO memberContent(int num) {
		String SQL = "select * from member where num=?";
		getConnect();
		MemberVO vo = null;
		try {
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, num);
			// sql문 전송 시 데이터 있으면 executeQuery가 결과집합을 rs로 넘김
			// 데이터가 없다면 NULL
			rs = ps.executeQuery();
			if(rs.next()) {
				// 한명의 데이터를 가져와 묶기(VO)
				num = rs.getInt("num");
				String id = rs.getString("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				
				vo = new MemberVO(num, id, pass, name, age, email, phone);
				// 한명의 데이터를 조회하느라 ArrayList 필요 없음
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return vo;
	}
	
	public int memberUpdate(MemberVO vo) {
		String SQL = "update member set age=?, email=?, phone=? where num=?";
		getConnect();
		int cnt = -1;
		try {
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, vo.getAge());
			ps.setString(2, vo.getEmail());
			ps.setString(3, vo.getPhone());
			ps.setInt(4, vo.getNum());
			
			cnt = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;
	}
	
	// DB 연결끊기
	public void dbClose() {
		try {
			if(rs != null) rs.close();  // rs 객체가 만들어져있으면 연결 끊기
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}





