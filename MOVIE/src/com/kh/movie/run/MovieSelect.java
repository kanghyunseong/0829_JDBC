package com.kh.movie.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MovieSelect {

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rest = null;

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

			rest = stmt.executeQuery(sql);

			while (rest.next()) {
				int movieId = rest.getInt("MOVIE_ID");
				String title = rest.getString("TITLE");
				String director = rest.getString("DIRECTOR");
				String genre = rest.getString("GENRE");
				String releaseDate = rest.getString("RELEASE_DATE");
				double rating = rest.getDouble("RATING");
				int duration = rest.getInt("DURATION");
				long boxOffice = rest.getLong("BOX_OFFICE");

				System.out.println("번호 : " + movieId + ", 영화제목 : " + title + ", 감독명 : " + director + ", 장르 : " + genre + ", 개봉일 : " + releaseDate
						+ ", 평점 : " + rating + ", 상영 시간 : " + duration + ", 분 박스오피스 수익 : " + boxOffice + "원");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rest != null) {
					rest.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
