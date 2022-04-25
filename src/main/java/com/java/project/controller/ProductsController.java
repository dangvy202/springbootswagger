package com.java.project.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.project.Entity.ProductsEntity;
import com.java.project.Entity.UserEntity;
import com.java.project.service.Impl.ProductsServiceImpl;
//import com.java.project.exception.ErrorExceptionHandler;
import com.java.project.service.Impl.UserServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class ProductsController extends Exception{
	
	@Autowired
	public ProductsServiceImpl productsImpl;
	
	//list user
	@GetMapping("/product/list")
	public ResponseEntity<List<ProductsEntity>> HomeController() { //List<UserEntity> HomeController() {
		List<ProductsEntity> listUser = productsImpl.findAll();
		return new ResponseEntity<List<ProductsEntity>>(listUser,HttpStatus.OK);
	}
	//get id
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProductsId(@PathVariable("id") Long id) {
		Object getIdProducts = productsImpl.findById(id);
		return new ResponseEntity<Object>(getIdProducts , HttpStatus.OK);
	}
	//delete user
	@DeleteMapping("/product/{id}")
	public String deleteController(Long id) {
		String message;
		Optional<ProductsEntity> checkId = productsImpl.findById(id);
		if(checkId.isPresent() == true) {
			productsImpl.remove(id);
			message = "DELETE DONE";
		}else {
			message = "DELETE FAIL";
		}
		return message;
	}
	//edit user
	@PutMapping("/product/{id}")
	public String editController(Long id, ProductsEntity products) {
		String message;
		
		Optional<ProductsEntity> checkIdExist = productsImpl.findById(id);
		
		if(checkIdExist.isPresent() == true) {
			checkIdExist.get().setDescription(products.getDescription());
			checkIdExist.get().setEnabled(products.getEnabled());
			checkIdExist.get().setPrice(products.getPrice());
			checkIdExist.get().setProduct_color(products.getProduct_color());
			checkIdExist.get().setProduct_name(products.getProduct_name()); 
			checkIdExist.get().setStock_quantity(products.getStock_quantity());
			checkIdExist.get().setUpdated(products.getUpdated());
			checkIdExist.get().setBrand_id(products.getBrand_id());
			checkIdExist.get().setCategory_id(products.getCategory_id());  
			checkIdExist.get().setType_id(products.getType_id());
			checkIdExist.get().setShort_description(products.getShort_description());
			productsImpl.updateProducts(checkIdExist);
			message = "Update Done";
		}else {
			message = "Update Fail";
		}
		
		return message;
	}
	//new user
	@PostMapping("/product")
	public String createController(ProductsEntity products) {
		String message;
		ProductsEntity productsSet = new ProductsEntity();
		 // get current date 
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
		Date date = new Date();
		
		productsSet.setDescription(products.getDescription());
		productsSet.setEnabled(products.getEnabled());
		productsSet.setPrice(products.getPrice());
		productsSet.setProduct_color(products.getProduct_color());
		productsSet.setProduct_name(products.getProduct_name()); 
		productsSet.setStock_quantity(products.getStock_quantity());
		productsSet.setUpdated(products.getUpdated());
		productsSet.setBrand_id(products.getBrand_id());
		productsSet.setCategory_id(products.getCategory_id());  
		productsSet.setType_id(products.getType_id());
		productsSet.setShort_description(products.getShort_description());
		
		List<ProductsEntity> checkArrIdAfter = productsImpl.findAll();
		Long idAfter = checkArrIdAfter.stream().count();
		productsImpl.save(productsSet);
		
		List<ProductsEntity> checkArrIdBefore = productsImpl.findAll();
		Long idBefore = checkArrIdBefore.stream().count();
		//check result
		if(idAfter < idBefore) {
			message = "Success Create";
		}else {
			message = "Fail Create";
		}
		return message;
	}
	
}
