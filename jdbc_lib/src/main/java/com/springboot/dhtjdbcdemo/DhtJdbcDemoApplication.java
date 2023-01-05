package com.springboot.dhtjdbcdemo;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.springboot.dhtjdbcdemo.model.User;
import com.springboot.dhtjdbcdemo.repository.UserRepository;
import com.springboot.dhtjdbcdemo.util.FileUploadUtil;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class DhtJdbcDemoApplication {
	
	@Autowired
	private UserRepository repository;

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/images/**").addResourceLocations(FileUploadUtil.uploadDir)
		.setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());

	}
	
	public static void main(String[] args) {
		SpringApplication.run(DhtJdbcDemoApplication.class, args);
	}
	

}
