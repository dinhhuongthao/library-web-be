package com.springboot.dhtjdbcdemo.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.springboot.dhtjdbcdemo.model.Book;
import com.springboot.dhtjdbcdemo.util.FileUploadUtil;

public class BookDAO extends DAO {
	// Define SQL statement
	private static final String SELECT_ALL_BOOKS = "SELECT * FROM book";
	private static final String SELECT_BOOK_BY_ID = "SELECT * FROM book WHERE bookcode = ?";
	private static final String INSERT_BOOKS_SQL = "INSERT INTO book (title, author, category, releaseDate, numOfPages, imageName) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_BOOKS_SQL = "UPDATE book SET title=?, author=?, category=?, releaseDate=?, numOfPages = ?, imageName=? WHERE bookcode=?";
	private static final String DELETE_BOOK_SQL = "DELETE FROM `book` WHERE (`bookcode` = ?)";
	
	
	public BookDAO() {
		super();
	}
	
	public List<Book> selectAllBooks() {
		List<Book> books = new ArrayList<Book>();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int bookcode = rs.getInt("bookcode");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String category = rs.getString("category");
				Date releaseDate = rs.getDate("releaseDate");
				int numOfPages = rs.getInt("numOfPages");
				String imageName = rs.getString("imageName");
				books.add(new Book(bookcode, title, author, category, releaseDate, numOfPages, imageName));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return books;
	}
	
	public Book selectBook(int id) {
		Book book = new Book();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				book.setBookcode(rs.getInt("bookcode"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setCategory(rs.getString("category"));
				book.setReleaseDate(rs.getDate("releaseDate"));
				book.setNumOfPages(rs.getInt("numOfPages"));
				book.setImageName(rs.getString("imageName"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return book;
	}
	
	public void insertBook(Book book) {
		try {
			PreparedStatement ps = connection.prepareStatement(INSERT_BOOKS_SQL);
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getCategory());
			ps.setDate(4, book.getReleaseDate());
			ps.setInt(5, book.getNumOfPages());
			ps.setString(6, book.getImageName());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateBook(Book book) {
		
		try {
			PreparedStatement ps = connection.prepareStatement(UPDATE_BOOKS_SQL);
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getCategory());
			ps.setDate(4, book.getReleaseDate());
			ps.setInt(5, book.getNumOfPages());
			ps.setString(6, book.getImageName());
			ps.setInt(7, book.getBookcode());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBook(int id) {
		try {

			Book book = selectBook(id);

//			DELETE IMAGE BY PATH
		    FileUploadUtil.deleteFile(book.getImageName());
		    
//		    DELETE FROM DB
		    PreparedStatement ps = connection.prepareStatement(INSERT_BOOKS_SQL);
			ps = connection.prepareStatement(DELETE_BOOK_SQL);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
