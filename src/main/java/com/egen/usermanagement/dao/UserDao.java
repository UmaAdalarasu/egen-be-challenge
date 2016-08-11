package com.egen.usermanagement.dao;

import java.util.ArrayList;

import com.egen.usermanagement.vo.Address;
import com.egen.usermanagement.vo.Company;
import com.egen.usermanagement.vo.User;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class UserDao {

	public static ArrayList<User> getAllUsers() {
		ArrayList<User> allUsers = new ArrayList<User>();
		DB db = null;
		DBCollection coll = db.getCollection("users");
		DBCursor cursor = coll.find();
		int i = 1;
		while (cursor.hasNext()) {
			BasicDBObject obj = (BasicDBObject) cursor.next();
			User users = new User();
			Address address = new Address();
			Company company = new Company();
			users.setId(obj.getString("id"));
			users.setFirstName(obj.getString("firstName"));
			users.setLastName(obj.getString("lastName"));
			users.setEmail(obj.getString("email"));
			BasicDBObject ob = (BasicDBObject) obj.get("address");
			address.setStreet(ob.getString("street"));
			address.setCity(ob.getString("city"));
			address.setState(ob.getString("state"));
			address.setCountry(ob.getString("country"));
			address.setZip(Integer.parseInt(obj.getString("zip")));
			users.setAdress(address);
			users.setDateCreated(obj.getString("dateCreated"));
			BasicDBObject companyObj = (BasicDBObject) obj.get("company");
			company.setName(companyObj.getString("name"));
			company.setWebsite(companyObj.getString("website"));
			users.setCompany(company);
			users.setId(obj.getString("profilePic"));
			allUsers.add(users);
			i++;
		}
		return allUsers;
	}

	// Check if user exist
	public static boolean getUser(String id) {
		boolean isExist = false;
		ArrayList<String> allId = new ArrayList<String>();
		DB db = null;
		DBCollection coll = db.getCollection("users");
		DBCursor cursor = coll.find();
		int i = 1;
		while (cursor.hasNext()) {
			BasicDBObject obj = (BasicDBObject) cursor.next();
			allId.add(obj.getString("id"));
			i++;
		}
		if (allId.contains(id)) {
			isExist = true;
		}
		return isExist;
	}

	// Create USer
	public static boolean insertUser(User user) {
		DB db = null;
		DBCollection coll = db.getCollection("users");
		Gson gson = new Gson();
		String jsonInString = gson.toJson(user);
		DBObject dbObject = (DBObject) JSON.parse(jsonInString);
		coll.insert(dbObject);
		return true;
	}

	// Update user details
	// Considering that is user exist, we are updating the email
	public static boolean updateUser(User user) {
		DB db = null;
		DBCollection coll = db.getCollection("users");
		DBCursor cursor = coll.find();
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("email", user.getEmail());
		BasicDBObject searchQuery = new BasicDBObject().append("id", user.getId());
		coll.update(searchQuery, newDocument);
		return true;
	}
}
