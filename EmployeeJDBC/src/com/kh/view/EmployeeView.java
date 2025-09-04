package com.kh.view;

import java.util.List;
import java.util.Scanner;

import com.kh.controller.EmployeeController;
import com.kh.model.vo.Employee;

public class EmployeeView {

	private Scanner sc = new Scanner(System.in);
	private EmployeeController ec = new EmployeeController();

	public void mainMenu() {

		while (true) {

			System.out.println("메뉴 선택");
			System.out.println("1. 회원 전체 조회");
			System.out.println("2. 부서명을 입력받아 부서가 동일한 사원 조회(총무부 입력시 총무부인 사원들만 조회되도록)");
			System.out.println("3. 직급명을 입력받아 직급이 동일한 사원 조회(과장 입력시 과장인 사원들만 조회되도록)");
			System.out.println("4. 사원 상세 조회(사번을 입력받아서 모든 컬럼 값 조회)");
			System.out.println("5. 급여가 높은 상위 다섯명 조회");
			System.out.println("6. 급여가 낮은 하위 다섯명 조회");
			System.out.println("7. 사원 추가 기능");
			System.out.println("8. 사원 퇴사 기능");
			System.out.println("0. 프로그램 종료");

			System.out.print("메뉴 선택하기 > ");

			int menuNo = sc.nextInt();
			sc.nextLine();

			switch (menuNo) {
			case 1:
				findAll();
				break;
			case 2:
				findByDept();
				break;
			case 3:
				findByJob();
				break;
			case 4:
				findByEmpId();
				break;
			case 5:
				costTop();
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 0:
				return;
			default:
				break;
			}

		}

	}

	private void findAll() {
		System.out.println();
		System.out.println("           [ 사원 전체 조회 ]");

		List<Employee> employees = ec.findAll();

		if (employees.isEmpty()) {
			System.out.println("조회된 사원이 없습니다.");
		} else {
			System.out.println("----------------------------------------");

			for (Employee e : employees) {
				String line = e.getEmpId() + " | " + e.getEmpName() + " | " + e.getSalary() + " | " + e.getDeptCode()
						+ " | " + e.getJobCode();
				System.out.println(line);
			}
			System.out.println("----------------------------------------");
			System.out.println("총 " + employees.size() + "명의 사원 정보가 조회되었습니다.");
		}

		System.out.println();
	}

	private void findByDept() {

		System.out.println("부서명으로 검색");
		System.out.print("검색할 부서 입력 > ");
		String searchDept = sc.nextLine();

		List<Employee> employees = ec.findByDept(searchDept);

		if (employees.isEmpty()) {
			System.out.println("조회된 사원이 없습니다.");
		} else {
			for (Employee e : employees) {
				String line = e.getEmpId() + " | " + e.getEmpName() + " | " + e.getSalary() + " | " + e.getDeptCode()
						+ " | " + e.getJobCode();
				System.out.println(line);
			}
		}
	}

	private void findByJob() {
		System.out.println("부서명으로 검색");
		System.out.print("검색할 직급 입력 > ");
		String searchJob = sc.nextLine();

		List<Employee> employees = ec.findByJob(searchJob);

		if (employees.isEmpty()) {
			System.out.println("조회된 사원이 없습니다.");
		} else {
			for (Employee e : employees) {
				String line = e.getEmpId() + " | " + e.getEmpName() + " | " + e.getSalary() + " | " + e.getDeptCode()
						+ " | " + e.getJobCode();
				System.out.println(line);
			}
		}
	}

	private void findByEmpId() {
		
		System.out.println("사원 상세 조회");
		System.out.print("사번을 입력해주세요 > ");
		String empId = sc.nextLine();
		
		Employee employee = ec.findByEmpId(empId);
		
		if(employee != null) {
			System.out.println("===========================");
			System.out.print("사번 : " + employee.getEmpId());
			System.out.print(", 사원명 : " + employee.getEmpName());
			System.out.print(", 급여 : " + employee.getSalary());
			System.out.print(", 부서명 : " + employee.getDeptCode());
			System.out.print(", 직급명 : " + employee.getJobCode());
		} else {
			System.out.println("조회 실패");
		}
		System.out.println();
	}

	private void costTop() {
	    System.out.println("====================");
	    System.out.println("급여 상위 5명");

	    List<Employee> employeeList = ec.costTop();

	    for (Employee emp : employeeList) {
	        System.out.println("사번: " + emp.getEmpId() 
	                         + ", 이름: " + emp.getEmpName() 
	                         + ", 급여: " + emp.getSalary());

	    }
	}
}
