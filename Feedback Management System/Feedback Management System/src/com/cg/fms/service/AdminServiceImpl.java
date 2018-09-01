package com.cg.fms.service;

import com.cg.fms.dao.AdminDAOImpl;
import com.cg.fms.dao.IAdminDAO;
import com.cg.fms.exception.FMSException;

public class AdminServiceImpl implements IAdminService{

	IAdminDAO dao = new AdminDAOImpl();
	@Override
	public void facultyMaintenance() throws FMSException{
		dao.facultyMaintenance();
		
	}

	@Override
	public void courseMaintenance() throws FMSException {
		dao.courseMaintenance();		
	}

}
