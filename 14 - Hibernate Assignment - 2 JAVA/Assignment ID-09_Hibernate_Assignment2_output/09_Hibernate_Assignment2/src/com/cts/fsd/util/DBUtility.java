package com.cts.fsd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cts.fsd.constants.ApplicationConstants;

public class DBUtility {

	public static Connection getConnection() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		// Register JDBC driver
		Class.forName(ApplicationConstants.JDBC_DRIVER).newInstance();

		// Open a connection
		System.out.println("Connecting to database...");
		Connection conn = DriverManager.getConnection(
				ApplicationConstants.DB_URL + ApplicationConstants.DB_NAME, 
				ApplicationConstants.DB_USER,
				ApplicationConstants.DB_PASSWORD );

		System.out.println("ApplicationConstants.DB_URL + ApplicationConstants.DB_NAME = " + ApplicationConstants.DB_URL + ApplicationConstants.DB_NAME);
		return conn;
	}
	
	// Close a connection
	public static void closeConnection(Connection conn) throws SQLException {
		System.out.println("Closing database connection...");
		conn.close();
		System.out.println("Connection Closed...");
	}
	
}
