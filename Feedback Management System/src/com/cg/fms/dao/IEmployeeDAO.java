package com.cg.fms.dao;

import java.sql.SQLException;

import com.cg.fms.bean.Employee;
import com.cg.fms.exception.FMSException;

public interface IEmployeeDAO {
	
	public boolean login(Employee employee) throws FMSException, SQLException;	
	public void viewFeedbackReport();
}
