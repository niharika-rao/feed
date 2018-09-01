package com.cg.fms.dao;

public interface IQuerymapper {

	static String emp_detail = "Select * from employee_master where Employee_ID = ?";
	static String training_Part_Query = "select Training_Code from training_participant_enroll where Participant_ID =?";
	static String feedback_entry = "insert into FEEDBACK_MASTER values(?,?,?,?,?,?,?,?,?)";
	static String faculty_maintenance = "create view faculty_maintenance as select  tp.faculty_code,fs.skill_set, tp.course_code, cm.course_name, cm.no_of_days from training_program tp, course_master cm, faculty_skill fs where tp.course_code=cm.course_id and tp.faculty_code=fs.faculty_id";
	static String faculty_view = "select * from faculty_maintenance";
	static String course_maintenance = "select * from course_master";
	static String training_maintenance = "select tp.training_code,tp.course_code,cm.course_name,tp.faculty_code,tp.start_date,tp.end_date from training_program tp, course_master cm where tp.course_code=cm.course_id";
	static String add_course = "insert into course_master values(COURSEID_SEQ.NEXTVAL,?,?)";
	static String course_list = "Select course_id, course_name from course_master";
	static String delete_course = "delete from course_master where course_id=?";
										
		}
