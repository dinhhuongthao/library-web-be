package com.springboot.dhtjdbcdemo.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.dhtjdbcdemo.model.Book;
import com.springboot.dhtjdbcdemo.model.CartBook;
import com.springboot.dhtjdbcdemo.model.Comment;
import com.springboot.dhtjdbcdemo.util.FileUploadUtil;

public class CartBookDAO  extends DAO {
	private static final String SELECT_ALL_BOOK_BY_USERNAME = "SELECT * FROM cartbook WHERE username = ?";
	private static final String INSERT_BOOK_TO_CART_SQL = "INSERT INTO cartbook (username, bookcode, quantity) VALUES (?, ?, ?)";
	private static final String UPDATE_CARTBOOK_SQL = "UPDATE cartbook SET quantity=? WHERE id=?";
	private static final String DELETE_CARTBOOK_SQL = "DELETE FROM `cartbook` WHERE (`id` = ?)";
	
	public CartBookDAO() {
		super();
	}

	public List<CartBook> selectAllCartBookByUsername(String username) {
		List<CartBook> cartbooks = new ArrayList<CartBook>();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOK_BY_USERNAME);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int bookcode = rs.getInt("bookcode");
				int quantity = rs.getInt("quantity");
				cartbooks.add(new CartBook(id, username, bookcode, quantity));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cartbooks;
	}
	
	public void insertCartBook(CartBook cartbook) {
		try {
			PreparedStatement ps = connection.prepareStatement(INSERT_BOOK_TO_CART_SQL);
			ps.setString(1, cartbook.getUsername());
			ps.setInt(2, cartbook.getBookcode());
			ps.setInt(3, cartbook.getQuantity());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateCartBook(CartBook cartbook) {
		
		try {
			PreparedStatement ps = connection.prepareStatement(UPDATE_CARTBOOK_SQL);
			ps.setInt(1, cartbook.getQuantity());
			ps.setInt(2, cartbook.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteCartBook(int id) {
		try {

//		    DELETE FROM DB
		    PreparedStatement ps = connection.prepareStatement(DELETE_CARTBOOK_SQL);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

	