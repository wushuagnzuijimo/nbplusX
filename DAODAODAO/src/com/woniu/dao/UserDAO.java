package com.woniu.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.woniu.bean.User;

public class UserDAO {
	
	BaseDAO<User> dd = new BaseDAO<User>();
	
	public List<User> findAll() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		String sql ="select * from admin";
		Object [] objs= {};
		List<User> users = dd.select(sql, objs, User.class);
		
		return users;
		
	}
	
	

}
