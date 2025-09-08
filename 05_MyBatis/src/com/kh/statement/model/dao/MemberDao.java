package com.kh.statement.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.statement.model.dto.PasswordDTO;
import com.kh.statement.model.vo.Member;

public class MemberDao {

	public int save(SqlSession session, Member member) {
		
		/*
		 * int result = 0;
		 * PreparedStatement pstmt = null;
		 * String sql = prop.getProperty("save");
		 * try {
		 * pstmt = conn.prepareStatement(sql);
		 * pstmt.setString(1, member.getUserId());
		 * psmtmt.setString(2, member.getUserPwd());
		 * ...
		 * result = pstmt.executeUpdate();
		 * }catch(IOException e){
		 * 		e.printStackTrace();
		 * } finally {
		 * 		JDBCTemplate.close(pstmt);
		 * }
		 * return result;
		 * }
		 * 
		 * SqlSession이 제공하는 메소드를 통해 SQL문을 찾아서 실행하고 결과도 받아볼 수 있음
		 * session.insert("메퍼파일의namespace.해당SQL문의id속성값);
		 * 
		 * 
		 */
		
		return session.insert("memberMapper.save", member);
		
	}

	public List<Member> findAll(SqlSession session) {
		// List<Member> member = session.selectList("memberMapper.findAll");
		// 조회결과가 존재하지 않는다면 빈 리스트를 반환
		return session.selectList("memberMapper.findAll");
		
	}
	
	public Member findById(SqlSession session, String userId) {
		// Member member = session.selectOne("memberMapper.findById", userId);
		// 조회결과가 존재하지 않다면 null 반환
		return session.selectOne("memberMapper.findById", userId);
		
	}

	public List<Member> finByKeyword(SqlSession session, String keyword) {
		
		return session.selectList("memberMapper.findByKeyword", keyword);
	}

	public int update(SqlSession session, PasswordDTO pd) {
		
		return session.update("memberMapper", pd);
	}

	public int delete(SqlSession session, Member member) {
		
		return session.update("memberMapper", member);
	}
}