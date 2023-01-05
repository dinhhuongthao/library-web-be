package com.springboot.dhtjdbcdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dhtjdbcdemo.common.APIResponse;
import com.springboot.dhtjdbcdemo.dao.LoginRequestDTO;
import com.springboot.dhtjdbcdemo.dao.SignUpRequestDTO;
import com.springboot.dhtjdbcdemo.service.LoginService;

@CrossOrigin
@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/signup")
	public ResponseEntity<APIResponse> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
		
		APIResponse apiResponse = loginService.signUp(signUpRequestDTO);
		
		return ResponseEntity
				.status(apiResponse.getStatus())
				.body(apiResponse);
	}
	
	@PostMapping("/login")
	public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO) {
		
		APIResponse apiResponse = loginService.login(loginRequestDTO);
		
		return ResponseEntity
				.status(apiResponse.getStatus())
				.body(apiResponse);
	}
}
