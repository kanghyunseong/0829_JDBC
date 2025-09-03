package com.kh.statement.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.JDBCTemplate;
import com.kh.statement.model.DTO.PasswordDTO;
import com.kh.statement.model.vo.Member;

public class MemberDao {

	public int save(Connection conn, Member member) {

		// 0) 필요한 변수세팅 ~~
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = """
						INSERT
						  INTO
						       MEMBER
						VALUES
						        (
						        SEQ_USERNO.NEXTVAL
						      , ?
						      , ?
						      , ?
						      , ?
						      , SYSDATE
						        )
				     """;

		// 1) Driver등록 -> 하고옴
		// 2) Connection -> 하고옴
		
		try {
			pstmt = conn.prepareStatement(sql);
			// 3_2) 미완성된 SQL문일 경우 묶어줄 값 전달하기
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getEmail());
			
			// 4, 5) DB에 완성된 SQL문을결과 (int)받기
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7_1)할 일이 다 끝난 PreparedStatement객체만 반
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public List<Member> findAll(Connection conn) {
		
		// 0) 필요한 변수 선언 먼저!
		// PreparedStatement, ResultSet, sql, List
		List<Member> members = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = """
					SELECT
					       UNSERNO
					     , UNSERID
					     , USERPWD
					     , USERNAME
					     , EMAIL
					     , ENROLLDATE
					  FROM
					       MEMBER
					 ORDER
					    BY
					       ENROLLDATE DESC;
				""";
		
		// 3_1)
		try {
			pstmt = conn.prepareStatement(sql);
			
			// 4, 5) SQL(SELECT)을 실행 후 결과 (ResultSet)받기
			rset = pstmt.executeQuery();
			
			//6 ) 조회결과 여부 판단 후
			//    컬럼값을 객체 필드에 매핑
			
			while(rset.next()) {
				Member member = new Member(rset.getInt("USERNO")
				                         , rset.getString("USERID")
				                         , rset.getString("USERPWD")
				                         , rset.getString("USERNAME")
				                         , rset.getString("EMAIL")
				                         , rset.getDate("ENROLLDATE"));
				members.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7) 사용이 다 끝난 JDBC용 객체 반납
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return members;
	}

	public Member findById(Connection conn, String userId) {
		
		// 1) Connection 객체 받아오기
		// Connection conn = getConnection();
		
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = """
						SELECT
						       USERNO
						     , USERID
						     , USERPWD
						     , USERNAME
						     , EMAIL
						     , ENROLLdATE
						 FROM
						       MEMBER
						WHERE
						       USERID = ?
				""";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				member = new Member(rset.getInt("USERNO")
								  , rset.getString("USERID")
								  , rset.getString("USERPWD")
								  , rset.getString("USERNAME")
								  , rset.getString("EMAIL")
								  , rset.getDate("ENROLLDATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return member;
	}

	public List<Member> findByKeyword(Connection conn, String keyword) {
		
		List<Member> members = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = """
					SELECT
					       USERNO
					     , USERID
					     , USERPWD
					     , USERNAME
					     , MEAIL
					     , ENROLLDATE
					  FROM
					       MEMBER
					 WHERE
					       USERNAME LIKE '%'||?||'%'
				""";
		
		try {
			conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				members.add(new Member(rset.getInt("USERNO")
								  , rset.getString("USERID")
								  , rset.getString("USERPWD")
								  , rset.getString("USERNAME")
								  , rset.getString("EMAIL")
								  , rset.getDate("ENROLLDATE")));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return members;
	}


	public int update(Connection conn, PasswordDTO pd) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = """
				      UPDATE
				         SET
				             USERPWD = ?
				       WHERE
				             USERID = ?
				         AND
				             USERPWD = ?
				""";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pd.getNewPassword());
			pstmt.setString(2, pd.getUserId());
			pstmt.setString(3, pd.getUserPwd());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int delete(Connection conn, Member member) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = """
					DELETE
					  FROM
					       MEMBER
					 WHERE
					       USERID = ?
					   AND
					       USERPWD = ?
					""";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	
}