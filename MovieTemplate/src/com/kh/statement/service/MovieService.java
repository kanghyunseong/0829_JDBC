package com.kh.statement.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.common.Template;
import com.kh.statement.model.dao.MovieDAO;
import com.kh.statement.model.dto.MovieDTO;
import com.kh.statement.model.vo.Movie;

public class MovieService {

	private MovieDAO movieDao = new MovieDAO();

	public int save(Movie movie) {

		SqlSession session = Template.getSqlSeeion();

		int result = movieDao.save(session, movie);

		if (result > 0) {
			session.commit();
		}

		session.close();

		return result;
	}

	public List<Movie> findAll() {

		SqlSession session = Template.getSqlSeeion();

		List<Movie> movie = movieDao.findAll(session);

		session.close();

		return movie;
	}

	public Movie findByTitle(String title) {

		SqlSession session = Template.getSqlSeeion();

		Movie movie = movieDao.findByTitle(session, title);

		session.close();

		return movie;
	}

	public List<Movie> findByKeyword(String keyword) {

		SqlSession session = Template.getSqlSeeion();

		List<Movie> movie = movieDao.findByKeyword(session, keyword);

		session.close();

		return movie;
	}

	public int update(MovieDTO md) {

		SqlSession session = Template.getSqlSeeion();

		int result = movieDao.update(session, md);

		if (result > 0) {
			session.commit();
		}

		session.close();

		return result;
	}

	public int delete(Movie movie) {

		SqlSession session = Template.getSqlSeeion();
		int result = movieDao.delete(session, movie);

		if (result > 0) {
			session.commit();
		}
		return result;
	}

}
