package com.cg.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.cg.fms.bean.Employee;
import com.cg.fms.bean.Feedback;
import com.cg.fms.dto.DBUtil;
import com.cg.fms.exception.FMSException;

public class ParticipantDAOImpl implements IParticipantDAO {


	int trainingCode;
	int participantID;
	int FBPrsComm;
	int FBClrfyDbts;
	int FBTm;
	int FBHandout;
	int FBHwSwNtwk;
	String comments;
	String suggestions;	
	
		//this method is for the participant to provide feedback for the training he attended
		@Override
		public int provideFeedback(Employee employee) throws FMSException {
			
			int n = 0;
			
			Feedback feed = new Feedback();
			PreparedStatement pstmt;
			PreparedStatement pstmt1;		
			ResultSet rs;
			
			Scanner in = new Scanner(System.in);
			
			//to establish connection with the database
			Connection conn = null;		
			try {
				conn = DBUtil.getConn();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			participantID = employee.getEmployeeID();
			
			try {			
				//the training id is retrieved from the database based on the employee id
				pstmt = conn.prepareStatement(IQuerymapper.training_Part_Query);			
				pstmt.setInt(1, participantID);			
				
				rs = pstmt.executeQuery();			
				while(rs.next()){
					trainingCode = rs.getInt(1);
				   }
				
				//the values so obtained are set in the bean class
				feed.setTrainingCode(trainingCode);
				feed.setParticipantID(participantID);
				
				System.out.println();
				System.out.println("============ Enter Feedback for training "+feed.getTrainingCode()+"============");
				//The feedback based on different parameters is then obtained from the participant
				try{
				System.out.println("1) Presentation and communication skills of faculty	");			
				FBPrsComm = in.nextInt();			
				System.out.println("2) Ability to clarify doubts and explain difficult points	");			
				FBClrfyDbts = in.nextInt();			
				System.out.println("3) Time management in completing the contents 	");			
				FBTm = in.nextInt();			
				System.out.println("4) Handout provided(Student Guide)	");			
				FBHandout = in.nextInt();			
				System.out.println("5) Hardware, software and network availability	");			
				FBHwSwNtwk = in.nextInt();
				System.out.println("Comments");
				String dummy = in.nextLine();
				comments = in.nextLine();
				System.out.println("Suggestions");
				suggestions = in.nextLine();
				}catch(Exception e){
					throw new FMSException("Enter proper input");
				}
				//The feedback entered by the user is then set in the bean class
				feed.setFBPrsComm(FBPrsComm);
				feed.setFBClrfyDbts(FBClrfyDbts);
				feed.setFBTm(FBTm);
				feed.setFBHandout(FBHandout);
				feed.setFBHwSwNtwk(FBHwSwNtwk);
				feed.setComments(comments);
				feed.setSuggestions(suggestions);			
				
				//The feedback entered by the participant for a particular training and faculty 
				//is then stored in the feedback_master table
				pstmt1 = conn.prepareStatement(IQuerymapper.feedback_entry);
				pstmt1.setInt(1, feed.getTrainingCode());
				pstmt1.setInt(2, feed.getParticipantID());
				pstmt1.setInt(3, feed.getFBPrsComm());
				pstmt1.setInt(4, feed.getFBClrfyDbts());
				pstmt1.setInt(5, feed.getFBTm());
				pstmt1.setInt(6, feed.getFBHandout());
				pstmt1.setInt(7, feed.getFBHwSwNtwk());
				pstmt1.setString(8, feed.getComments());
				pstmt1.setString(9, feed.getSuggestions());
				n = pstmt1.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			}
					
			return n;
			
		}
}
