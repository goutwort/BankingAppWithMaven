package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {
	
	public boolean getEmployee(String loginname) {

		try {

			Connection c = ConnectionManager.getConnection();

			PreparedStatement preparedStatement = c
					.prepareStatement("SELECT * FROM employees WHERE employee_ID = ?");
			preparedStatement.setString(1, loginname);

			ResultSet results = preparedStatement.executeQuery();

			int size = results.getFetchSize();
			if (size > 0)
				return true;

			return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

}
