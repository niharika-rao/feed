package com.cg.fms.service;

import com.cg.fms.exception.FMSException;

public interface ICoordinatorService {

	void trainingMaintenance() throws FMSException;

	void participantEnroll() throws FMSException;

}
