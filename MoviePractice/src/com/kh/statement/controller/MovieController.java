package com.kh.statement.controller;

import java.sql.Date;

import com.kh.statement.model.dao.MovieDao;
import com.kh.statement.model.vo.Movie;

public class MovieController {

	public void save(int movieId, String title, String director, String genre, Date releaseDate, int rating, int duration, int boxOffice) {
		
		Movie movie = new Movie (movieId, title, director, genre, releaseDate, rating, duration, boxOffice);
	
		int result = new MovieDao().save(movie);
	}
}
