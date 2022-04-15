package com.java.project.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.project.Entity.UserEntity;
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


}
