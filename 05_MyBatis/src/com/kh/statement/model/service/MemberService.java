package com.kh.statement.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.common.Template;
import com.kh.statement.model.dao.MemberDao;
import com.kh.statement.model.dto.PasswordDTO;
import com.kh.statement.model.vo.Member;

public class MemberService {

	private MemberDao memberDao = new MemberDao();

	public int save(Member member) {
		/*
		 * Connection conn = JDBCTemplate.getConnection();
		 * 
		 * int result = new MemberDao().save(conn, member);
		 * 
		 * if(result > 0) {
		 * 		JDBCTemplate.close(conn);
		 * }
		 * 
		 * return result;
		 * 
		 */
		SqlSession session = Template.getSqlSeeion();
		
		int result = memberDao.save(session, member);
		
		if(result > 0) {
			session.commit();
		}
		
		session.close();
		
		return result;
	}
	
	public List<Member> findAll() {
		
		SqlSession session = Template.getSqlSeeion();
		
		List<Member> members = memberDao.findAll(session);
	
		session.close();
		
		return members;
	}
	
	public Member findById(String userId) {
		
		SqlSession session = Template.getSqlSeeion();
		
		Member member = memberDao.findById(session, userId);
		
		session.close();
		
		return member;
	}
	
	public List<Member> findByKeyword(String keyword) {
		
		SqlSession session = Template.getSqlSeeion();
		
		List<Member> members = memberDao.finByKeyword(session, keyword);
		
		session.close();
		
		return members;
		
	}
	
	public int update(PasswordDTO pd) {
		
		SqlSession session = Template.getSqlSeeion();
		
		int result = memberDao.update(session, pd);
		
		if(result > 0) {
			session.commit();
		}
		
		session.close();
		
		return result;
	}
	
	public int delete(Member member) {
		
		SqlSession session = Template.getSqlSeeion();
		
		int result = memberDao.delete(session, member);
			
		if(result > 0) {
			session.commit();
		}
		
		session.close();	
	
		return result;
	}
	
}
