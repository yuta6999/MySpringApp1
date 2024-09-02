package com.example.myspringapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myspringapp.entity.Inventory;
import com.example.myspringapp.repository.WarehouseRepository;
import com.example.myspringapp.service.InventoryService;
import com.example.myspringapp.service.ProductService;

@Controller
@RequestMapping("/inventories")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private WarehouseRepository warehouseRepository;

	@GetMapping
	public String listInventories(Model model) {
		model.addAttribute("inventories", inventoryService.findAll());
		return "inventory/list";
	}

	@GetMapping("/new")
	public String newInventoryForm(Model model) {
		model.addAttribute("inventory", new Inventory());
		model.addAttribute("products", productService.findAll());
		model.addAttribute("warehouses", warehouseRepository.findAll());
		return "inventory/form";
	}

	@PostMapping
	public String saveInventory(@ModelAttribute Inventory inventory) {
		inventoryService.save(inventory, "admin"); // Example registeredBy value
		return "redirect:/inventories";
	}

	@GetMapping("/{id}/edit")
	public String editInventoryForm(@PathVariable Long id, Model model) {
		Inventory inventory = inventoryService.findById(id);
		model.addAttribute("inventory", inventory);
		model.addAttribute("products", productService.findAll());
		model.addAttribute("warehouses", warehouseRepository.findAll());
		return "inventory/form";
	}

	@PostMapping("/{id}")
	public String updateInventory(@PathVariable Long id, @ModelAttribute Inventory inventory) {
		inventory.setId(id);
		inventoryService.save(inventory, "admin"); // Example registeredBy value
		return "redirect:/inventories";
	}

	@GetMapping("/{id}/delete")
	public String deleteInventory(@PathVariable Long id) {
		inventoryService.delete(id);
		return "redirect:/inventories";
	}
}
