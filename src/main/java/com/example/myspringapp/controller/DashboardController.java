package com.example.myspringapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.example.myspringapp.entity.Inventory;
import com.example.myspringapp.entity.Product;
import com.example.myspringapp.entity.Warehouse;
import com.example.myspringapp.repository.WarehouseRepository;
import com.example.myspringapp.service.InventoryService;
import com.example.myspringapp.service.ProductService;

@Controller
public class DashboardController {

	@Autowired
	private ProductService productService;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private WarehouseRepository warehouseRepository;

	@GetMapping("/")
	public RedirectView redirectToDashboard() {
		return new RedirectView("/dashboard");
	}

	@GetMapping("/dashboard")
	public String showDashboard(Model model) {
		List<Product> products = productService.findAll();
		List<Inventory> inventories = inventoryService.findAll();
		List<Warehouse> warehouses = warehouseRepository.findAll();

		model.addAttribute("products", products);
		model.addAttribute("inventories", inventories);
		model.addAttribute("warehouses", warehouses);

		return "dashboard";
	}
}
