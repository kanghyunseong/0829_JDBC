package com.kh.statement.view;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

import com.kh.statement.controller.MovieController;

public class MovieView {

	private Scanner sc = new Scanner(System.in);
	private MovieController mc = new MovieController();

	public void mainMenu() {
		System.out.println("--- 영화 검색 ---");

		System.out.print("1. 영화 추가 하기");
		System.out.print("2. 영화 전체 조회");
		System.out.print("3. 영화 이름으로 검색");
		System.out.print("4. 키워드로 검색하기");
		int menuNo = sc.nextInt();
		sc.nextLine();

		switch (menuNo) {
		case 1:
			save();
			break;
		case 2:
			//findAll();
			break;
		case 3:
			//findBytitle();
			break;
		case 4:
			//findByKeyword();
			break;
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

		
		
	}

}
