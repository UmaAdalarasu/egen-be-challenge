
package com.egen.usermanagement.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import org.codehaus.jackson.map.ObjectMapper;

import com.egen.usermanagement.constants.UserConstants;
import com.egen.usermanagement.vo.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.google.gson.Gson;

public class UserController {
	public static void main(String[] args) {
		// Create User
		post("/users", (req, res) -> {
			try {
				ObjectMapper mapper = new ObjectMapper();
				User inputUser = mapper.readValue(req.body(), User.class);
				// Check if user already exist
				boolean isExist = UserService.getUser(inputUser.getId());
				// If User does not exist then create
				if (!isExist) {
					// Creating User
					boolean isCreated = UserService.createUser(inputUser);

					// Send Error message if Creation is not successful
					if (!isCreated) {
						res.status(UserConstants.HTTP_BAD_REQUEST);
						return UserConstants.ERRCREATE;
					}
					// Send success message if Creation is successful
					else {
						res.status(UserConstants.HTTP_OK);
						res.type("application/json");
						return UserConstants.SUCCCREATE;
					}
				}
				// Send Error message if user already exists
				else {
					res.status(UserConstants.HTTP_BAD_REQUEST);
					res.type("application/json");
					return UserConstants.USEREXIST;
				}
			} catch (JsonParseException jpe) {
				res.status(UserConstants.HTTP_BAD_REQUEST);
				return "";
			}
		});

		// Get All Users
		get("/users", (req, res) -> {
			res.status(UserConstants.HTTP_OK);
			res.type("application/json");
			Gson gson = new Gson();
			//Getting Users and converting it to json
			String jsonInString = gson.toJson(UserService.getAllUsers());
			return jsonInString;
		});

		// Update User
		post("/updateUser", (req, res) -> {
			try {
				ObjectMapper mapper = new ObjectMapper();
				User inputUser = mapper.readValue(req.body(), User.class);
				// Check if user already exist
				boolean isExist = UserService.getUser(inputUser.getId());
				// If User exist then Update
				if (isExist) {
					// Updating User
					boolean isUpdated = UserService.updateUser(inputUser);

					// Send Error message if Update is not successful
					if (!isUpdated) {
						res.status(UserConstants.HTTP_BAD_REQUEST);
						return UserConstants.ERRUPDATE;
					}
					// Send success message if update is successful
					else {
						res.status(UserConstants.HTTP_OK);
						res.type("application/json");
						return UserConstants.SUCCUPDATE;
					}
				}
				// Send Error message if user does not exists
				else {
					res.status(UserConstants.HTTP_BAD_REQUEST);
					res.type("application/json");
					return UserConstants.USERNOTEXIST;
				}
			} catch (JsonParseException jpe) {
				res.status(UserConstants.HTTP_BAD_REQUEST);
				return "";
			}
		});
	}
}
