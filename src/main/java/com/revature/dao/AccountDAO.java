package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

	public boolean getAccount(String loginname) {

		try {
			// 1. Create a Statement
			Connection c = ConnectionManager.getConnection();

			PreparedStatement preparedStatement = c
					.prepareStatement("SELECT * FROM accounts WHERE account_loginname = ?, account_approved = ?");
			preparedStatement.setString(1, loginname);

			ResultSet results = preparedStatement.executeQuery();
			// 3. If there is more than 0 records, we'll return false. Otherwise true.
			int size = results.getFetchSize();
			if (size > 0)
				// The username IS taken = true
				return true;

			return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public void setupAccount(String loginname) {

		try {
			Connection c = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = c
					.prepareStatement("INSERT INTO accounts VALUES account_loginname = '?'");
			preparedStatement.setString(1, loginname);
			preparedStatement.setBoolean(2, false);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateApprovalStatus(String loginname, boolean isApproved) {
		try {
			Connection c = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = c
					.prepareStatement("UPDATE accounts SET account_approval = ? WHERE account_loginname = ?");
			preparedStatement.setBoolean(1, isApproved);
			preparedStatement.setString(2, loginname);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
