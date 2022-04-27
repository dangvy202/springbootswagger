package com.java.project.service.Impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.project.Entity.UserEntity;
import com.java.project.exception.ErrorEmptyInputExceptionHandler;
import com.java.project.repository.UserRepository;
import com.java.project.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	public UserRepository userRepo;
	
	@Override
	public List<UserEntity> findAll() {
		return userRepo.findAll();
	}

	@Override
	public Optional<UserEntity> findById(Long id) {
		if(id == null) {
			throw new ErrorEmptyInputExceptionHandler("500", "Internal Server Error");			
		}
		else if(userRepo.findById(id).isPresent() == false) {
			throw new NoSuchElementException("404");	
		}
		return userRepo.findById(id);
	}

	@Override
	public UserEntity save(UserEntity user) {
		return userRepo.save(user);
	}

	@Override
	public void remove(Long id) {
		userRepo.deleteById(id);
	}

	@Override
	public UserEntity updateUser(Optional<UserEntity> user) {
		return userRepo.save(user.get());
	}

//	@Override
//	public List<UserEntity> findByUserName(String user) {
//		System.out.println("user implement user" +user);
//		System.out.println("user implement useraa" +userRepo.findByName(user));
//		return userRepo.findByName(user);
//	}

}
