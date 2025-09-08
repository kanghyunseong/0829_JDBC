package com.kh.run;

import com.kh.common.Template;
import com.kh.view.EmployeeView;

public class EmployeeRun {

	public static void main(String[] args) {

		Template.getSqlSeeion();

		EmployeeView ec = new EmployeeView();
		ec.mainMenu();
	}
}
 