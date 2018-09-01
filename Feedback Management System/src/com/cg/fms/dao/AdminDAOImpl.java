package com.cg.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.cg.fms.bean.CourseMaster;
import com.cg.fms.bean.FacultySkill;
import com.cg.fms.bean.TrainingProgram;
import com.cg.fms.dto.DBUtil;
import com.cg.fms.exception.FMSException;

public class AdminDAOImpl implements IAdminDAO {
	PreparedStatement delete_pstmt;
	PreparedStatement pstmt1, pstmt;
	
	int courseCode;
	
	int courseID;
	String courseName;
	int noOfDays;
	
	int facultyID;
	String skillSet;	
	
	int facultyCode;
	
	@Override
	public void facultyMaintenance() throws FMSException {		
		
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
			
			TrainingProgram tp = new TrainingProgram();
			FacultySkill fs = new FacultySkill();
			CourseMaster cm = new CourseMaster();		
			
			pstmt1 = conn.prepareStatement(IQuerymapper.faculty_view);
			
			System.out.println("****** FACULTY SKILL MAINTENANCE ******");
			System.out.println();
			System.out.println("FACULTY CODE"+"\t\t"+"SKILL SET"+"\t\t\t"+"COURSE CODE"+"\t\t"+"COURSE NAME"+"\t\t"+"NO OF DAYS");
			System.out.println("---------------------------------------------------------------------------------------------------------");
			rs = pstmt1.executeQuery();
			while(rs.next()){
				facultyCode = rs.getInt(1);
				skillSet = rs.getString(2);
				courseCode = rs.getInt(3);
				courseName = rs.getString(4);
				noOfDays = rs.getInt(5);
				System.out.println(facultyCode + "\t\t" + skillSet + "\t\t\t" + courseCode + "\t\t" + courseName+ "\t\t"+ noOfDays);				
			}
			
			/*tp.setFacultyCode(facultyCode);
			fs.setSkillSet(skillSet);
			tp.setCourseCode(courseCode);
			cm.setCourseName(courseName);
			cm.setNoOfDays(noOfDays);*/
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
		
	}

	@Override
	public void courseMaintenance() throws FMSException {		
		
		int choice;
		
		Scanner in = new Scanner(System.in);
		
		PreparedStatement pstmt;
		ResultSet rs;
		
		CourseMaster course = new CourseMaster();
		
		Connection conn = null;		
		try {
			conn = DBUtil.getConn();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			
			System.out.println("******COURSE MAINTENANCE******");
			System.out.println();
			System.out.println("Choose the action you want to perform");
			System.out.println("1)View Course details");
			System.out.println("2)Add new course");
			System.out.println("3)Delete course");
			
			choice = in.nextInt();
			
			switch(choice){
			case 1: System.out.println("Course ID"+"\t\t"+"Course Name"+"\t\t\t"+"No of days"+"\t\t");
					pstmt = conn.prepareStatement(IQuerymapper.course_maintenance);
					rs = pstmt.executeQuery();
			
					while(rs.next()){
						courseID = rs.getInt(1);
						courseName = rs.getString(2);
						noOfDays = rs.getInt(3); 
						System.out.println(courseID+"\t\t\t"+courseName+"\t\t\t"+noOfDays);
					}
					break;
			case 2: try {							
							System.out.println("Enter the Course Name");
							courseName = in.next();
							System.out.println("Enter the Course duration");
							noOfDays = in.nextInt();
					 } catch (Exception e) {
						throw new FMSException("Enter proper input type");
					}
				
					course.setCourseName(courseName);
					course.setNoOfDays(noOfDays);
					
					String add_course = "insert into course_master values(COURSEID_SEQ.NEXTVAL,?,?)";
					
					pstmt = conn.prepareStatement(add_course);
					
					pstmt.setString(1, courseName);
					pstmt.setInt(2, noOfDays);
						
					pstmt.executeUpdate();
					System.out.println("You have added a new course");
					break;
				
			case 3: System.out.println("Enter the course you want to delete");
					System.out.println("----------------------------------------");
					System.out.println("Course List");
					System.out.println("----------------------------------------");
					
					String course_list = "Select course_id, course_name from course_master";
					
					pstmt = conn.prepareStatement(course_list);
					ResultSet rs2;
					rs2 = pstmt.executeQuery();
					
					System.out.println("Course ID"+"\t"+"Course Name");
									
					while(rs2.next()){
						System.out.println(rs2.getInt(1)+"\t"+rs2.getString(2));
					}
					
					courseID = in.nextInt();
					String delete_course = "delete from course_master where course_id=?";
					delete_pstmt = conn.prepareStatement(delete_course);
					delete_pstmt.setInt(1, courseID);
					delete_pstmt.executeQuery();
					System.out.println("You have deleted course "+courseID);
					break;
			
				default: System.out.println("Please enter an option from 1-3");
			
			}		
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

}
