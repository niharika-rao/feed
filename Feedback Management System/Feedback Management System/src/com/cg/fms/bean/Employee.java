package com.cg.fms.bean;

public class Employee {

	private int employeeID;
	private String password;	
	private String employeeName;
	private String role;	
	
	public Employee(){
		
	}
	
	public Employee(int employeeID, String password, String employeeName, String role){
		this.employeeID = employeeID;
		this.password = password;
		this.employeeName = employeeName;
		this.role = role;			
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}		
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	
	
}
