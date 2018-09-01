package com.cg.fms.bean;

import java.util.Date;

public class TrainingProgram {

	int TrainingCode;
	int CourseCode;
	int FacultyCode;
	Date StartDate;
	Date EndDate;
	
	public int getTrainingCode() {
		return TrainingCode;
	}
	public void setTrainingCode(int trainingCode) {
		TrainingCode = trainingCode;
	}
	public int getCourseCode() {
		return CourseCode;
	}
	public void setCourseCode(int courseCode) {
		CourseCode = courseCode;
	}
	public int getFacultyCode() {
		return FacultyCode;
	}
	public void setFacultyCode(int facultyCode) {
		FacultyCode = facultyCode;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	
}
