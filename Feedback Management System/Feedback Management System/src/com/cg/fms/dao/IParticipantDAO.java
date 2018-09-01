package com.cg.fms.dao;

import com.cg.fms.bean.Employee;
import com.cg.fms.exception.FMSException;

public interface IParticipantDAO {

	int provideFeedback(Employee employee) throws FMSException;

}
