package com.kh.movie.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MovieInsert {

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		int result = 0;

		Scanner sc = new Scanner(System.in);

		System.out.print("영화 고유번호를 입력해주세요 > ");
		int movieId = sc.nextInt();
		sc.nextLine();

		System.out.print("영화 제목을 입력해주세요 > ");
		String title = sc.nextLine();

		System.out.print("감독명을 입력해주세요 > ");
		String director = sc.nextLine();

		System.out.print("영화 장르를 입력해주세요 > ");
		String genre = sc.nextLine();

		System.out.print("영화 개봉일을 입력해주세요 > ");
		String releaseDate = sc.nextLine();

		System.out.print("영화 평점을 입력해주세요 > ");
		double rating = sc.nextDouble();
		sc.nextLine();

		System.out.print("상영 시간을 입력해주세요 > ");
		int duration = sc.nextInt();
		sc.nextLine();

		System.out.print("박스오피스 수익을 입력해주세요 > ");
		long boxOffice = sc.nextLong();

		String sql =  "INSERT INTO MOVIE VALUES ("
				+ movieId + ", '"
				+ title + "', '"
				+ director + "', '"
				+ genre + "', "
				+ "TO_DATE('" + releaseDate.trim() + "', 'YYYY-MM-DD'),"
				+ rating + ", "
				+ duration + ", "
				+ boxOffice +")";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 드라이버연결
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", "KHS02", "KHS021234");
			// 디비 연결

			stmt = conn.createStatement();
			// statement객체 생성
			
			result = stmt.executeUpdate(sql);
			// sql문 실행
			
			if (result > 0) {
				System.out.println("데이터 삽입 성공!");
			} else {
				System.out.println("데이터 삽입 실패!");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
