package com.springboot.dhtjdbcdemo.model;

public class CartBook {
	private int id;
	private String username;
	private int bookcode;
	private int quantity;
	
	public CartBook() {
		
	}
	
	public CartBook(int id, String username, int bookcode, int quantity) {
		this.id = id;
		this.username = username;
		this.bookcode = bookcode;
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBookcode() {
		return bookcode;
	}
	public void setBookcode(int bookcode) {
		this.bookcode = bookcode;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
