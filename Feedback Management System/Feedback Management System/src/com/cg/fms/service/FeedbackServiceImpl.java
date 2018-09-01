package com.cg.fms.service;

import com.cg.fms.dao.FeedbackDAOImpl;
import com.cg.fms.dao.IFeedbackDAO;
import com.cg.fms.exception.FMSException;


public class FeedbackServiceImpl implements IFeedbackService {

	IFeedbackDAO dao = new FeedbackDAOImpl();
	@Override
	public int viewFeedback() throws FMSException {
		
		return dao.viewFeedback();
	}

}
