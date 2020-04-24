package com.ecommerce.microcommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.microcommercemodel.Product;


public interface ProductDao {
	public List<Product>findAll();
	public Product findById(int id);
	public Product save(Product product);
}

