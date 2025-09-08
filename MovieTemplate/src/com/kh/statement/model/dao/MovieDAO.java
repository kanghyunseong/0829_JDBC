package com.kh.statement.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.statement.model.dto.MovieDTO;
import com.kh.statement.model.vo.Movie;

public class MovieDAO {

	public int save(SqlSession session, Movie movie) {
		
		return session.insert("movieMapper.save", movie);
	}

	public List<Movie> findAll(SqlSession session) {
		return session.selectList("movieMapper.findAll");
	}

	public Movie findByTitle(SqlSession session, String title) {
		return session.selectOne("movieMapper.findByTitle", title);
	}

	public List<Movie> findByKeyword(SqlSession session, String keyword) {
		return session.selectList("movieMapper.findByKeyword", keyword);
	}

	public int update(SqlSession session, MovieDTO md) {
		return session.update("movieMapper.update", md);
	}

	public int delete(SqlSession session, Movie movie) {
		return session.delete("movieMapper.delete", movie);
	}

	
	
	
}