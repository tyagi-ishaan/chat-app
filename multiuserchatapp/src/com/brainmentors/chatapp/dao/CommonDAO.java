package com.brainmentors.chatapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.brainmentors.chatapp.utils.ConfigReader.getValue;

// Throw Early and Catch Later
public interface CommonDAO {
	public static Connection createConnection() throws ClassNotFoundException, SQLException {
		//step-1 load a Driver
		Class.forName(getValue("DRIVER"));
		//step-2 making a Connection
		final String CONNECTION_STRING = getValue("CONNECTION_URL");
		final String USER_ID = getValue("USER_ID");
		final String PASSWORD = getValue("PASSWORD");
		Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_ID, PASSWORD);
		if(con != null) {
			System.out.println("Connection Created...");
			//con.close();
		}
		return con;
	}
	
}
