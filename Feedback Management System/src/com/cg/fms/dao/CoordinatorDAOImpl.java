package com.cg.fms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cg.fms.dto.DBUtil;
import com.cg.fms.exception.FMSException;

public class CoordinatorDAOImpl implements ICoordinatorDAO {

	int trainingCode;
	int courseCode;
	String courseName;
	int facultyCode;
	Date startDate;
	Date endDate;
	
	@Override
	public void trainingMaintenance() throws FMSException {
		
		PreparedStatement pstmt;
		ResultSet rs;
		
		Connection conn = null;		
		try {
			conn = DBUtil.getConn();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			pstmt = conn.prepareStatement(IQuerymapper.training_maintenance);
			System.out.println("****** Training Maintenance ******");
			System.out.println();
						
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				trainingCode = rs.getInt(1);
				courseCode = rs.getInt(2);
				courseName = rs.getString(3);
				facultyCode = rs.getInt(4);
				startDate = rs.getDate(5);
				endDate = rs.getDate(6);
				System.out.println(trainingCode+" "+courseCode+" "+courseName+" "+facultyCode+" "+startDate+" "+endDate);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void participantEnroll() {
		// TODO Auto-generated method stub
		
	}

}
