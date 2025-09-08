package com.kh.statement.view;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import com.kh.statement.controller.MovieController;
import com.kh.statement.model.vo.Movie;

public class MovieView {

	private Scanner sc = new Scanner(System.in);
	private MovieController mc = new MovieController();

	public void mainMenu() {
		while (true) {

			System.out.println("--- 영화 검색 ---");

			System.out.println("1. 영화 추가 하기 ");
			System.out.println("2. 영화 전체 조회 ");
			System.out.println("3. 영화 이름으로 검색 ");
			System.out.println("4. 키워드로 검색하기 ");
			System.out.println("5. 영화 정보 수정하기");
			System.out.println("6. 영화 삭제하기");
			System.out.print("메뉴를 선택해주세요 > ");
			int menuNo = sc.nextInt();
			sc.nextLine();

			switch (menuNo) {
			case 1:
				save();
				break;
			case 2:
				findAll();
				break;
			case 3:
				findByTitle();
				break;
			case 4:
				findByKeyword();
				break;
			case 5:
				update();
				break;
			case 6:
				delete();
				break;
			case 0:
				return;
			}
		}

	}

	private void save() {

		System.out.println("=== 영화 추가 하기 ===");
		System.out.print("영화 고유번호를 입력해주세요 > ");
		int movieId = sc.nextInt();
		sc.nextLine();

		System.out.print("영화 제목을 입력해주세요 > ");
		String title = sc.nextLine();

		System.out.print("감독명을 입력해주세요 > ");
		String director = sc.nextLine();

		System.out.print("영화 장르를 입력해주세요 > ");
		String genre = sc.nextLine();

		System.out.print("영화 평점을 입력해주세요 > ");
		int rating = sc.nextInt();
		sc.nextLine();

		System.out.print("상영 시간을 입력해주세요 > ");
		int duration = sc.nextInt();
		sc.nextLine();

		System.out.print("박스오피스 수익을 입력해주세요 > ");
		int boxOffice = sc.nextInt();

		int result = mc.save(movieId, title, director, genre, rating, duration, boxOffice);

		if (result > 0) {
			System.out.println("영화 추가에 성공하셨습니다. ");
		} else {
			System.out.println("영화 추가에 실패 하셨습니다. ");
		}
	}

	private void findAll() {

		System.out.println("\n영화 전체 조회");

		List<Movie> movies = mc.findAll();
		/*
		 * MOVIE_ID NUMBER PRIMARY KEY, TITLE VARCHAR2(20) NOT NULL, DIRECTOR
		 * VARCHAR2(20) NOT NULL, GENRE VARCHAR2(20) NOT NULL, RELEASE_DATE DATE NOT
		 * NULL, RATING NUMBER NOT NULL, DURATION NUMBER NOT NULL, BOX_OFFICE NUMBER NOT
		 * NULL
		 */
		if (movies.isEmpty()) {
			System.out.println("조회 결과가 존재하지 않습니다");
		} else {
			for (Movie movie : movies) {
				System.out.println("========================");
				System.out.print(movie.getMovieId() + "번 영화 정보");
				System.out.print("영화제목 : " + movie.getTitle() + ", ");
				System.out.print("영화 감독 : " + movie.getDirector() + ", ");
				System.out.print("영화 장르 : " + movie.getGenre() + ", ");
				System.out.print("개봉일 : " + movie.getReleaseDate() + ", ");
				System.out.print("평점 : " + movie.getRating() + ", ");
				System.out.print("상영 시간 : " + movie.getDuration() + ", ");
				System.out.print("박스오피스 수익 : " + movie.getBoxOffice() + ", ");
				System.out.println();

			}
		}
	}

	private void findByTitle() {

		System.out.println("\n 영화제목으로 검색하기");
		System.out.print("검색하실 제목을 입력해주세요 > ");
		String title = sc.nextLine();

		Movie movie = mc.findByTitle(title);

		if (movie != null) {
			System.out.println("========================");
			System.out.print(movie.getMovieId() + "번 영화 정보");
			System.out.print("영화제목 : " + movie.getTitle() + ", ");
			System.out.print("영화 감독 : " + movie.getDirector() + ", ");
			System.out.print("영화 장르 : " + movie.getGenre() + ", ");
			System.out.print("개봉일 : " + movie.getReleaseDate() + ", ");
			System.out.print("평점 : " + movie.getRating() + ", ");
			System.out.print("상영 시간 : " + movie.getDuration() + ", ");
			System.out.print("박스오피스 수익 : " + movie.getBoxOffice() + ", ");
			System.out.println();
		} else {
			System.out.println("존재하지 않는 영화입니다.");
		}
	}

	private void findByKeyword() {

		System.out.print("\n영화 이름 키워드로 검색");
		System.out.print("검색하고자 하는 키워드를 입력해주세요 > ");
		String keyword = sc.nextLine();

		List<Movie> movies = mc.findByKeyword(keyword);
		if (movies.isEmpty()) {
			System.out.println("조회 결과가 존재하지 않습니다. ");
		} else {
			for (int i = 0; i < movies.size(); i++) {
				System.out.println((i + 1) + "번 째 조회 결과!");
				System.out.println(movies.get(i).toString());
			}
		}
	}

	private void update() {

		System.out.println("\n영화제목 수정하기");
		System.out.print("수정하고자 하는 영화 제목을 입력해주세요 > ");
		String title = sc.nextLine();

		System.out.print("바꿀 영화 제목을 입력해라 > ");
		String newTitle = sc.nextLine();

		int result = mc.update(title, newTitle);

		if (result > 0) {
			System.out.println("영화 제목 변경 성공");
		} else {
			System.out.println("에잉 쯧쯧 ");
		}
	}

	private void delete() {
		System.out.println("안녕히가세요 ~ ");
		System.out.print("삭제할 영화제목 > ");
		String title = sc.nextLine();

		int result = mc.delete(title);

		if (result > 0) {
			System.out.println("성공했습니다.");
		} else {
			System.out.println("실패했습니다.");
		}
	}

}
