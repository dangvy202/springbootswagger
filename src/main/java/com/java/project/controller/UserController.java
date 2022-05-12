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

import com.java.project.Entity.UserEntity;
import com.java.project.RabbitMQ.constance.RabbitMQConstance;
import com.java.project.service.Impl.RabbitMQService;
//import com.java.project.exception.ErrorExceptionHandler;
import com.java.project.service.Impl.UserServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class UserController extends Exception {

	@Autowired
	private RabbitMQService rabbitmqService;
	
	
	
	@Autowired
	public UserServiceImpl userImpl;

	// list user
	@GetMapping("/user/list")
	public ResponseEntity<List<UserEntity>> HomeController() { // List<UserEntity> HomeController() {
		List<UserEntity> listUser = userImpl.findAll();
		this.rabbitmqService.messageMQResponse(RabbitMQConstance.FILA_ESTOQUE,listUser);
		return new ResponseEntity<List<UserEntity>>(listUser, HttpStatus.OK);
	}

	// get id
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getuserId(@PathVariable("id") Long id) {
		Object getIdUser = userImpl.findById(id);
		this.rabbitmqService.messageMQResponse(RabbitMQConstance.FILA_ESTOQUE,getIdUser);
		return new ResponseEntity<Object>(getIdUser, HttpStatus.OK);
	}

	// get string user
//	@GetMapping("/user/{user}")
//	public ResponseEntity<?> getuserId(@PathVariable("user") String user) {
//		Object getIdUser = userImpl.findByUserName(user);
//		return new ResponseEntity<Object>(getIdUser, HttpStatus.OK);
//	}

	// delete user
	@DeleteMapping("/user/{id}")
	public String deleteController(Long id) {
		String message;
		Optional<UserEntity> checkId = userImpl.findById(id);
		if (checkId.isPresent() == true) {
			userImpl.remove(id);
			message = "DELETE DONE";
		} else {
			message = "DELETE FAIL";
		}
		return message;
	}

	// edit user
	@PutMapping("/user/{id}")
	public String editController(Long id, UserEntity user) {
		String message;

		Optional<UserEntity> checkIdExist = userImpl.findById(id);

		if (checkIdExist.isPresent() == true) {
			checkIdExist.get().setEmail(user.getEmail());
			checkIdExist.get().setEnabled(user.getEnabled());
			checkIdExist.get().setPassword(user.getPassword());
			checkIdExist.get().setRole(user.getRole());
			checkIdExist.get().setUsername(user.getUsername());
			checkIdExist.get().setAmount(user.getAmount());
			checkIdExist.get().setCustomer_order_id(user.getCustomer_order_id());
			checkIdExist.get().setPayment_date(user.getPayment_date());
			checkIdExist.get().setPayment_method(user.getPayment_method());
			checkIdExist.get().setStatus(user.getStatus());
			userImpl.updateUser(checkIdExist);
			message = "Update Done";
		} else {
			message = "Update Fail";
		}

		return message;
	}

	// new user
	@PostMapping("/user")
	public String createController(UserEntity user) {
		String message;
		UserEntity userSet = new UserEntity();
		// get current date
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		userSet.setEmail(user.getEmail());
		userSet.setEnabled(user.getEnabled());
		userSet.setPassword(user.getPassword());
		userSet.setRole(user.getRole());
		userSet.setUsername(user.getUsername());
		userSet.setAmount(user.getAmount());
		userSet.setCustomer_order_id(user.getCustomer_order_id());
		userSet.setPayment_date(user.getPayment_date());
		userSet.setPayment_method(user.getPayment_method());
		userSet.setStatus(user.getStatus());

		List<UserEntity> checkArrIdAfter = userImpl.findAll();
		Long idAfter = checkArrIdAfter.stream().count();
		userImpl.save(userSet);

		List<UserEntity> checkArrIdBefore = userImpl.findAll();
		Long idBefore = checkArrIdBefore.stream().count();
		// check result
		if (idAfter < idBefore) {
			message = "Success Create";
		} else {
			message = "Fail Create";
		}
		return message;
	}

}
