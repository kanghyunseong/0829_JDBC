package com.kh.statement.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.JDBCTemplate;
import com.kh.statement.model.DTO.MovieDTO;
import com.kh.statement.model.vo.Movie;

public class MovieDao {
	
	public int save(Connection conn, Movie movie) {
		
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = """
						INSERT
						  INTO
						       MOVIE
						 VALUES 
						        (
						        ?
						      , ?
						      , ?
						      , ?
						      , SYSDATE
						      , ?
						      , ?
						      , ?
						        )
				     """;
		try {


			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movie.getMovieId());
			pstmt.setString(2, movie.getTitle());
			pstmt.setString(3, movie.getDirector());
			pstmt.setString(4, movie.getGenre());
			pstmt.setInt(5, movie.getRating());
			pstmt.setInt(6, movie.getDuration());
			pstmt.setInt(7, movie.getBoxOffice());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	public List<Movie> findAll(Connection conn) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		List<Movie> movies = new ArrayList<>();

		String sql = """
					SELECT
					       MOVIE_ID
					     , TITLE
					     , DIRECTOR
					     , GENRE
					     , RELEASE_DATE
					     , RATING
					     , DURATION
					     , BOX_OFFICE
					 FROM
					      MOVIE
					ORDER
					   BY
					      MOVIE_ID DESC
				""";
		try {


			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Movie movie = new Movie();
				
				movie.setMovieId(rset.getInt("MOVIE_ID"));
				movie.setTitle(rset.getString("TITLE"));
				movie.setDirector(rset.getString("DIRECTOR"));
				movie.setGenre(rset.getString("GENRE"));
				movie.setReleaseDate(rset.getDate("RELEASE_DATE"));
				movie.setRating(rset.getInt("RATING"));
				movie.setDuration(rset.getInt("DURATION"));
				movie.setBoxOffice(rset.getInt("BOX_OFFICE"));

				movies.add(movie);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return movies;
	}

	public Movie findByTitle(Connection conn, String title) {
		
		Movie movie = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = """
					SELECT
					       MOVIE_ID
					     , TITLE
					     , DIRECTOR
					     , GENRE
					     , RELEASE_DATE
					     , RATING
					     , DURATION
					     , BOX_OFFICE
					 FROM
					      MOVIE
					WHERE
					      TITLE = ?
				    """;
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				movie = new Movie(rset.getInt("MOVIE_ID")
						        , rset.getString("TITLE")
						        , rset.getString("DIRECTOR")
						        , rset.getString("GENRE")
						        , rset.getDate("RELEASE_DATE")
						        , rset.getInt("RATING")
						        , rset.getInt("DURATION")
						        , rset.getInt("BOX_OFFICE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return movie;
	}

	public List<Movie> findByKeyword(Connection conn, String keyword) {
		
		List<Movie> movies = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = """
				SELECT
				       MOVIE_ID
				     , TITLE
				     , DIRECTOR
				     , GENRE
				     , RELEASE_DATE
				     , RATING
				     , DURATION
				     , BOX_OFFICE
				 FROM
				       MOVIE
				 WHERE
				       TITLE LIKE '%' || ? || '%'
				 ORDER
				    BY
				        MOVIE_ID DESC
				     """; 
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			
			rset = pstmt.executeQuery();

			while (rset.next()) {
				
				movies.add(new Movie(rset.getInt("MOVIE_ID")
				         , rset.getString("TITLE")
				         , rset.getString("DIRECTOR")
				         , rset.getString("GENRE")
				         , rset.getDate("RELEASE_DATE")
				         , rset.getInt("RATING")
				         , rset.getInt("DURATION")
				         , rset.getInt("BOX_OFFICE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return movies;
	}

	public int update(Connection conn, MovieDTO md) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = """
						UPDATE
				              MOVIE
				          SET
				              TITLE = ?
				        WHERE 
				              TITLE = ?
				     """;
		try {
			pstmt = conn.prepareStatement(sql);
			
			conn.setAutoCommit(false);
			
			pstmt.setString(1, md.getNewTitle());
			pstmt.setString(2, md.getTitle());
			
			
			result = pstmt.executeUpdate();
			if(result > 0) {
				conn.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		}
		return result;
	}

	public int delete(Connection conn, Movie movie) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = """
						DELETE 
						  FROM
						        MOVIE
			             WHERE
			                    TITLE = ?
				     """;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			conn.setAutoCommit(false);
			
			pstmt.setString(1, movie.getTitle());
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}
