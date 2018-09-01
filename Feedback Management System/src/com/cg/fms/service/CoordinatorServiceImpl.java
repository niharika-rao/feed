package com.cg.fms.service;

import com.cg.fms.dao.CoordinatorDAOImpl;
import com.cg.fms.dao.ICoordinatorDAO;
import com.cg.fms.exception.FMSException;

public class CoordinatorServiceImpl implements ICoordinatorService {

	ICoordinatorDAO dao = new CoordinatorDAOImpl();
	@Override
	public void trainingMaintenance() throws FMSException{
		dao.trainingMaintenance();
	}

	@Override
	public void participantEnroll() {
		dao.participantEnroll();		
	}

}
