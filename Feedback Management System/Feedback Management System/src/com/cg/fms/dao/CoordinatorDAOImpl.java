package com.cg.fms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.cg.fms.bean.TrainingProgram;
import com.cg.fms.dto.DBUtil;
import com.cg.fms.exception.FMSException;

public class CoordinatorDAOImpl implements ICoordinatorDAO {

	int trainingCode;
	int courseCode;
	String courseName;
	int facultyCode;
	Date startDate;
	Date endDate;
	
	int employeeID;
	
	PreparedStatement pstmt,pstmt1,pstmt2;
	ResultSet rs,rs1;
	Connection conn = null;
	
	Scanner in = new Scanner(System.in);
	
	@Override
	public void trainingMaintenance() throws FMSException {
		
		int choice = 0;
		
		TrainingProgram training = new TrainingProgram();		
				
		try {
			conn = DBUtil.getConn();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		
		try {
			do{
			System.out.println();
			System.out.println("****** Training Maintenance ******");
			System.out.println();
			System.out.println("Choose the action you want to perform");
			System.out.println("1) View training details");
			System.out.println("2) Add new training");
			System.out.println("3) Delete training");
			System.out.println("4) Exit");
			
			
			choice = in.nextInt();
						
			switch(choice){
			
			case 1: pstmt = conn.prepareStatement(IQuerymapper.training_maintenance);			
					rs = pstmt.executeQuery();
					System.out.println("Training Code"+"\t"+"Course Code"+"\t"+"Course Name"+"\t"+"Faculty Code"+"\t"+"Start Date"+"\t"+"End Date");
					System.out.println("-----------------------------------------------------------------------------------");
					while(rs.next()){
						trainingCode = rs.getInt(1);
						courseCode = rs.getInt(2);
						courseName = rs.getString(3);
						facultyCode = rs.getInt(4);
						startDate = rs.getDate(5);
						endDate = rs.getDate(6);
						System.out.println(trainingCode+"\t\t"+courseCode+"\t\t" +courseName +"\t"+facultyCode+"\t"+startDate+"\t"+endDate);
					}
					break;
			case 2: System.out.println();
					System.out.println("Choose course to add to the training program");
					System.out.println("Course List");
					System.out.println("----------------------------------------");				
					
					pstmt = conn.prepareStatement(IQuerymapper.course_list);
					ResultSet rs2;
					rs2 = pstmt.executeQuery();
					
					System.out.println("----------------------------------------");
					System.out.println("Course ID"+"\t\t"+"Course Name");
					System.out.println("----------------------------------------");
					
					while(rs2.next()){
						System.out.println(rs2.getInt(1)+"\t\t"+rs2.getString(2));
					}
					
					courseCode = in.nextInt();
					
					System.out.println("Choose Faculty");
					
					System.out.println("Faculty List");
					System.out.println("----------------------------------------");				
					
					String faculty_list = "select * from faculty_skill";
					
					pstmt1 = conn.prepareStatement(faculty_list);
					ResultSet rs1;
					rs1 = pstmt1.executeQuery();
					
					System.out.println("----------------------------------------");
					System.out.println("Faculty ID"+"\t\t"+"Skills");
					System.out.println("----------------------------------------");
					
					while(rs1.next()){
						System.out.println(rs1.getInt(1)+"\t\t"+rs1.getString(2));
					}
					
					System.out.println("Enter start date in the format (DD/MM/YYYY)");
					
					
					break;
					
			case 3: System.out.println();
					System.out.println("Enter the training you want to delete");
					System.out.println("----------------------------------------");
					
					String training_list = "select training_code from training_program";
					pstmt = conn.prepareStatement(training_list);
					rs = pstmt.executeQuery();
					
					while(rs.next()){
						System.out.println(rs.getInt(1));
					}
					
					trainingCode = in.nextInt();
					training.setTrainingCode(trainingCode);
					String delete_training = "delete from training_program where training_code=?";
					pstmt1 = conn.prepareStatement(delete_training);
					pstmt1.setInt(1, training.getTrainingCode());
					pstmt1.executeQuery();
					System.out.println("You have deleted the training "+trainingCode);	
					break;
			case 4: continue;
			default: System.out.println("Please enter an option from 1-3");
			}	
			
			}while(choice>0 && choice<4);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void participantEnroll() throws FMSException {
		
		PreparedStatement training_list_pstmt;
		ResultSet rs;
		
		try {
			conn = DBUtil.getConn();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("*** PARTICIPANT ENROLL ***");
		System.out.println("=====================================================");
		
		System.out.println("---------------------------------------------------");
		System.out.println("Training Programs");
		System.out.println("---------------------------------------------------");
		String training_list = "select tp.training_code, cm.course_id, cm.course_name from training_program tp, course_master cm where tp.course_code=cm.course_id";
		System.out.println("Training Code"+"\t\t"+"Course Code"+"\t\t"+"Course Name");
		try {
			training_list_pstmt = conn.prepareStatement(training_list);
			rs = training_list_pstmt.executeQuery();
			while(rs.next()){
				System.out.print(rs.getInt(1)+"\t\t\t");
				System.out.print(rs.getInt(2)+"\t\t");
				System.out.print(rs.getString(3)+"\t\t");
			}
			
			System.out.println();
			System.out.println("Choose from the training ID to enroll the participant to");
			 trainingCode = in.nextInt();
			 
		System.out.println("Choose the participant to enroll");
		System.out.println("---------------------------------------------------");
		System.out.println("Participant list");
		System.out.println("---------------------------------------------------");
		
		String participant_list = "select employee_id from employee_master where role='Participant'";
		pstmt1 = conn.prepareStatement(participant_list);
		rs1 = pstmt1.executeQuery();
		while(rs1.next()){
			System.out.println(rs1.getInt(1));
		}
				 
		employeeID = in.nextInt();
		
		String participant_enroll = "insert into training_participant_enroll values(?,?)";
		pstmt2 = conn.prepareStatement(participant_enroll);
		pstmt2.setInt(1, trainingCode);
		pstmt2.setInt(2, employeeID);
		
		pstmt2.executeQuery();
		System.out.println();
		System.out.println("You have enrolled participant with ID "+employeeID+" to the training "+trainingCode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
}
