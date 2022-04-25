package com.java.project.service.Impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.project.Entity.ProductsEntity;
import com.java.project.Entity.UserEntity;
import com.java.project.exception.ErrorEmptyInputExceptionHandler;
import com.java.project.repository.ProductsRepository;
import com.java.project.repository.UserRepository;
import com.java.project.service.ProductsService;
import com.java.project.service.UserService;

@Service
public class ProductsServiceImpl implements ProductsService{
	
	@Autowired
	public ProductsRepository productsRepo;
	
	@Override
	public List<ProductsEntity> findAll() {
		return productsRepo.findAll();
	}

	@Override
	public Optional<ProductsEntity> findById(Long id) {
		if(id == null) {
			throw new ErrorEmptyInputExceptionHandler("500", "Internal Server Error");			
		}
		else if(productsRepo.findById(id).isPresent() == false) {
			throw new NoSuchElementException("404");	
		}
		return productsRepo.findById(id);
	}

	@Override
	public ProductsEntity save(ProductsEntity products) {
		return productsRepo.save(products);
	}

	@Override
	public void remove(Long id) {
		productsRepo.deleteById(id);
	}

	@Override
	public ProductsEntity updateProducts(Optional<ProductsEntity> products) {
		return productsRepo.save(products.get());
	}
}
