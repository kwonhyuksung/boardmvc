package com.kitri.util;

import java.sql.*;

public class DBConnection {
	
	static {
		try {
			Class.forName("oracle.odbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.getStackTrace();
		}
	}
	
	public static Connection makeConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:oracle:thin:@192.168.18.33:1521:xe", "kitri", "kitri");
	}
}
