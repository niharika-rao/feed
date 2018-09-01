package com.cg.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.cg.fms.dto.DBUtil;
import com.cg.fms.exception.FMSException;

public class FeedbackDAOImpl implements IFeedbackDAO {

	@Override
	public int viewFeedback() throws FMSException {
		
		int choice = 0;
		PreparedStatement pstmt;
		Scanner in = new Scanner(System.in);

		Connection conn = null;		
		try {
			conn = DBUtil.getConn();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		
		do{
		System.out.println("*** FEEDBACK REPORT ***");
		System.out.println();
		System.out.println("1) Traning Feedback Report for month");
		System.out.println("2) Faculty Feedback Report for month");
		System.out.println("3) General Feedback Report");
		System.out.println("4) Exit");
		
		choice = in.nextInt();
		
		switch(choice){
		case 1: System.out.println();
				System.out.println("*** MONTHLY TRAINING PROGRAM FEEDBACK ***");
				System.out.println("-----------------------------------------");
				
				String training_feedback = "select ";
				
		
		}
		
		}while(choice>0 && choice<5);
		
		
		return 0;
	}

}
