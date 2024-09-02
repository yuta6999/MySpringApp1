package com.example.myspringapp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myspringapp.entity.Inventory;
import com.example.myspringapp.entity.Product;
import com.example.myspringapp.repository.InventoryRepository;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	public List<Inventory> findByProduct(Product product) {
		return inventoryRepository.findByProduct(product);
	}

	public void save(Inventory inventory, String registeredBy) {
		inventory.setRegisteredAt(LocalDateTime.now());
		inventory.setRegisteredBy(registeredBy);
		inventoryRepository.save(inventory);
	}

	public void delete(Long id) {
		inventoryRepository.deleteById(id);
	}
}
