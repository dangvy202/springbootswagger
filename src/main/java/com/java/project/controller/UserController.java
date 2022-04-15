package com.java.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.project.Entity.UserEntity;
import com.java.project.service.Impl.UserServiceImpl;

@RestController
public class UserController {
	
	//@GetMapping("/api/user")
	/*public List<UserEntity> getAlluser(){
		return List<UserEntity>;
	}*/
	@Autowired
	public UserServiceImpl userImpl;
	
	@GetMapping("/user")
	public List<UserEntity> HomeController() {
		
		return userImpl.findAll();
	}
	@GetMapping("{id}")
	public Optional<UserEntity> getuserId(Long id){
		return userImpl.findById(id);
	}
}
