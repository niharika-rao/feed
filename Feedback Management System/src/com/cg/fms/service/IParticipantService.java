package com.cg.fms.service;

import com.cg.fms.bean.Employee;
import com.cg.fms.exception.FMSException;

public interface IParticipantService {

	public int provideFeedback(Employee employee) throws FMSException;
}
