package edu.handong.analysise.utils;

public class NotEnoughArgumentException extends Exception{
	
	public NotEnoughArgumentException() {
		super("No CLI argument Exception! Please put a file path.");
	}
	
	public NotEnoughArgumentException(String message) {
		super(message);
	}
}


/*public class NotEnoughArgumentException extends Exception{
	
	/*String file;
	boolean fullpath;
	
	public NotEnoughArgumentException() {

		CommandLineParser parser = new DefaultParser();
		
		try {
			CommandLine cmd = parser.parse(args);
			file = cmd.getOptionValue("f");

		} catch (Exception e) {
			System.out.println("No CLI argument Exception! Please put a file path.");
		}
		if(parseOptions(options, args)){
			
			File f = new File(file);
			if(!f.exists()) {
				System.out.println("The file path does not exist. Please check your CLI argument!");
				System.exit(0);
			} else{
				System.out.println(f.getPath());
			}
		}
	}
	
	//File theDir = new File(DirectoryPath);
	//if (!theDir.exists()) theDir.mkdirs();
	public NotEnoughArgumentException() {
		super("No CLI argument Exception! Please put a file path.");
	}


	public NotEnoughArgumentException(String message) {
		super(message);
	}
}*/
