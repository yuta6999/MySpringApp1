package com.example.myspringapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myspringapp.entity.Inventory;
import com.example.myspringapp.entity.Product;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	List<Inventory> findByProduct(Product product);
}
