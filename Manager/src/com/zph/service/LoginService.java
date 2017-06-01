package com.zph.service;

import java.util.Date;

public interface LoginService {
	
	String writeLoginLog(String name,Date time);
	String Reigise(String name,String password);
	String checkLogin(String name,String password);
}
