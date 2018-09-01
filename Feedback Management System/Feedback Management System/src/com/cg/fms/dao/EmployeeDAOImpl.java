package com.cg.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.cg.fms.bean.Employee;
import com.cg.fms.bean.Feedback;
import com.cg.fms.dto.DBUtil;
import com.cg.fms.exception.FMSException;

/* In this class, the various functions of the user can be performed*/
public class EmployeeDAOImpl implements IEmployeeDAO {

	int employeeID;
	int empID;
	String employeeName;
	String pass;
	String role;
	
	
	
	//this function is used to validate the user's credentials
	@Override
	public boolean login(Employee employee) throws FMSException {
		
		//Database connection is done here 
		Connection conn = null;		
		try {
			conn = DBUtil.getConn();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		//first, the employeeid entered by the user is obtained from the bean class
		employeeID = employee.getEmployeeID();
		
		try{
		/* Based on the user_id entered, the rest of the details are first obtained from the database
		 * and if both the userId and password are correct then all of the parameters are set in the bean class
		 */
		PreparedStatement pstmt;
		
			try {
				pstmt = conn.prepareStatement(IQuerymapper.emp_detail);
			//the obtained employee id is passed to the query emp_detail
			pstmt.setInt(1, employeeID);
			
			ResultSet rs = pstmt.executeQuery();
			//all the attributes from the table employee_master are obtained and stored in local variables
			while(rs.next()){
				empID = rs.getInt(1);
				employeeName = rs.getString(2);
				pass = rs.getString(3);
				role = rs.getString(4);
			   }	
			} catch (SQLException e) {
				throw new FMSException("Could not connect to database");
			}
			/*now the user id obtained from the database is checked with the userId entered and if that is
			 *true, the password is also verified. If both of them are valid, then the values are set in the
			 *bean class it returns true to the service layer. If not true, the user Id and password values 
			 *set in the bean class is made to be null*/
			if(employeeID == empID){
				if(pass.equals(employee.getPassword())){
					employee.setRole(role);
					employee.setEmployeeName(employeeName);
					employee.setPassword(pass);
					employee.setEmployeeID(empID);					
					return true;
				}				
			}
			else{
				employee.setEmployeeID(0);
				employee.setPassword(null);
			}
		}catch(Exception exp){
			throw new FMSException("Could not login");
		}
		return false;
		
	}


	
}
