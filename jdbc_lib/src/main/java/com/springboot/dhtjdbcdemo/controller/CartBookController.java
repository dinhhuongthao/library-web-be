package com.springboot.dhtjdbcdemo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dhtjdbcdemo.dao.CartBookDAO;
import com.springboot.dhtjdbcdemo.model.Book;
import com.springboot.dhtjdbcdemo.model.CartBook;

@RestController
@CrossOrigin
public class CartBookController {
	CartBookDAO cartbookDAO = new CartBookDAO();
	
	@GetMapping("/cart/{username}")
	public List<CartBook> getCartBooks(@PathVariable String username) {
		List<CartBook> cartbooks = cartbookDAO.selectAllCartBookByUsername(username);
		return cartbooks;
	}
	
	@PostMapping("cart/save")
	public void addCartBook(@RequestBody CartBook cartbook) {
		cartbookDAO.insertCartBook(cartbook);
		
	}
	
	@PutMapping("cart/update")
	public void updateCartBook(@RequestBody CartBook cartbook) {
		cartbookDAO.updateCartBook(cartbook);
		
	}
	
	@DeleteMapping("cart/{bookcartId}")
	public void deleteCartBook(@PathVariable int bookcartId) {
		cartbookDAO.deleteCartBook(bookcartId);
	}
	
}
