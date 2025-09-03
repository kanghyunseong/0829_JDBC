package com.kh.statement.controller;

import java.sql.Date;
import java.util.List;

import com.kh.statement.model.dao.MovieDao;
import com.kh.statement.model.vo.Movie;

public class MovieController {

	public int save(int movieId, String title, String director, String genre, int rating, int duration, int boxOffice) {

		Movie movie = new Movie(movieId, title, director, genre, rating, duration, boxOffice);

		int result = new MovieDao().save(movie);
		return result;
	}

	public List<Movie> findAll() {

		List<Movie> movies = new MovieDao().findAll();

		return movies;
	}
	
	public Movie findByTitle(String title) {
		
		Movie movie = new MovieDao().findByTitle(title);
		
		return movie;
	}
	
	public List<Movie> findByKeyword(String keyword) {
		// 결과값이 나중에 어떻게 돌아올까??
		// SELECT -> ResultSet -> Member(필드)-> List<Member>
		
		List<Movie> members = new MovieDao().findByKeyword(keyword);
		
		return members;
	
	}
}
