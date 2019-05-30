package edu.handong.analysis.datamodel;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

public class Course {
	
	private String studentId;
	private String yearMonthGraduated;
	private String firstMajor;
	private String secondMajor;
	private String courseCode;
	private String courseName;
	private String courseCredit;
	private int yearTaken;
	private int semesterCourseTaken;
	
	public Course(CSVRecord record) {
		studentId = record.get("STudentID");
		yearMonthGraduated = record.get("YearMonthGraduated");
		firstMajor = record.get("FistMajor");
		secondMajor = record.get("SecondMajor");
		courseCode = record.get("CourseCode");
		courseName = record.get("CourseName");
		courseCredit = record.get("CourseCredit");
		yearTaken = Integer.parseInt(record.get("YearTaken"));
		semesterCourseTaken = Integer.parseInt(record.get("SemesterTaken"));
	}
	
	public String getStudentId() {
		return studentId;
	}
	public int getYearTaken() {
		return yearTaken;
	}
	public int getSemesterCourseTaken() {
		return semesterCourseTaken;
	}
	public String getCourseTakenKey() {
		String courseTakenKey = Integer.toString(yearTaken) + "-" + Integer.toString(semesterCourseTaken);
		return courseTakenKey;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
}
