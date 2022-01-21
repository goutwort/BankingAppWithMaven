package com.revature;


public class JointAccount extends Account {
	private Person secondOwner;
	
	JointAccount(Person firstOwner, Person secondOwner, String loginName, String password){
		super.setOwner(firstOwner);
		setSecondOwner(secondOwner);
		super.setLoginName(loginName);
		super.setPassword(password);
	}

	public Person getSecondOwner() {
		return secondOwner;
	}

	public void setSecondOwner(Person secondOwner) {
		this.secondOwner = secondOwner;
	}


}
