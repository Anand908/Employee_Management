package com.admin.employeemanagement.bean;

import java.util.Date;

public class DummyEmployee 
{
	protected Integer sr;
	protected Integer employeeId;
	protected String name;
	protected String address;
	protected Byte gender;
	protected Double salary;
	protected Date birthdate;
	
	public DummyEmployee(Integer sr, Integer employeeId, String name, String address, Byte gender, Double salary,
			Date birthdate) {
		super();
		this.sr = sr;
		this.employeeId = employeeId;
		this.name = name;
		this.address = address;
		this.gender = gender;
		this.salary = salary;
		this.birthdate = birthdate;
	}

	public Integer getSr() {
		return sr;
	}

	public void setSr(Integer sr) {
		this.sr = sr;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Byte getGender() {
		return gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	
}
