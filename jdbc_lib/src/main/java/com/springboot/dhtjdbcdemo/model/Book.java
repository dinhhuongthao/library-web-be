package com.springboot.dhtjdbcdemo.model;

import java.io.IOException;
import java.sql.Date;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Book {
	private int bookcode;
	private String title;
	private String author;
	private String category;
	private Date releaseDate;
	private int numOfPages;
	private String imageName;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(int bookcode, String title, String author, String category, Date releaseDate, int numOfPages,
			String imageName) {
		super();
		this.bookcode = bookcode;
		this.title = title;
		this.author = author;
		this.category = category;
		this.releaseDate = releaseDate;
		this.numOfPages = numOfPages;
		this.imageName = imageName;
	}



	public int getBookcode() {
		return bookcode;
	}

	public void setBookcode(int bookcode) {
		this.bookcode = bookcode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getNumOfPages() {
		return numOfPages;
	}

	public void setNumOfPages(int numOfPages) {
		this.numOfPages = numOfPages;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public String toString() {
		return "Book [bookcode=" + bookcode + ", title=" + title + ", author=" + author + ", category=" + category
				+ ", releaseDate=" + releaseDate + ", numOfPages=" + numOfPages + ", imageName=" + imageName + "]";
	}

	public static Book getJson(String book) {
		Book bookJson = new Book();
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			bookJson = objectMapper.readValue(book, Book.class);
		} catch (IOException err) {
			System.out.printf("Error", err.toString());
		}
		
		return bookJson;
	}
	
}
