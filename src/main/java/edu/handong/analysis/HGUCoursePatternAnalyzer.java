package edu.handong.analysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.csv.CSVRecord;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysis.utils.NotEnoughArgumentException;
import edu.handong.analysis.utils.Utils;

public class HGUCoursePatternAnalyzer {

	private HashMap<String,Student> students;
	private ArrayList<String> linesToBeSaved;
	private String input;
	private String output;
	private String analysis;
	private String coursecode;
	private int startyear;
	private int endyear;
	boolean help;
	
	
	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 * @throws IOException 
	 */
	public void run(String[] args) throws IOException {
		
		Options options = createOptions();
		
		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}
			
			List<CSVRecord> records = Utils.getLines(input, true);
			
			students = loadStudentCourseRecords(records);
	
			// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
			Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
			
			if(analysis.equals("1")) {
		
				// Generate result lines to be saved.
				ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents,startyear,endyear);
				// Write a file (named like the value of resultPath) with linesTobeSaved.
				Utils.writeAFile(linesToBeSaved, output);
				
			}else if(analysis.equals("2")){
				try {
					linesToBeSaved = RateOfStudentTakenCertainCourse(sortedStudents,startyear,endyear,coursecode);
					//Utils.writeAFile2(linesToBeSaved, output);
				}catch(Exception e){
					printHelp(options);
					System.exit(0);
				}
				Utils.writeAFile2(linesToBeSaved, output);
			}
		}
	}
	
	/**
	 * This method create HashMap<String,Student> from the data csv file. Key is a student id and the corresponding object is an instance of Student.
	 * The Student instance have all the Course instances taken by the student.
	 * @param lines
	 * @return
	 */
	private HashMap<String,Student> loadStudentCourseRecords(List<CSVRecord> records) {
		
		HashMap<String,Student> students = new HashMap<String,Student>();
		
		for (CSVRecord record : records) {
			
			Course course = new Course(record);
			String studentId = course.getStudentId();
					
			if(students.containsKey(studentId)) { //if same ID, add course
				students.get(studentId).addCourse(course);
			}
			else { // no same ID, make new Student
				Student student = new Student(studentId);
				student.addCourse(course);
				students.put(studentId,student);
			}	
		}
		return students;
	}
	
	/**
	 * This method generate the number of courses taken by a student in each semester. The result file look like this:
	 * StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester
	 * 0001,14,1,9
     * 0001,14,2,8
	 * ....
	 * 
	 * 0001,14,1,9 => this means, 0001 student registered 14 semesters in total. In the first semester (1), the student took 9 courses.
	 * 
	 * 
	 * @param sortedStudents
	 * @return
	 */
	private ArrayList<String> countNumberOfCoursesTakenInEachSemester(Map<String, Student> sortedStudents, int startyear,int endyear) {
		
		ArrayList<String> linesToBeSaved = new ArrayList<String>();
		
		for(String studentId : sortedStudents.keySet()) {
			
			Student student = sortedStudents.get(studentId);
			
			HashMap<String,Integer> semesterByYearAndSemester = new HashMap<String,Integer>();
			semesterByYearAndSemester = student.getSemesterByYearAndSemester(startyear,endyear);
			
			if(!(semesterByYearAndSemester.isEmpty())) {
				int totalNumOfSemester = semesterByYearAndSemester.size();
				
				for(int i=1; i<= totalNumOfSemester; i++) {
					
					int NumOfCourseTaken = student.getNumCourseInNthSemester(i,startyear,endyear);
					String line = studentId + "," + totalNumOfSemester + "," + i +","+ NumOfCourseTaken;
					linesToBeSaved.add(line);
				}
			}else ;
		}
		return linesToBeSaved; // do not forget to return a proper variable.
	}
	
	private ArrayList<String> RateOfStudentTakenCertainCourse(Map<String, Student> sortedStudents,int startyear,int endyear, String coursecode) {
		
		ArrayList<String> linesToBeSaved = new ArrayList<String>();
		String semesterKey;
		String courseName = null;
		int totalStudent = 0;
		int NumOfStudentTakenCourse = 0;
		float rate;
		String Rate;
		
		for(int year = startyear; year <= endyear; year++) {
			for(int semester = 1; semester <= 4; semester++) {
				
				semesterKey = year + "-" + semester;
				totalStudent=0;
				NumOfStudentTakenCourse=0;
				
				for(String studentId : sortedStudents.keySet()) {
					Student student = sortedStudents.get(studentId);
					if(student.getTotalStudent(semesterKey)==true) {
						totalStudent++;
						if(student.getNumOfStudentTakenCourse(semesterKey,coursecode)==true) {
							NumOfStudentTakenCourse++;
							courseName = student.getCourseName(coursecode);
						}else ;
					}else ;
				}
				
				if(NumOfStudentTakenCourse == 0) {
					continue;
				}else {
					rate = (float) ((float)NumOfStudentTakenCourse / (float)totalStudent * 100.0);
					Rate = String.format("%.1f", rate) + "%";
					String line = year +","+ semester +","+ coursecode +","+ courseName +","+ totalStudent +","+ NumOfStudentTakenCourse +","+Rate;
					linesToBeSaved.add(line);
				}
			}
		}
		return linesToBeSaved;
	}
	
	
	
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			input = cmd.getOptionValue("i");
			output = cmd.getOptionValue("o");
			analysis = cmd.getOptionValue("a");
			coursecode = cmd.getOptionValue("c");
			startyear = Integer.parseInt(cmd.getOptionValue("s"));
			endyear = Integer.parseInt(cmd.getOptionValue("e"));
			help = cmd.hasOption("h");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	// Definition Stage
	private Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input file path")
				.hasArg()
				.argName("Input path")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an output file path")
				.hasArg() 
				.argName("Output path")
				.required()
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("a").longOpt("analysis")
				.desc("1: Count courses per semester, 2: Count per course name and year")
				.hasArg() 
				.argName("Analysis option")
				.required()
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("c").longOpt("coursecode")
				.desc("Course code for '-a 2' option")
				.hasArg() 
				.argName("course code")
				//.required()
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("s").longOpt("startyear")
				.desc("Set the start year for analysis e.g., -s 2002")
				.hasArg() 
				.argName("Start year for analysis")
				.required()
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("e").longOpt("endyear")
				.desc("Set the end year for analysis e.g., -e 2005")
				.hasArg() 
				.argName("End year for analysis")
				.required()
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Show a Help page")
		        .argName("Help")
		        .build());

		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "HGU Course Analyzer";
		String footer ="";
		formatter.printHelp("HGUCourseCounter", header, options, footer, true);
	}
}
	
	