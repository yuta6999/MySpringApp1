package com.example.myspringapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myspringapp.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
