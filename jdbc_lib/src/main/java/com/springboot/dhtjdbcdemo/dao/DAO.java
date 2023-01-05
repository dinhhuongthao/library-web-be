package com.springboot.dhtjdbcdemo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/jdbc_lib";
	private String jdbcUsername = "root";
	private String jdbcPassword = "dht2001";
	
	public static Connection connection;
	
	public DAO() {
		if (connection == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
