package com.cg.fms.dao;

import com.cg.fms.exception.FMSException;

public interface ICoordinatorDAO {

	void trainingMaintenance() throws FMSException;

	void participantEnroll();
}
