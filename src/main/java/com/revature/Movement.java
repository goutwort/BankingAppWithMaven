package com.revature;

public class Movement {
	private String date = "";
	private double amount;
	private Account accountOrigin;
	private String accountDestination = "";
	
	Movement(String date, String recipient, double amount){
		this.date = date;
	    accountDestination = recipient;
	    this.amount = amount;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Account getAccountOrigin() {
		return accountOrigin;
	}
	public void setAccountOrigin(Account accountOrigin) {
		this.accountOrigin = accountOrigin;
	}
	public String getAccountDestination() {
		return accountDestination;
	}
	public void setAccountDestination(String accountDestination) {
		this.accountDestination = accountDestination;
	}
	
}
