package com.springboot.dhtjdbcdemo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dhtjdbcdemo.common.APIResponse;
import com.springboot.dhtjdbcdemo.dao.LoginRequestDTO;
import com.springboot.dhtjdbcdemo.dao.SignUpRequestDTO;
import com.springboot.dhtjdbcdemo.model.User;
import com.springboot.dhtjdbcdemo.repository.UserRepository;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;
	
	public APIResponse signUp(SignUpRequestDTO signUpRequestDTO) {
		APIResponse apiResponse = new APIResponse();
		Map<String, Object> body = new HashMap<>();
		
		// Validation
		User checkEntity = userRepository.findByUsername(signUpRequestDTO.getUsername());
		if (checkEntity != null) {
			body.put("status", "existed username");
			body.put("message", "Username existed. Choose another username and try again!");
			apiResponse.setData(body);
			return apiResponse;
		}
		
		
		// DTO to Entity
		User userEntity = new User();
		userEntity.setUsername(signUpRequestDTO.getUsername());
		userEntity.setPassword(signUpRequestDTO.getPassword());
		userEntity.setAddress(signUpRequestDTO.getAddress());
		userEntity.setPhone(signUpRequestDTO.getPhone());
		userEntity.setEmail(signUpRequestDTO.getEmail());
		
		// Store entity
		userEntity = userRepository.save(userEntity);
		
		// return
		body.put("status", "signup successfully");
		body.put("message", "Signup successfully");
		apiResponse.setData(body);
		
		return apiResponse;
	}

	public APIResponse login(LoginRequestDTO loginRequestDTO) {
		APIResponse apiResponse = new APIResponse();
		Map<String, Object> body = new HashMap<>();
		// Validation
		
		
		// Verify user exist with given email and password
		User user = userRepository.findOneByUsernameAndPassword(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
		
		// Response
		if (user == null) {
			body.put("status", "wrong username password");
			body.put("message", "Wrong username or password. Please try again!");
			apiResponse.setData(body);
			
		} else {
			body.put("status", "login successfully");
			body.put("message", "Login successfully!");
			body.put("role", user.getRole() == null ? "user" : user.getRole());
			apiResponse.setData(body);
		}
		
		// return
		
		
		return apiResponse;
		
	}

}
