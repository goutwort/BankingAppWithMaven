package com.revature;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {
	static final Logger logger = LogManager.getLogger(Driver.class);

	static ArrayList<Account> accounts = new ArrayList<Account>();

	static ArrayList<Employee> employees = new ArrayList<Employee>();

	static Scanner inputScanner = new Scanner(System.in);

	public static void main(String[] args) {

		BankingApp.initializeAccounts();
		BankingApp.initializeEmployees();
		// Login
		boolean credentialsFound = false;
		boolean noCredentialsNeeded = false;
		boolean isEmployee = false;
		String loginName;

		boolean quitApplication = false;

		while (!quitApplication) {
			Account account = null;
			Employee employee = null;
			System.out.println("Welcome!\n");
			do {
				System.out.println("Do you already have an account with us? (yes/no)");
				if (inputScanner.next().equals("yes")) {
					System.out.println("Enter login name: ");
					loginName = inputScanner.next();
					System.out.println("Enter password: ");
					String password = inputScanner.next();

					int index = 0;

					if (employees.size() > 0) {
						while (!isEmployee && index < employees.size()) {

							employee = (Employee) employees.get(index);

							if (employee.getEmployeeID().equals(loginName))
								isEmployee = true;
							++index;
						}

						if (isEmployee && employee.getEmployeePassword().equals(password)) {
							credentialsFound = true;
							System.out.println("\nHello, Mrs./ Mr. " + employee.getName() + "!\n");

						}
					}

					if (!credentialsFound) {

						boolean loginNameFound = false;

						index = 0;

						if (accounts.size() > 0) {
							while (!loginNameFound && index < accounts.size()) {
								account = (Account) accounts.get(index);
								if (account.getLoginName().equals(loginName)) {
									loginNameFound = true;
								}
								++index;
							}
						}

						if (loginNameFound && account.getPassword().equals(password)) {
							credentialsFound = true;
							System.out.println("\nHello Mrs./ Mr. " + account.getOwner().getName() + "!");
						} else
							System.out.println("\nLogging-in has failed\n");
					}

				} else
					noCredentialsNeeded = true; // no login required

			} while (!credentialsFound && !noCredentialsNeeded);

			if (isEmployee) {

				System.out.println("Would you like to open an account for a customer? (yes/no)");

				if (inputScanner.next().equals("yes")) { // Creation of an account is intended
					BankingApp.accountCreation();

				} else { // No account creation is intended
//			Lookup of the account whose login name is equal to the loginName

					System.out.println("\nList of unapproved accounts:");
					System.out.println("------------------------------");
					for (Account a : accounts) {
						if (!a.isApproved())
							System.out.println(a.getLoginName());
					}
					System.out.println("------------------------------\n");

					System.out.println("Which account would you like to inspect? (login name)");
					String customerLoginName = inputScanner.next();

					boolean loginNameFound = false;

					int index = 0;
					if (accounts.size() > 0) {
						while (!loginNameFound && index < accounts.size()) {
							account = (Account) accounts.get(index);
							if (account.getLoginName().equals(customerLoginName)) {
								loginNameFound = true;
							}
							++index;
						}
					}
					if (loginNameFound) {
						if (account.isApproved()) {
							BankingApp.showMovements(account);

							System.out.println("Shall money be transferred? (yes/no)");
							if (inputScanner.next().equals("yes")) {
								BankingApp.orderMoneyTransfer(account);
							} else {
								System.out.println("Shall the account be blocked? (yes/no)");
								if (inputScanner.next().equals("yes")) {
									account.setApproved(false);
								}
							}
						} else {
							System.out.println("Shall the account be approved? (yes/no)");
							if (inputScanner.next().equals("yes")) {
								account.setApproved(true);
							}
						}
					} else
						System.out.println("\nThe account doesn't exist\n");

				}
			} else { // The user is a customer

				if (credentialsFound) {
					if (account.isApproved()) {
						BankingApp.showMovements(account);
						System.out.println("\nShall money be transferred? (yes/no)");
						if (inputScanner.next().equals("yes")) {
							BankingApp.orderMoneyTransfer(account);
						}
					} else {
						System.out.println("\nYour account hasn't yet been approved. Please contact us.\n");
					}
				} else {
					System.out.println("Would you like to open an account? (yes/no)");

					if (inputScanner.next().equals("yes")) { // Creation of an account is intended
						BankingApp.accountCreation();
					}
				}
			}

			if (isEmployee) {
				System.out.println("Would you like to shut down the banking app? (yes/no)");
				if (inputScanner.next().equals("yes")) {
					quitApplication = true;
				}
			}

			System.out.println("\nGood bye!\n\n\n\n\n");

			credentialsFound = false;
			noCredentialsNeeded = false;
			isEmployee = false;
		}

		inputScanner.close();

	}

}
