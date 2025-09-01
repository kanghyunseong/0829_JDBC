package com.kh.statement.model.dao;

import java.sql.Connection;
import java.sql.Statement;

public class MovieDao {

	public void save(Movie movie) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		
		
		String sql = "INSERT INTO MOVIE VALUES ("
		        + movie.movieId + ", '"      
		        + movie.title + "', '"       
		        + movie.director + "', '"   
		        + movie.genre + "', "       
		        + "SYSDATE, "          
		        + movie.rating + ", "        
		        + movie.duration + ", "     
		        + movie.boxOffice + ")";     

		
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}
}
