package com.example.swagger.service;

import javax.servlet.http.HttpServletRequest;

import com.example.swagger.dto.UserDTO;
import com.example.swagger.model.UserModel;

public interface UserService {
	public UserDTO getUserByAuthToken(String authToken);

	UserDTO saveUser(UserModel userModel, HttpServletRequest request) throws Exception;

	UserDTO getUserById(UserModel userModel) throws Exception;
}
