package com.egen.usermanagement.connection;

import java.net.UnknownHostException;

import com.egen.usermanagement.constants.UserConstants;
import com.mongodb.DB;
import com.mongodb.MongoClient;

public class DBConnection {
	
	public static DB getConnection() throws UnknownHostException {
		//Connect to MongoDB
		MongoClient mongo = new MongoClient( UserConstants.DBHOST , UserConstants.DBPORT );
		DB db = mongo.getDB(UserConstants.DBName);
		return db;
	}
}
