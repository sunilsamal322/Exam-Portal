package com.exam.helper;

public class UserFoundException extends Exception{

	public UserFoundException() {
		super("this username already taken try different one ");
	}
}
