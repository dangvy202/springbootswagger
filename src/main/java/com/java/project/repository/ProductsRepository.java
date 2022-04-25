package com.java.project.repository;

import com.java.project.Entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity , Long> {

}
