package edu.handong.analysise.utils;

import java.util.ArrayList;
import java.util.Scanner;

import edu.handong.analysis.datamodel.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Utils {
	
	public static ArrayList<String>getLines(String file, boolean removeHeader){
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new File(file));
		} catch(FileNotFoundException e) {
			System.out.println("The file path does not exist. Please check your CLI argument!");
			System.exit(0);
		}
	
		ArrayList<String> lines = new ArrayList<String>();
		
		while(inputStream.hasNextLine()) {
			String line = inputStream.nextLine();
			lines.add(line);
		}
		inputStream.close();
		return lines;
	}
	
	public static void writeAFile(ArrayList<String>lines, String targetFileName) {
		
		PrintWriter outputStream = null;
		try {
			outputStream = new PrintWriter(targetFileName);
		} catch(FileNotFoundException e) {
			System.out.println("The file path does not exist. Please check your CLI argument!");
			System.exit(0);
		}
		for(String line:lines) {
			outputStream.println(line);
		}
		outputStream.close();
	}
}




