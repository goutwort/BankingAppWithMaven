package com.revature;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;

public class BankingApp {
	
	static final Logger logger = Driver.logger;

	static ArrayList<Account> accounts = Driver.accounts;

	static ArrayList<Employee> employees = Driver.employees;

	static Scanner inputScanner = Driver.inputScanner;
	
	static void showMovements(Account account) {
		ArrayList<Movement> movements = account.getMovementList();

		System.out.println("==============================");
		System.out.println("\nMovements of " + account.getLoginName() + "\'s account ");
		System.out.println("------------------------------");
		for (Movement m : movements) {
			System.out.println("Date " + m.getDate() + ", " + m.getAccountDestination() + ", EUR "
					+ String.format("%,.2f", m.getAmount()));
		}
		System.out.println("---------------------------------------");
		System.out.println("Balance: " + "EUR " + String.format("%,.2f", account.getSaldo()));
		System.out.println("==============================");
	}
	
    static void initializeAccounts() {
		Person customer1 = new Person();
		customer1.setName("Henriette Matussek");
		customer1.setAge(59);
		customer1.setAddress("Kesselgasse 18, Ingolstadt");
		customer1.setPhoneNumber("+44917560142");
		customer1.setEmailAddress("liedermacherin@kculture.de");
		customer1.setInformation("Customer since 2005");

		accounts.add(new SimpleAccount(customer1, "hmatussek", "schokoriegel"));

		Person customer2 = new Person();
		customer2.setName("Gordon Shatley");
		customer2.setAge(42);
		customer2.setAddress("7, Barton Lane, Perry Island");
		customer2.setPhoneNumber("+448254673");
		customer2.setEmailAddress("g.shatley@perryisland.com");
		customer2.setInformation("Customer since 2002");

		Account accountCustomer2 = new SimpleAccount(customer2, "gshatley", "chewinggum");
		accountCustomer2.setApproved(true);
		accounts.add(accountCustomer2);
	}

	static void initializeEmployees() {
		Employee employee1 = new Employee();
		employee1.setName("Conrad Walton");
		employee1.setEmployeeID("Conrad");
		employee1.setEmployeePassword("XYZ");
		employees.add(employee1);
	}

	static void accountCreation() {
		System.out.println("Would you like to open a joint account for you and another person? (yes/no)");

		if (inputScanner.next().equals("yes")) { // Creation of a joint account is intended

			Person firstOwner = new Person();
			Person secondOwner = new Person();

			System.out.println("Please enter the account owner's name: ");
			firstOwner.setName(inputScanner.next());
			firstOwner.setAddress("5, Hutton Park Road, Herfordshire");
			firstOwner.setAge(72);
			firstOwner.setPhoneNumber("+4493746034");
			firstOwner.setEmailAddress("lute@lexus.co.uk");
			firstOwner.setInformation("Customer since 1986");

			System.out.println("Please enter the name of the person who is going to be the second account owner: ");
			secondOwner.setName(inputScanner.next());
			secondOwner.setAddress("82, Radish Street, Brighton");
			secondOwner.setAge(65);
			secondOwner.setPhoneNumber("+449234756");
			secondOwner.setEmailAddress("sally@bentley.co.uk");
			secondOwner.setInformation("Customer since 2021");

			System.out.println("Please enter the account's login name: ");
			String newLoginName = inputScanner.next();
			System.out.println("Please enter the account's password: ");
			String newPassword = inputScanner.next();

			System.out.println("Shall a new joint account be created? (yes/ no)");
			if (inputScanner.next().equals("yes")) {
				JointAccount jointAccount = new JointAccount(firstOwner, secondOwner, newLoginName, newPassword);
				accounts.add(jointAccount);
				System.out.println("\nAccount has been created.\n");
			}

		} else {

			Person owner = new Person();

			System.out.println("Please enter the account owner's name: ");
			owner.setName(inputScanner.next());
			owner.setAddress("8D, Old Bridge, Bristol");
			owner.setAge(29);
			owner.setPhoneNumber("+443857394");
			owner.setEmailAddress("irky@bbc.co.uk");
			owner.setInformation("New customer");

			System.out.println("Please enter the account's login name: ");
			String newLoginName = inputScanner.next();
			System.out.println("Please enter the account's password: ");
			String newPassword = inputScanner.next();

			System.out.println("Shall a new account be created? (yes/ no)");
			if (inputScanner.next().equals("yes")) {
				Account simpleAccount = new SimpleAccount(owner, newLoginName, newPassword);
				accounts.add(simpleAccount);
				System.out.println("Account has been created.\n");
			}

		}
	}

	static void orderMoneyTransfer(Account account) {
		System.out.println("Who shall be the recipient?");
		String recipient = inputScanner.next();
		System.out.println("How much money would you like to have transferred?");
		double amount = inputScanner.nextDouble();
		System.out.println("EUR " + String.format("%,.2f", amount) + " will be transferred to " + recipient
				+ ". Do you confirm? (yes/no)");
		if (inputScanner.next().equals("yes"))
			doMoneyTransfer((String) Calendar.getInstance().getTime().toString().subSequence(0, 10), account, recipient,
					amount);
	}

	static void doMoneyTransfer(String date, Account account, String recipient, double amount) {
		account.getMovementList().add(new Movement(date, recipient, amount));
		account.setSaldo(account.getSaldo() - amount);

	}

}
