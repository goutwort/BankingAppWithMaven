package com.revature;

import java.util.ArrayList;

public abstract class Account {

	private Person owner;
	
	
	private String loginName = "";
	private String password = "";
	
	private double saldo;
	
	private ArrayList<Movement> movementList = new ArrayList<Movement>();
	
	private boolean approved = false;

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public ArrayList<Movement> getMovementList() {
		return movementList;
	}

	public void setMovementList(ArrayList<Movement> movementList) {
		this.movementList = movementList;
	}
	

	
}
