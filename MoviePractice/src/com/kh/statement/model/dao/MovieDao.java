package com.kh.statement.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.statement.model.DTO.MovieDTO;
import com.kh.statement.model.vo.Movie;

public class MovieDao {
	
	private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@115.90.212.20:10000:XE";
	private final String USERNAME = "KHS02";
	private final String PASSWORD = "KHS021234";

	public int save(Movie movie) {
		Connection conn = null;
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
						      , ?
						      , ?
						      , ?
						      , ?
						        )
				     """;
		try {
			Class.forName(DRIVER);

			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movie.getMovieId());
			pstmt.setString(2, movie.getTitle());
			pstmt.setString(3, movie.getDirector());
			pstmt.setString(4, movie.getGenre());
			pstmt.setInt(5, movie.getRating());
			pstmt.setInt(6, movie.getDuration());
			pstmt.setInt(7, movie.getBoxOffice());

			result = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<Movie> findAll() {

		Connection conn = null;
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
			Class.forName(DRIVER);

			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

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

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return movies;
	}

	public Movie findByTitle(String title) {
		
		Movie movie = null;
		Connection conn = null;
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
			
			Class.forName(DRIVER);
			
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
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
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movie;
	}

	public List<Movie> findByKeyword(String keyword) {
		
		List<Movie> movies = new ArrayList();
		Connection conn = null;
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
			Class.forName(DRIVER);

			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		try {
			if (rset != null) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movies;
	}

	public int update(MovieDTO md) {
		Connection conn =  null;
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
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(sql);
			
			conn.setAutoCommit(false);
			
			pstmt.setString(1, md.getNewTitle());
			pstmt.setString(2, md.getTitle());
			
			
			result = pstmt.executeUpdate();
			if(result > 0) {
				conn.commit();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int delete(Movie movie) {
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = """
						DELETE 
						  FROM
						        MOVIE
			             WHERE
			                    TITLE = ?
				     """;
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(sql);
			
			conn.setAutoCommit(false);
			
			pstmt.setString(1, movie.getTitle());
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
