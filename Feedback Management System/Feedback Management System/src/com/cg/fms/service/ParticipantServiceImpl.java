package com.cg.fms.service;

import com.cg.fms.bean.Employee;
import com.cg.fms.dao.IParticipantDAO;
import com.cg.fms.dao.ParticipantDAOImpl;
import com.cg.fms.exception.FMSException;

public class ParticipantServiceImpl implements IParticipantService {

	IParticipantDAO dao = new ParticipantDAOImpl();
	public int provideFeedback(Employee employee) throws FMSException {
		
		 return dao.provideFeedback(employee);
		
	}
}
