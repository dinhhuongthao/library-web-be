package com.springboot.dhtjdbcdemo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.springboot.dhtjdbcdemo.model.Book;
import com.springboot.dhtjdbcdemo.model.User;

public class UserDAO extends DAO {
	private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
	
	public UserDAO() {
		super();
	}
	
//	public static User selectUser(User user) {
//		try {
//			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME);
//			preparedStatement.setString(1, user.getUsername());
//			ResultSet rs = preparedStatement.executeQuery();
//			while (rs.next()) {
//				user.setId(rs.getInt("id"));
//				user.setAddress(rs.getString("address"));
//				user.setPhone(rs.getString("phone"));
//				user.setEmail(rs.getString("email"));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return user;
//	}
	
	
}
