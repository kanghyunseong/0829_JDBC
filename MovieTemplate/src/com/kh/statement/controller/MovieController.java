package com.kh.statement.controller;

import java.util.List;

import com.kh.statement.model.DTO.MovieDTO;
import com.kh.statement.model.dao.MovieDao;
import com.kh.statement.model.vo.Movie;
import com.kh.statement.service.MovieService;

public class MovieController {

	public int save(int movieId, String title, String director, String genre, int rating, int duration, int boxOffice) {

		Movie movie = new Movie(movieId, title, director, genre, rating, duration, boxOffice);

		int result = new MovieService().save(movie);
		return result;
	}

	public List<Movie> findAll() {

		List<Movie> movies = new MovieService().findAll();

		return movies;
	}
	
	public Movie findByTitle(String title) {
		
		Movie movie = new MovieService().findByTitle(title);
		
		return movie;
	}
	
	public List<Movie> findByKeyword(String keyword) {
		
		List<Movie> movies = new MovieService().findByKeyword(keyword);
		
		return movies;
	
	}

	public int update(String title, String newTitle) {
		
		MovieDTO md = new MovieDTO(title, newTitle);
		
		int result = new MovieService().update(md);
		
		return result;
	}

	public int delete(String title) {
		
		Movie movie = new Movie();
		
		movie.setTitle(title);
		
		int result = new MovieService().delete(movie);
		
		return result;
	}
}
