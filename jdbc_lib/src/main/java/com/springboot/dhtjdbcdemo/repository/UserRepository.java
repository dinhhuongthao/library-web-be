package com.springboot.dhtjdbcdemo.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.dhtjdbcdemo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

	User findByUsername(String username);

	User findOneByUsernameAndPassword(String username, String password);

}
