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
	
	public HashMap<String,Integer>getSemesterByYearAndSemester(){
		
		semesterByYearAndSemester = new HashMap<String,Integer>();
		int num=1; //학기수 세기
		for(Course course: courseTaken) {
			
			String semesterByYear = Integer.toString(course.getYearTaken()) + "-" + Integer.toString(course.getSemesterCourseTaken());
			if(semesterByYearAndSemester.containsKey(semesterByYear)) { //동일 년도 학기이면 패스
				;
			}
			else { // 동일 년, 학기 없으면 새로 semesterByYearAndSemester 만들기
				semesterByYearAndSemester.put(semesterByYear,num);
				num++;
			}
		}
		return semesterByYearAndSemester;
	}
	
	
	public int getNumCourseInNthSemester(int semester) {
		int num = 0;
		for(Course course: courseTaken) {
			if(semester == semesterByYearAndSemester.get(course.getCourseTakenKey())) {
				num++;
			}
			else ;
		}
		return num;
	}

}