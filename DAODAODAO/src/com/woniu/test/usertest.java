package com.woniu.test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.woniu.bean.User;
import com.woniu.dao.UserDAO;

class usertest {

	@Test
	public void testfindall() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		UserDAO dd = new UserDAO();
		List<User> users = dd.findAll();
		
		for(User c:users) {
			System.out.println(c);
		}
	}

}
