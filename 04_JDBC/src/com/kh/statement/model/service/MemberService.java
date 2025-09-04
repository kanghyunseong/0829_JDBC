package com.kh.statement.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.function.Function;

import com.kh.statement.model.DTO.PasswordDTO;
import com.kh.statement.model.dao.MemberDao;
import com.kh.statement.model.vo.Member;

public class MemberService {

	private Connection conn = null;

	public MemberService() {
		super();
		this.conn = getConnection();
	}

	public int save(Member member) {

		int result = new MemberDao().save(conn, member);

		// 6) 트랜잭션 처리
		if (result > 0) {
			commit(conn);
		}
		close(conn);
		return result;
	}
	
	private <T> T executeQuery(Function<Connection, T> daoFunction) {
		Connection conn = null;
		T result = null;
		
		conn = getConnection();
		result = daoFunction.apply(conn);
		
		close(conn);
		
		return result;
	}

	public List<Member> findAll() {
		
		return executeQuery(new MemberDao()::findAll);
	}

	public Member findById(String userId) {
		
		return executeQuery(conn -> new MemberDao().findById(conn, userId));
	}

	public List<Member> findByKeyword(String keyword) {

		return executeQuery(conn -> new MemberDao().findByKeyword(conn, keyword));
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
		int result = new MemberDao().delete(conn, member);
		
		close(conn);
		return result;
	}
}
