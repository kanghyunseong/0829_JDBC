package com.kh.statement.model.vo;

import java.sql.Date;
import java.util.Objects;

public class Movie {

	private int moiveId;
	private String title;
	private String director;
	private String genre;
	private Date releaseDate;
	private int rating;
	private int duration;
	private int boxOffice;
	public Movie() {
		super();
	}
	public Movie(int moiveId, String title, String director, String genre, Date releaseDate, int rating, int duration,
			int boxOffice) {
		super();
		this.moiveId = moiveId;
		this.title = title;
		this.director = director;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.rating = rating;
		this.duration = duration;
		this.boxOffice = boxOffice;
	}
	public int getMoiveId() {
		return moiveId;
	}
	public void setMoiveId(int moiveId) {
		this.moiveId = moiveId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getBoxOffice() {
		return boxOffice;
	}
	public void setBoxOffice(int boxOffice) {
		this.boxOffice = boxOffice;
	}
	
	
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(boxOffice, director, duration, genre, moiveId, rating, releaseDate, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return boxOffice == other.boxOffice && Objects.equals(director, other.director) && duration == other.duration
				&& Objects.equals(genre, other.genre) && moiveId == other.moiveId && rating == other.rating
				&& Objects.equals(releaseDate, other.releaseDate) && Objects.equals(title, other.title);
	}
	@Override
	public String toString() {
		return "Movie [moiveId=" + moiveId + ", title=" + title + ", director=" + director + ", genre=" + genre
				+ ", releaseDate=" + releaseDate + ", rating=" + rating + ", duration=" + duration + ", boxOffice="
				+ boxOffice + "]";
	}
	
	
	
	
}
