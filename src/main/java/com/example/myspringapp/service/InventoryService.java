package com.example.myspringapp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.myspringapp.entity.Inventory;
import com.example.myspringapp.entity.Product;
import com.example.myspringapp.repository.InventoryRepository;
import com.example.myspringapp.repository.UserRepository;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private UserRepository userRepository;

	public List<Inventory> findByProduct(Product product) {
		return inventoryRepository.findByProduct(product);
	}

	public void save(Inventory inventory) {
		inventory.setRegisteredAt(LocalDateTime.now());

		// ログインユーザーを取得
		String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUsername();

		// registeredByフィールドにログインユーザーを設定
		inventory.setRegisteredBy(username);

		inventoryRepository.save(inventory);
	}

	public void delete(Long id) {
		inventoryRepository.deleteById(id);
	}

	public List<Inventory> findAll() {
		return inventoryRepository.findAll();
	}

	public Inventory findById(Long id) {
		return inventoryRepository.findById(id).orElse(null);
	}
}
