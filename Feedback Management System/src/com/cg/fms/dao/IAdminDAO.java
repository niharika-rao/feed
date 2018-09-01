package com.cg.fms.dao;

import com.cg.fms.exception.FMSException;

public interface IAdminDAO {

	public void facultyMaintenance() throws FMSException;
	public void courseMaintenance() throws FMSException;
}
