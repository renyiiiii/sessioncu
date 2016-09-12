package com.test.hello.security;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

public class KdJdbcDaoImpl extends JdbcDaoImpl{
	
	public static ConcurrentHashMap<String,String> userpass = new ConcurrentHashMap<String, String>();

	@Override
	protected List<UserDetails> loadUsersByUsername(String username) {
		List<UserDetails> list = new ArrayList<UserDetails>();
		 String password = userpass.get(username);
		 if(password != null){
			 list.add(new User(username, password, true, true, true, true, AuthorityUtils.NO_AUTHORITIES));
		 }
		
		 return list;
	}
	
	
	 


}
