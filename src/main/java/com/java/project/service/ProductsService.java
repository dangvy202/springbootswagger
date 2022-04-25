package com.java.project.service;

import java.util.List;
import java.util.Optional;

import com.java.project.Entity.ProductsEntity;

public interface ProductsService {
	List<ProductsEntity> findAll();
	Optional<ProductsEntity> findById(Long id);
	ProductsEntity save(ProductsEntity products);
	void remove(Long id);
	ProductsEntity updateProducts(Optional<ProductsEntity> products);
}
