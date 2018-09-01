package com.cg.fms.service;


import java.sql.SQLException;

import com.cg.fms.bean.Employee;
import com.cg.fms.dao.EmployeeDAOImpl;
import com.cg.fms.dao.IEmployeeDAO;
import com.cg.fms.exception.FMSException;

public class EmployeeServiceImpl implements IEmployeeService {

	IEmployeeDAO dao = new EmployeeDAOImpl();
	@Override
	public boolean login(Employee employee) throws FMSException, SQLException {
		
		return dao.login(employee);
	}

}
