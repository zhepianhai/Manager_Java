package com.zph.service.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.ws.ServiceMode;

import com.zph.service.LoginService;
import com.zph.util.FileUtil;

@ServiceMode
public class LoginServiceImp implements LoginService{
	private static final String LOGINLGO="login/loginlog.log";
	private static final String REGISE="login/userlog.log";
	@Override
	public String writeLoginLog(String name, Date time) {
		//登录日志
		if("".equals(name))
			name="admin";
		time=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String times=sdf.format(time);
		StringBuffer sb=new StringBuffer();
		sb.append("name:"+name);
		sb.append("time:"+times);
		FileUtil.writeByFileReader("", sb.toString());
		return null;
	}

	@Override
	public String Reigise(String name, String password) {
		String message="";
		//注册
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String times=sdf.format(new Date());
		StringBuffer sb=new StringBuffer();
		sb.append("name:"+name);
		sb.append("password:"+password);
		sb.append("time:"+times);
		message=FileUtil.writeByFileReader(REGISE, sb.toString());
		return message;
	}
	
	
	/**
	 * 检查登录
	 * */
	@Override
	public String checkLogin(String name, String password) {
		String message="";
		List<String> list=
		FileUtil.readByBufferedReader(REGISE);
		for(int i=list.size()-1;i>=0;--i){
			String[] a=list.get(i).split("name:");
			String[] b=a[1].split("password:");
			String nam=b[0];
			String[] c=b[1].split("time:");
			String psd=c[0];
			if(name.equals(nam) && password.equals(psd)){
				message="OK";
				return message;
			}
			
		}
		return null;
	}

}
