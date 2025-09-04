package com.kh.run;

import com.kh.common.JDBCTemplate;
import com.kh.view.EmployeeView;

public class EmployeeRun {

	public static void main(String[] args) {

		JDBCTemplate.registDriver();

		EmployeeView ec = new EmployeeView();
		ec.mainMenu();
	}
}
