package com.revature;

public class SimpleAccount extends Account {

	SimpleAccount(Person owner, String loginName, String password){
		super.setOwner(owner);
		super.setLoginName(loginName);
		super.setPassword(password);
	}
	
}
