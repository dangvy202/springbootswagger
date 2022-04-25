package com.java.project.service;

import java.util.List;
import java.util.Optional;

import com.java.project.Entity.ProductsEntity;
import com.java.project.Entity.UserEntity;

public interface UserService {
	List<UserEntity> findAll();
	Optional<UserEntity> findById(Long id);
	UserEntity save(UserEntity user);
	void remove(Long id);
	UserEntity updateUser(Optional<UserEntity> user);
}
