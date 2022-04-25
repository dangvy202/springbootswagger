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
@Table(name = "products")
public class ProductsEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	 
	@Column(name="description") 
	private String description;
	 
	@Column(name="enabled") 
	private int enabled;
	  
	@Column(name="price") 
	public double price;
	 
	@Column(name="product_color") 
	private String product_color;
	  
	@Column(name="product_name") 
	private String product_name;
	 
	@Column(name="stock_quantity") 
	private int stock_quantity;
	  
	@Column(name="updated") 
	private String updated;
	  
	@Column(name="brand_id ") 
	private int brand_id ;
	 
	@Column(name="category_id ") 
	private int category_id ;
	 
	@Column(name="type_id ") 
	private int type_id ;
	
	@Column(name="short_description ") 
	private String short_description ;
	 
}
