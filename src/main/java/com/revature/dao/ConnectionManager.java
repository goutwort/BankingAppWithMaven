package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static Connection connection;
	
	private static String connectionString = "jdbc:postgresql://ella.db.elephantsql.com:5432/olmwhsyq",
			username = "olmwhsyq ",
			password = "2ItJU_0d-2tMjulYPq_30RV8r4zNLdFW";
	
	public static Connection getConnection() {
		try {
			if (connection == null) {
			
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(connectionString, username, password);
			}
			
			return connection;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}