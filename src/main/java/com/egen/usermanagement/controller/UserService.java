package com.egen.usermanagement.controller;
import java.util.ArrayList;
import com.egen.usermanagement.dao.UserDao;
import com.egen.usermanagement.vo.*;
import com.google.gson.Gson;

public class UserService {
	Gson gson = new Gson();
	// returns a list of all users
	public static ArrayList<User> getAllUsers() {
		ArrayList<User> users=new ArrayList<User>();
		users=UserDao.getAllUsers();
		return users;	
	}
	// creates a new user
	public static boolean createUser(User user) {
		boolean isCreated=false;
		isCreated=UserDao.insertUser(user);
		return isCreated;
	}
	//Check if User Exist
	public static boolean getUser(String id) {
		boolean isExist=false;
		isExist=UserDao.getUser(id);
		return isExist;
	}
	// updates an existing user
	public static boolean updateUser(User user) {
		boolean isUpdated=false;
		isUpdated=UserDao.updateUser(user);
		return isUpdated;
	}
	}
