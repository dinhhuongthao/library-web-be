package com.springboot.dhtjdbcdemo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.springboot.dhtjdbcdemo.model.Book;
import com.springboot.dhtjdbcdemo.model.Comment;

public class CommentDAO extends DAO {
	private static final String SELECT_COMMENTS_BY_BOOKCODE = "SELECT * FROM comment WHERE bookcode = ?";
	private static final String INSERT_COMMENT_SQL = "INSERT INTO comment (bookcode, username, body, rating) VALUES (?, ?, ?, ?)";
	
	public CommentDAO() {
		super();
	}
	
	public List<Comment> selectComments(int bookcode) {
		List<Comment> comments = new ArrayList<Comment>();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement( SELECT_COMMENTS_BY_BOOKCODE);
			preparedStatement.setInt(1, bookcode);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String body = rs.getString("body");
				int rating = rs.getInt("rating");
				comments.add(new Comment(id, bookcode, username, rating, body));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return comments;
	}
	
	public void insertComment(Comment comment) {
		try {
			PreparedStatement ps = connection.prepareStatement(INSERT_COMMENT_SQL );
			ps.setInt(1, comment.getBookcode());
			ps.setString(2, comment.getUsername());
			ps.setString(3, comment.getBody());
			ps.setInt(4, comment.getRating());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
