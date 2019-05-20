package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
	
	private String studentId;
	private ArrayList<Course> courseTaken;
	private HashMap<String,Integer> semesterByYearAndSemester;
	
	public Student(String studentId) {
		courseTaken = new ArrayList<Course>();
	}
	
	public void addCourse(Course newRecord) {
		courseTaken.add(newRecord);
	}
	
	public HashMap<String,Integer>getsemesterByYearAndSemester(){
		
		return null;
	}
	
	public int getNumCourseInNthSemester(int semester) {
		
		
		return null;
	}

}