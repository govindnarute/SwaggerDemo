package com.example.swagger.dao;


import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.example.swagger.dto.UserDTO;
import com.example.swagger.model.UserModel;

public class UserDaoImpl extends BaseDAO implements UserDao{
	private static Logger logger = Logger.getLogger(UserDaoImpl.class);

	public UserDTO getUserByAuthToken(String token) throws HibernateException {
		UserDTO userDTO = null;
		try {
			Session session = getCurrentSession();
			userDTO = (UserDTO) session.createCriteria(UserDTO.class).add(Restrictions.eq("authToken", token))
					.uniqueResult();
		} catch (HibernateException exception) {
		}
		return userDTO;
	}
	
	@Override
	public UserDTO getUserById(UserModel userModel) throws HibernateException {
		UserDTO userDTO = null;
		try {
			Session session = getCurrentSession();
			userDTO = (UserDTO) session.createCriteria(UserDTO.class).add(Restrictions.eq("id", userModel.getId()))
					.uniqueResult();
		} catch (HibernateException exception) {
		}
		return userDTO;
	}

	
	@Override
	public void saveUser(UserDTO userDTO) throws HibernateException {
		try {
			save(userDTO);
		} catch (HibernateException hibernateException) {
			logger.error(hibernateException.getMessage(), hibernateException);
			throw hibernateException;
		}
	}

	
}
