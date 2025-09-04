package com.kh.statement.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;

import java.sql.Connection;
import java.util.List;

import com.kh.common.JDBCTemplate;
import com.kh.statement.model.DTO.MovieDTO;
import com.kh.statement.model.dao.MovieDao;
import com.kh.statement.model.vo.Movie;

public class MovieService {

	private Connection conn = null;

	public MovieService() {
		super();
		this.conn = JDBCTemplate.getConnection();
	}
	
	public int save(Movie movie) {
		
		int result = new MovieDao().save(conn, movie);
	
		if (result > 0) {
			commit(conn);
		}
		JDBCTemplate.close(conn);
		return result;
		
	}

	public List<Movie> findAll() {
		
		List<Movie> movies = new MovieDao().findAll(conn);
		
		close(conn);
		
		return movies;

		
	}

	public Movie findByTitle(String title) {
		
		Movie movie = new MovieDao().findByTitle(conn, title);
		
		return movie;
		
	}

	public List<Movie> findByKeyword(String keyword) {
		
		List<Movie> movies = new MovieDao().findByKeyword(conn, keyword);
		
		close(conn);
		
		return movies;
		
		
	}	

	public int delete(Movie movie) {
		
		int result = new MovieDao().delete(conn, movie);
		
		close(conn);
		
		return result;
		
		
	}

	public int update(MovieDTO md) {
		
		int result = new MovieDao().update(conn, md);
		
		close(conn);
		
		return result;
	}
	
}
