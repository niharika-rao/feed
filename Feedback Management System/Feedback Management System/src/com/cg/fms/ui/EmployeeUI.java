package com.cg.fms.ui;

import java.sql.SQLException;
import java.util.Scanner;

import com.cg.fms.bean.Employee;
import com.cg.fms.exception.FMSException;
import com.cg.fms.service.AdminServiceImpl;
import com.cg.fms.service.CoordinatorServiceImpl;
import com.cg.fms.service.EmployeeServiceImpl;
import com.cg.fms.service.FeedbackServiceImpl;
import com.cg.fms.service.IAdminService;
import com.cg.fms.service.ICoordinatorService;
import com.cg.fms.service.IEmployeeService;
import com.cg.fms.service.IFeedbackService;
import com.cg.fms.service.IParticipantService;
import com.cg.fms.service.ParticipantServiceImpl;

/* In this class, the user enters his user id and password and the menu will be displayed based 
 * on the role of the user which can be admin, coordinator or a participant. The admin has the privileges
 * to maintain faculty skill and course. The coordinator has the privileges to maintain the training program
 * and participant enrollment. The participant can login and enter the feedback for the training attended*/
public class EmployeeUI {

	static int employeeID;
	static String password;
	static String role;
		
	public static void main(String[] args) {
		int choice = 0, status = 0, action=0;
		int attempt=0;
		boolean check;
		
		Scanner in = new Scanner(System.in);
		IEmployeeService service = new EmployeeServiceImpl();
		IAdminService admin = new AdminServiceImpl();
		ICoordinatorService coordinator = new CoordinatorServiceImpl();
		IParticipantService participant = new ParticipantServiceImpl();
		IFeedbackService feedback = new FeedbackServiceImpl();
		Employee employee = new Employee();
				
		//The user enters their employee id and password
		System.out.println("*** Feedback Management System ***");
		
		do{
				
			System.out.println();
			System.out.println("=================LOGIN===================");
			System.out.println();
			System.out.println("Enter the Employee ID:");
			employeeID = in.nextInt();
			System.out.println("Enter the Password:");
			password = in.next();
				
		/*The entered credentials are set in the bean class and the object is sent 
		 * to the service layer which then calls the dao layer where the user is authenticated
		 */
		employee.setEmployeeID(employeeID);
		employee.setPassword(password);
		
		
		try{
		check = service.login(employee);	
		//if true is returned it means the entered userId and password is correct and the menu will be displayed accordingly
		
		
		if(check){
		//If the role is admin, then he can choose if he wants to perform faculty skill maintenance or course maintenance
		if(employee.getRole().equalsIgnoreCase("Admin")){
			System.out.println("============ Welcome "+employee.getEmployeeName()+ "============");
			
			do{
			System.out.println();
			System.out.println("--------------------------------------------");
			System.out.println(" Choose the action you want to perform: ");
			System.out.println("--------------------------------------------");
			System.out.println("1) Faculty Skill Maintenance");
			System.out.println("2) Course Maintenance");
			System.out.println("3) View Feedback Report");
			System.out.println("4) Exit");
			
			
			try {
				choice  = in.nextInt();
			} catch (Exception e) {
				System.err.println("Enter appropriate option type");
			}
			
			switch(choice){
			case 1 : admin.facultyMaintenance();
					 break;
			case 2: admin.courseMaintenance();
					break;
			case 3: feedback.viewFeedback();
					break;
			case 4: System.out.println("====== GOOD BYE ======"); 
				System.exit(0);
			
			default: System.out.println("Please choose an option from 1-4");					
			}
			}while(choice>0 && choice<4);
		}
		//if the user is a coordinator then he can choose to perform training program maintenance or particpant enrollment
		else if(employee.getRole().equalsIgnoreCase("Coordinator")){
			System.out.println("============ Welcome "+employee.getEmployeeName()+ "============");
			System.out.println("--------------------------------------------");
			System.out.println("Choose the action you want to perform: ");
			System.out.println("--------------------------------------------");
			System.out.println("1) Training Program Maintenance");
			System.out.println("2) Participant Enrolling");
			System.out.println("3) View Feedback Report");
			System.out.println("4) Exit");
			
			choice  = in.nextInt();
			
			switch(choice){
			case 1: coordinator.trainingMaintenance();
					 break;
			case 2: coordinator.participantEnroll();
					break;
			case 3: feedback.viewFeedback();
					break;
			case 4: System.out.println("====== GOOD BYE ======"); 
					System.exit(0);
			default: System.out.println("Please choose an option from 1-4");
			}
			
		}
		else if(employee.getRole().equalsIgnoreCase("Participant")){
			System.out.println("============ Welcome "+employee.getEmployeeName()+ "============");
			System.out.println("Please rate the training from 1-5 where");
			System.out.println("5 - Excellent:The ideal way of doing it");
			System.out.println("4 - Good:No pain areas or concern but could have been better");
			System.out.println("3 - Average: There are concerns but not significant");
			System.out.println("2 - Below Average: Needs improvement and is salvageable");
			System.out.println("1 - Poor: This way of doing things must change");
			
			status = participant.provideFeedback(employee);
			//if status is greater than 0 it means rows were updated and hence a display message can be displayed
			if(status>0){
				System.out.println("Thank you for the feedback!");
				System.exit(0);
			}
			
		  }
		}
		
		else{
			System.out.println("Wrong credentials");
			attempt++;
		 }
		
		}catch(FMSException e){
			System.err.println(e);
		} catch (SQLException e) {
			System.err.println(e);
		}
		
	}while(attempt<4);{
			System.out.println("*** You have exceeded your limit to login ***");
		}
	}

}
