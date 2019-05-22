package edu.handong.analysis.datamodel;

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
	
	public Course(String line) {
		String[] values = line.split(",");
		studentId = values[0];
		yearMonthGraduated = values[1];
		firstMajor = values[2];
		secondMajor = values[3];
		courseCode = values[4];
		courseName = values[5];
		courseCredit = values[6];
		yearTaken = Integer.parseInt(values[7].trim());
		semesterCourseTaken = Integer.parseInt(values[8].trim());
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
}
