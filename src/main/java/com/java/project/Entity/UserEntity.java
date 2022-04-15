package com.java.project.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "users")
public class UserEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	 
	@Column(name="email") 
	private String email;
	 
	@Column(name="enabled") 
	private int enabled;
	  
	@Column(name="password") 
	public String password;
	 
	@Column(name="role") 
	private String role;
	  
	@Column(name="username") 
	private String username;
	 
	@Column(name="amount") 
	private int amount;
	  
	@Column(name="customer_order_id") 
	private int customer_order_id;
	  
	@Column(name="payment_date") 
	private String payment_date;
	 
	@Column(name="payment_method") 
	private String payment_method;
	 
	@Column(name="status") 
	private int status;
	 
}
