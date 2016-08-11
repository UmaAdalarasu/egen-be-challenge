package com.egen.usermanagement.constants;

public class UserConstants {
	//DATABASE DETAILS
	public static final String DBHOST="localhost";
	public static final int DBPORT=27017;
	public static final String DBName="myDB";
	
	//HTTP ERROR CODES
	public static final int HTTP_BAD_REQUEST = 400;
	public static final int HTTP_OK = 200;
	
	//CREATE SUCCESS & FAILURE MESSAGE
	public static final String ERRCREATE= "Error creating user";
	public static final String SUCCCREATE="Success!!New User Created";
	
	//UPDATE SUCCESS & FAILURE MESSAGE
	public static final String ERRUPDATE= "Error creating user";
	public static final String SUCCUPDATE="Success!!New User Created";
	
	//USER EXIST & NOT EXIST MESSAGE
	public static final String USEREXIST="User Already Exist";
	public static final String USERNOTEXIST="User Does Not Exist";

}
