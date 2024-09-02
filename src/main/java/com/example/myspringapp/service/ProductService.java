package com.example.myspringapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myspringapp.entity.Product;
import com.example.myspringapp.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product findById(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	public void save(Product product) {
		productRepository.save(product);
	}

	public void delete(Long id) {
		productRepository.deleteById(id);
	}
}
