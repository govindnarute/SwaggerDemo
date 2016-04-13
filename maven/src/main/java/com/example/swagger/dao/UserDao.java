package com.example.swagger.dao;

import org.hibernate.HibernateException;

import com.example.swagger.dto.UserDTO;
import com.example.swagger.model.UserModel;

public interface UserDao {

	public UserDTO getUserByAuthToken(String authToken);

	void saveUser(UserDTO userDTO) throws HibernateException;

	UserDTO getUserById(UserModel userModel) throws HibernateException;

}
