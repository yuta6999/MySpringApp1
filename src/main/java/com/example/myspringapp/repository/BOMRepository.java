package com.example.myspringapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myspringapp.entity.BOM;
import com.example.myspringapp.entity.Product;

public interface BOMRepository extends JpaRepository<BOM, Long> {
	List<BOM> findByProduct(Product product);
}
