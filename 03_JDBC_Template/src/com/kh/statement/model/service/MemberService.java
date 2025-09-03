package com.kh.statement.model.service;

import java.sql.Connection;
import java.util.List;

import static com.kh.common.JDBCTemplate.*;

import com.kh.statement.model.DTO.PasswordDTO;
import com.kh.statement.model.dao.MemberDao;
import com.kh.statement.model.vo.Member;

// 클라이언트 요청처리
// 제어흐름 --> 컨트롤러
// 핵심로직 실행 --> 서비스
/*
 * 비즈니스 로직 실행 (의사결정코드) -> 데이터 가공, 중복 체크, 연산 처리, 암호화
 * 트랜잭션 관리
 * 여러 DAO를 조합
 * 에외 처리 및 변환
 * 보안 및 권한 검사
 */
public class MemberService {

	private Connection conn = null;
	/*
	 * Service : 비즈니스로직 / 의사 결정코드를 작성하는 부분 Controller -> Service의 메소드를 호출 Service에서
	 * Connection을 생성해서 DAO로 전달 만약 SQL문을 수행해야하는데 필요한 값이 있다면 Controller로부터 전달받아서
	 * Connection과 같이 넘겨줄 것 DAO에서 DB작업이 끝나면 Service단에서 결과에 따른 트랜잭션 처리도 진행
	 * 
	 * => Service를 추가함으로 DAO는 순수하게 SQL문을 처리하는 부분만 남겨둘 것
	 */

	public MemberService() {
		super();
		this.conn = getConnection();
	}

	public int save(Member member) {

		// Connection conn = getConnection();

		// DAO 호출 시 Connection 객체 전달
		// +
		// Controller가 넘겨준 사용자가 입력한 값이 필드에 담겨있는
		// Member 참조변수를 함꼐 넘겨줌

		int result = new MemberDao().save(conn, member);

		// 6) 트랜잭션 처리
		if (result > 0) {
			commit(conn);
		}
		close(conn);
		return result;
	}

	public List<Member> findAll() {
		// Connection conn = getConnection();

		// 2) DAO 호출
		// Service에서 받아온 Connection 넘겨주기 + 만약에 Controller가 넘겨준 값이 있다면
		// 같이 넘겨줄 것
		List<Member> members = new MemberDao().findAll(conn);

		// 3) Connection 반납
		close(conn);

		// 4) 결과반환
		return members;
	}

	public Member findById(String userId) {
		conn = getConnection();

		Member member = new MemberDao().findById(conn, userId);

		return member;
	}

	public List<Member> findByKeyword(String keyword) {
		List<Member> members = new MemberDao().findByKeyword(conn, keyword);

		close(conn);

		return members;
	}

	public int update(PasswordDTO pd) {
		// 회원의 비밀번호를 수정해야한다 == Member테이블에서 한 행 UPDATE
		// 비밀번호 수정
		// UPDATE MEMBER SET USERPWD == 머시기 WHERE USERID = 머시기 AND USERPWD = 머시기
		if (pd.getNewPassword().length() > 20) {
			// throw new RuntimeException("비밀번호가 너무 길어요");
			return 0;
		}
		Member member = new MemberDao().findById(conn, pd.getUserId());
		if (member == null) {
			// throw new RuntimeException("존재하지 않는 아이디입니다.");
			return 0;
		}

		int result = new MemberDao().update(conn, pd);

		if(result > 0) {
			commit(conn);
		}
		
		close(conn);

		return result;
	}

	public int delete(Member member) {
		// 회원의 정보를 삭제해야지
		// 1) Connection 만들어야지
		
		// 2) 매개변수로 받은거하고 커넥션하고 DAO로 넘겨야지
		int result = new MemberDao().delete(conn, member);
		// 3) DML이니까 다녀오면 트랜잭션처리
		
		// 4) 트랜잭션 끝나면 Connection할 일 없으니까 반납해야지
		close(conn);
		// 5) 결과 반환해줘야지
		return result;
	}

}
