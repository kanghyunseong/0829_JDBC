package com.kh.statement.run;

import com.kh.common.JDBCTemplate;
import com.kh.statement.view.MovieView;

public class MovieRun {

	public static void main(String[] args) {

		
		JDBCTemplate.registDriver();
		MovieView mv = new MovieView();
		mv.mainMenu();
	}

}
