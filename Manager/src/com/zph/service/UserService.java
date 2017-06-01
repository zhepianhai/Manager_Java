package com.zph.service;

import java.util.Date;
import java.util.List;

import com.zph.pojo.User;

public interface UserService {
	
	/**
	 * add
	 * */
	String AddOneUser(User user);
	
	
	
	/**
	 * delete
	 * */
	String DeleteOneUser(String username,String phone);
	
	
	/**
	 * selsect
	 * */
	
	List<User> SelectAllUser();
	
	
	/**
	 * 
	 * updata
	 * */
	String UpdateUser(User user);
	
	
}
