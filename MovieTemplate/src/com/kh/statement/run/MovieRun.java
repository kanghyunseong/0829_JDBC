package com.kh.statement.run;

import com.kh.common.JDBCTemplate;
import com.kh.common.Template;
import com.kh.statement.view.MovieView;

public class MovieRun {

	public static void main(String[] args) {
		
		JDBCTemplate.registDriver();Template.getSqlSeeion();
		
		MovieView mv = new MovieView();
		mv.mainMenu();
	}

}
