package com.kh.statement.view;

import java.util.Scanner;

import com.kh.statement.controller.BookController;
import com.kh.statement.model.vo.Book;

public class BookView {

	private Scanner sc = new Scanner(System.in);
	private BookController bc = new BookController();
	
	public void mainMenu() {

		System.out.println("책 저장 프로그램!");
		System.out.println("====================================");
		System.out.print("1. 저장할 책 추가하기 ! > ");
		System.out.print("2. 저장할 전체 도서 출력하기 ! > ");
		System.out.print("3. 저장할 책 제목으로 검색 ! > ");
		System.out.print("4. 저장할 책 정보 수정 ! > ");
		System.out.print("5. 저장할 책 제목 키워드로 검색 ! > ");
		System.out.print("6. 저장할 책 삭제 ! > ");
		System.out.print("0. 프로그램 종료 ! > ");

		int menuNo = sc.nextInt();

		switch (menuNo) {
		case 1:
			save();
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 0:
			return;
		}
	}
	
	private void save() {
		
		System.out.println("도서 추가 서비스");
		System.out.print("추가하실 도서를 입력해주세요 > ");
		
		
		
		Book book = new Book();
		
	}
}
