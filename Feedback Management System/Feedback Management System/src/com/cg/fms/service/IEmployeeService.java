package com.cg.fms.service;

import java.sql.SQLException;

import com.cg.fms.bean.Employee;
import com.cg.fms.exception.FMSException;

public interface IEmployeeService{
	
	public boolean login(Employee employee) throws FMSException, SQLException;	
	
	
}
