package com.springboot.dhtjdbcdemo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dhtjdbcdemo.dao.CommentDAO;
import com.springboot.dhtjdbcdemo.model.Book;
import com.springboot.dhtjdbcdemo.model.Comment;

@RestController
@CrossOrigin
public class CommentController {
	private CommentDAO commentDAO = new CommentDAO(); 
	
	@GetMapping("/comments/{bookcode}")
	public List<Comment> getCommentsByBookcode(@PathVariable int bookcode) {
		List<Comment> comments = commentDAO.selectComments(bookcode);
		return comments;
	}
	
	@PostMapping("/comments/addComment")
	public void addComment(@RequestBody Comment comment) {
		System.out.println(comment.toString()); 
		commentDAO.insertComment(comment);
		
	}
}
