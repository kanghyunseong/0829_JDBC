package com.kh.statement.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.statement.model.vo.Movie;

public class MovieDao {

	public int save(Movie movie) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;

		String sql = "INSERT INTO MOVIE VALUES (" + movie.getMovieId() + ", '" + movie.getTitle() + "', '"
				+ movie.getDirector() + "', '" + movie.getGenre() + "', " + "SYSDATE, " + movie.getRating() + ", "
				+ movie.getDuration() + ", " + movie.getBoxOffice() + ")";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", "KHS02", "KHS021234");

			stmt = conn.createStatement();

			result = stmt.executeUpdate(sql);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null) {
					stmt.close();
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
		Statement stmt = null;
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
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", "KHS02", "KHS021234");

			stmt = conn.createStatement();

			rset = stmt.executeQuery(sql);

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
				if (stmt != null) {
					stmt.close();
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
					      TITLE =
				""";
		sql += "'" + title + "'";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", "KHS02",
					"KHS021234"); Statement stmt = conn.createStatement(); ResultSet rset = stmt.executeQuery(sql)) {

				if (rset.next()) {
					movie = new Movie(rset.getInt("MOVIE_ID"), rset.getString("TITLE"), rset.getString("DIRECTOR"),
							rset.getString("GENRE"), rset.getDate("RELEASE_DATE"), rset.getInt("RATING"),
							rset.getInt("DURATION"), rset.getInt("BOX_OFFICE"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return movie;
	}

	public List<Movie> findByKeyword(String keyword) {
		// 0) 필요한 변수들
		List<Movie> movies = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		String sql =
			    "SELECT MOVIE_ID, TITLE, DIRECTOR, GENRE, RELEASE_DATE, RATING, DURATION, BOX_OFFICE " +
			    "FROM MOVIE " +
			    "WHERE TITLE LIKE '%" + keyword + "%' " +
			    "ORDER BY MOVIE_ID DESC";



		try {
			// 1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2) Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", "KHS02", "KHS021234");

			// 3) Statement 객체 생성
			stmt = conn.createStatement();

			// 4, 5) SQL문 실행 후 결과 받아오기
			rset = stmt.executeQuery(sql);

			// 6) ResultSet객체에서 각 행에 접근하면서
			// 조회 결과가 있다면 컬럼의 값을 뽑아서 VO객체에 필드에 대입한 뒤
			// List의 요소로 추가함

			while (rset.next()) {
				movies.add(new Movie(rset.getInt("MOVIE_ID"), rset.getString("TITLE"), rset.getString("DIRECTOR"),
						rset.getString("GENRE"), rset.getDate("RELEASE_DATE"), rset.getInt("RATING"),
						rset.getInt("DURATION"), rset.getInt("BOX_OFFICE")));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7) 자원반납 => 생성된 순서의 역순으로 close()를 호출
		}
		try {
			if (rset != null) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (stmt != null) {
				stmt.close();
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
		// 8) 결과 반환
		return movies;
	}

}
