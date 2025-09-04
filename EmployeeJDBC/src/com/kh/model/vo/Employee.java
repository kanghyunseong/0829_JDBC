package com.kh.model.vo;

import java.sql.Date;
import java.util.Objects;

public class Employee {

	private String empId;
	private String empName;
	private String empNo;
	private String deptCode;
	private String jobCode;
	private String salLevel;
	private int salary;
	private Date entDate;
	private String entYn;

	public Employee() {
		super();
	}

	public Employee(String empId, String empName, String empNo, String deptCode, String jobCode, String salLevel,
			int salary, Date entDate, String entYn) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empNo = empNo;
		this.deptCode = deptCode;
		this.jobCode = jobCode;
		this.salLevel = salLevel;
		this.salary = salary;
		this.entDate = entDate;
		this.entYn = entYn;
	}
	
	

	public Employee(String empId, String empName, int salary) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.salary = salary;
	}

	public Employee(String empId, String empName, int salary, String jobCode, String deptCode) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.deptCode = deptCode;
		this.jobCode = jobCode;
		this.salary = salary;
	}

	public String getEmpId() {
		return empId;
	}

	public String getEmpName() {
		return empName;
	}

	public String getEmpNo() {
		return empNo;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public String getJobCode() {
		return jobCode;
	}

	public String getSalLevel() {
		return salLevel;
	}

	public int getSalary() {
		return salary;
	}

	public Date getEntDate() {
		return entDate;
	}

	public String getEntYn() {
		return entYn;
	}

	@Override
	public int hashCode() {
		return Objects.hash(deptCode, empId, empName, empNo, entDate, entYn, jobCode, salLevel, salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(deptCode, other.deptCode) && Objects.equals(empId, other.empId)
				&& Objects.equals(empName, other.empName) && Objects.equals(empNo, other.empNo)
				&& Objects.equals(entDate, other.entDate) && Objects.equals(entYn, other.entYn)
				&& Objects.equals(jobCode, other.jobCode) && Objects.equals(salLevel, other.salLevel)
				&& salary == other.salary;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empNo=" + empNo + ", deptCode=" + deptCode
				+ ", jobCode=" + jobCode + ", salLevel=" + salLevel + ", salary=" + salary + ", entDate=" + entDate
				+ ", entYn=" + entYn + "]";
	}

}