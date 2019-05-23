package edu.handong.analysis.utils;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Utils {
	
	public static ArrayList<String> getLines(String file, boolean removeHeader){
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new File(file));
		} catch(FileNotFoundException e) {
			System.out.println("The file path does not exist. Please check your CLI argument!");
			System.exit(0);
		}
		
		String line = inputStream.nextLine(); //deleting first line
		ArrayList<String> lines = new ArrayList<String>();
		
		while(inputStream.hasNextLine()) {
			line = inputStream.nextLine();
			lines.add(line);
		}
		inputStream.close();
		return lines;
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
			System.out.println("Error opening file");
			System.exit(0);
		}
				
		outputStream.println("StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester");
		for(String line:lines) {
			outputStream.println(line);
		}
		outputStream.close();
	}
}