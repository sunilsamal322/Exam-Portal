package com.exam.helper;

public class UserNotFoundException extends Exception{

	public UserNotFoundException()
	{
	super("user not found with this username");
	}
}
