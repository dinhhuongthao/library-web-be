package com.springboot.dhtjdbcdemo.model;

public class Comment {
	private int id;
	private String username;
	private int bookcode;
	private int rating;
	private String body;
	
	public Comment() {
	}
	
	public Comment(int id, int bookcode, String username, int rating, String body) {
		this.id = id;
		this.username = username;
		this.bookcode = bookcode;
		this.rating = rating;
		this.body = body;
	}

	public Comment( int bookcode, String username, int rating, String body) {

		this.username = username;
		this.bookcode = bookcode;
		this.rating = rating;
		this.body = body;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getBookcode() {
		return bookcode;
	}
	public void setBookcode(int bookcode) {
		this.bookcode = bookcode;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	
}
