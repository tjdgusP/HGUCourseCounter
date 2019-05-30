package edu.handong.analysis.utils;

import java.util.ArrayList;
import java.util.List;//
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;//
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Utils {
	
	public static List<CSVRecord> getLines(String file, boolean removeHeader) throws IOException{
		
		FileReader reader = null;
		
		try{
			
			reader = new FileReader(file);
		
		} catch(FileNotFoundException e){
			System.out.println("The file path does not exist. Please check your CLI argument!");
			System.exit(0);
		}
		
		CSVParser parser = new CSVParser(reader, CSVFormat.RFC4180
				.withFirstRecordAsHeader()
				.withIgnoreHeaderCase()
				.withTrim());
		List<CSVRecord> records = parser.getRecords();
		 
		return records;
	}
	
	public static void writeAFile(ArrayList<String>lines, String targetFileName) {
		
		PrintWriter outputStream = null;
		File file = new File(targetFileName);
		
		if(!file.exists()) {
			file.getParentFile().mkdirs();
			targetFileName = file.getAbsolutePath();
		}
		
		try {
			outputStream = new PrintWriter(targetFileName);
		} catch (FileNotFoundException e) {
			System.out.println("The file path does not exist. Please check your CLI argument!");
			System.exit(0);
		}
				
		outputStream.println("StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester");
		for(String line:lines) {
			outputStream.println(line);
		}
		outputStream.close(); 
	}
	
	public static void writeAFile2(ArrayList<String>lines, String targetFileName) {
		
		PrintWriter outputStream = null;
		File file = new File(targetFileName);
		
		if(!file.exists()) {
			file.getParentFile().mkdirs();
			targetFileName = file.getAbsolutePath();
		}
		
		try {
			outputStream = new PrintWriter(targetFileName);
		} catch (FileNotFoundException e) {
			System.out.println("The file path does not exist. Please check your CLI argument!");
			System.exit(0);
		}
				
		outputStream.println("Year,Semester,CourseCode,CoureName,TotalStudents,StudentsTaken,Rate");
		for(String line:lines) {
			outputStream.println(line);
		}
		outputStream.close(); 
	}
}