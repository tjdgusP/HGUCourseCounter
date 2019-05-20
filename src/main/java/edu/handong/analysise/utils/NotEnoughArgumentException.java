package edu.handong.analysise.utils;

public class NotEnoughArgumentException extends Exception{
	
	public NotEnoughArgumentException() {
		super("The file path does not exist. Please check your CLI argument!");
	}
	
	public NotEnoughArgumentException(String message) {
		super(message);
	}
}
