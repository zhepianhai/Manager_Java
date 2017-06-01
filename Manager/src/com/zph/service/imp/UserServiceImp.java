package com.zph.service.imp;

import java.util.Date;
import java.util.List;

import com.zph.pojo.User;
import com.zph.service.UserService;
import com.zph.util.FileUtil;

public class UserServiceImp implements UserService{
	private static final String USERPATH="user/myUsers.zph";


	@Override
	public String DeleteOneUser(String username, String phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> SelectAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String UpdateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String AddOneUser(User user) {
		FileUtil.writeByFileReader(USERPATH,user.toString());
		return null;
	}

}
