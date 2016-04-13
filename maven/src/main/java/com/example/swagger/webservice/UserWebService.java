package com.example.swagger.webservice;


import javax.servlet.http.HttpServletRequest;

import com.example.swagger.model.ResponseModel;
import com.example.swagger.model.UserModel;

public interface UserWebService {
	ResponseModel getUser(String authToken);

	ResponseModel getUserById(HttpServletRequest request, UserModel userModel) throws Exception;
}
