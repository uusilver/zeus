package com.schenker.zeus.service;

import org.springframework.stereotype.Service;

@Service("userVal")
public class UserValidation {

	
	public boolean userValidation(String userId, String password){
		if(userId.equals("admin")&&password.equals("admin")){
			return true;
		}else{
			return false;
		}
	}
}
