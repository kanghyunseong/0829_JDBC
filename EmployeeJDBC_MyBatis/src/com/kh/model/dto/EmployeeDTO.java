package com.kh.model.dto;

import java.util.Objects;

public class EmployeeDTO {

	private String empId;
	private String deptCode;
	private String jobCode;
	private int salary;

	public EmployeeDTO() {
		super();
	}

	public EmployeeDTO(String empId, String deptCode, String jobCode, int salary) {
		super();
		this.empId = empId;
		this.deptCode = deptCode;
		this.jobCode = jobCode;
		this.salary = salary;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public int hashCode() {
		return Objects.hash(deptCode, empId, jobCode, salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDTO other = (EmployeeDTO) obj;
		return Objects.equals(deptCode, other.deptCode) && Objects.equals(empId, other.empId)
				&& Objects.equals(jobCode, other.jobCode) && salary == other.salary;
	}

}
