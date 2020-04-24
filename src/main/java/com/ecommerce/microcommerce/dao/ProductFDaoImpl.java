package com.ecommerce.microcommerce.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecommerce.microcommercemodel.Product;

@Repository //classe qui gère les données
public class ProductFDaoImpl implements ProductDao{
    public static List<Product>products=new ArrayList<>();
    
    public ProductFDaoImpl() {
    	 products.add(new Product(1, new String("Ordinateur portable"), 350,120));
         products.add(new Product(2, new String("Aspirateur Robot"), 500,200)); 
         products.add(new Product(3, new String("Table de Ping Pong"), 750,400));
    }
	@Override
	public List<Product> findAll() {
		return products;
	}

	@Override
	public Product findById(int id) {
		for(Product product : products) {
			if(id == product.getId()) {
				return product;
			}
		}
		return null;
	}

	@Override
	public Product save(Product product) {
		products.add(product);
		return product;
	}

}
