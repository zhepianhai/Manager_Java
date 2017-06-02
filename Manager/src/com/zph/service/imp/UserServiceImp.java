package com.zph.service.imp;

import java.util.ArrayList;
import java.util.List;

import com.zph.pojo.User;
import com.zph.service.UserService;
import com.zph.util.FileUtil;

public class UserServiceImp implements UserService {
	private static final String USERPATH = "user/myUsers.zph";

	@Override
	public String DeleteOneUser(String username, String phone) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 查询用户
	 * */
	@Override
	public List<User> SelectAllUser() {
		// TODO Auto-generated method stub
		//username=大大发发的发的 phone=发达的发生address=大大发, other=dfadsf, time=2017-06-02 10:48:35
		List<User> listUser=new ArrayList();
		List<String> list=FileUtil.readByBufferedReader("user/myUsers.zph");
		for (String string : list) {
			User user=new User();
			String username,phone,address,other,time;
			String[]a=string.split("username=");
			String[]b=a[1].split("phone=");
			username=b[0];
			String[]c=b[1].split("address=");
			phone=c[0];
			String[]d=c[1].split("other=");
			address=d[0];
			String[]e=d[1].split("time=");
			other=e[0];
			time=e[1];
			user.setAddress(address);
			user.setOther(other);
			user.setPhone(phone);
			user.setTime(time);
			user.setUsername(username);
			listUser.add(user);
		}

		return listUser;
	}

	@Override
	public String UpdateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String AddOneUser(User user) {
		FileUtil.writeByFileReader(USERPATH, user.toString());
		return null;
	}

}
