package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
	
	private String studentId;
	private ArrayList<Course> courseTaken;
	private HashMap<String,Integer> semesterByYearAndSemester;
	
	public Student(String studentId) {
		this.studentId = studentId;
		courseTaken = new ArrayList<Course>();
	}
	
	public void addCourse(Course newRecord) {
		courseTaken.add(newRecord);
	}
	
	public HashMap<String,Integer>getSemesterByYearAndSemester(int startyear, int endyear){
		
		semesterByYearAndSemester = new HashMap<String,Integer>();
		int num=1; //count semester
		for(Course course: courseTaken) {
			if(course.getYearTaken()>=startyear && course.getYearTaken()<=endyear) {
				//String semesterByYear = Integer.toString(course.getYearTaken()) + "-" + Integer.toString(course.getSemesterCourseTaken());
				String YearAndSemester = course.getCourseTakenKey();
				if(semesterByYearAndSemester.containsKey(YearAndSemester)) { //if same YearAndSemester, pass
					;
				}
				else { // if there`s no same YearAndSemester, make semesterByYearAndSemester
					semesterByYearAndSemester.put(YearAndSemester,num);
					num++;
				}
			}else ;
		}
		return semesterByYearAndSemester;
	}
	
	
	public int getNumCourseInNthSemester(int semester, int startyear, int endyear) {
		int num = 0;
		
		for(Course course: courseTaken) {
			
			if(course.getYearTaken()>=startyear && course.getYearTaken()<=endyear) {
				if(semester == semesterByYearAndSemester.get(course.getCourseTakenKey())) {
					num++;
				}else ;
			}else ;
		}
		return num;
	}
	
	public boolean getTotalStudent(String semesterKey) {
		for(Course course: courseTaken) {
			if(semesterKey.equals(course.getCourseTakenKey())) {
				return true;
			}else ;
		}
		return false;
	}
	
	public boolean getNumOfStudentTakenCourse(String semesterKey, String coursecode) {
		for(Course course: courseTaken) {
			if(semesterKey.equals(course.getCourseTakenKey())) {
				if(coursecode.equals(course.getCourseCode())) {
					return true;
				}else ;
			}else ;
		}
		return false;
	}
	
	public String getCourseName(String courseCode) {
		String courseName = null;
		for(Course course: courseTaken) {
			if(courseCode.equals(course.getCourseCode())) {
				courseName = course.getCourseName(); 
				return courseName;
			}else ;
		}
		return null;
	}
}
