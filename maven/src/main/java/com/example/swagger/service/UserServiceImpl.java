package com.example.swagger.service;



import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.swagger.Exception.UserException;
import com.example.swagger.dao.UserDao;
import com.example.swagger.dto.UserDTO;
import com.example.swagger.model.UserModel;
//import com.example.swagger.util.LocaleConverter;
import com.example.swagger.util.TokenGenerator;


public class UserServiceImpl implements UserService {
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private Mapper dozerMapper;
	

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDTO getUserByAuthToken(String authToken){
		UserDTO userDto = userDao.getUserByAuthToken(authToken);
		return userDto;
	}
	
	@Override
	public UserDTO saveUser(UserModel userModel, HttpServletRequest request) throws Exception {
//		Locale locale = LocaleConverter.getLocaleFromRequest(request);
		UserDTO userDTO = null;
		try {
			userDTO = dozerMapper.map(userModel, UserDTO.class);
			String authToken = TokenGenerator.generateToken(userModel.getName()+new Date());


			userDTO.setAuthToken(authToken);

			userDao.saveUser(userDTO);


		} catch (Exception ex) {
			logger.error(ex.getStackTrace(), ex);
			throw ex;
		}
		return userDTO;
	}
	
	@Override
	public UserDTO getUserById(UserModel userModel) throws Exception{
		UserDTO userDto = null ;
		try{
		userDto = userDao.getUserById(userModel);
		if (userDto == null){
			throw new UserException("User does not exists with the given ID");
		}
		}catch(Exception ex){
			logger.error(ex.getStackTrace(), ex);
			throw ex;
		}
		return userDto;
	}

}
