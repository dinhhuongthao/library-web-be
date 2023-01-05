package com.springboot.dhtjdbcdemo.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.dhtjdbcdemo.dao.BookDAO;
import com.springboot.dhtjdbcdemo.model.Book;
import com.springboot.dhtjdbcdemo.util.FileUploadUtil;

@RestController
@CrossOrigin
public class BookController {
	
	private BookDAO bookDAO = new BookDAO();
	private static final String IMAGE_PRE_URL = "http://localhost:8080/images/";
	
	@GetMapping("/books")
	public List<Book> getBooks() {
		List<Book> books = bookDAO.selectAllBooks();
		for (Book book : books) {
			book.setImageName(IMAGE_PRE_URL + book.getImageName());
		}
		return books;
	}
	
	@GetMapping("/booktest")
	public String booktest() {
		return "Server is running";
	}
	
	@GetMapping("book/{bookcode}")
	private Book getBook(@PathVariable int bookcode) {
		Book book = bookDAO.selectBook(bookcode);
		book.setImageName(IMAGE_PRE_URL + book.getImageName());
		return book;
	}
	
	
	@PostMapping("book/save")
	public void addBook(@RequestBody Book book) {
		System.out.println(book.toString()); 
		bookDAO.insertBook(book);
		
	}
	
	@PostMapping(value = "/upload-file", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE }) 
	public String uploadImage(@RequestPart("book") String bookData, @RequestPart("file") MultipartFile file){
		Book book = Book.getJson(bookData);
		
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getName());
		System.out.println(file.getContentType());
		System.out.println(file.getSize());
		
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			FileUploadUtil.saveFile(filename, file);
			book.setImageName(filename);
			System.out.println(book.getImageName());
			System.out.println(book);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bookDAO.insertBook(book);
		
		return "Successfully Image is uploaded";
	}
	
//	@PutMapping("book/save/{bookcode}")
//	public void update(@RequestBody Book book) {
//		bookDAO.updateBook(book);
//		
//	}
	
	@PutMapping(value = "book/save/{bookcode}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public String update(@RequestPart("book") String bookData, @RequestPart(value = "file", required = false) MultipartFile file) {
		Book book = Book.getJson(bookData);
		Book book1 = bookDAO.selectBook(book.getBookcode());
		book.setImageName(book1.getImageName());
		
		if (file != null) {
			try {
				System.out.println(book.getBookcode());
				System.out.println(book1.getBookcode());
				
				// Delete old image
				FileUploadUtil.deleteFile(book1.getImageName());
				
				// Save new image and change imagePath
				String filename = StringUtils.cleanPath(file.getOriginalFilename());
				FileUploadUtil.saveFile(filename, file);
				book.setImageName(filename);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		bookDAO.updateBook(book);
		
		return "Successfully Updated";
		
	}
	@DeleteMapping("/book/{id}")
	private void deleteBook(@PathVariable int id) {
		bookDAO.deleteBook(id);
		
	}
}
