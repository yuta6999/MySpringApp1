package com.example.myspringapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myspringapp.entity.Warehouse;
import com.example.myspringapp.repository.WarehouseRepository;

@Controller
@RequestMapping("/warehouses")
public class WarehouseController {

	@Autowired
	private WarehouseRepository warehouseRepository;

	@GetMapping
	public String listWarehouses(Model model) {
		model.addAttribute("warehouses", warehouseRepository.findAll());
		return "warehouse/list";
	}

	@GetMapping("/new")
	public String newWarehouseForm(Model model) {
		model.addAttribute("warehouse", new Warehouse());
		return "warehouse/form";
	}

	@PostMapping
	public String saveWarehouse(@ModelAttribute Warehouse warehouse) {
		warehouseRepository.save(warehouse);
		return "redirect:/warehouses";
	}

	@GetMapping("/{id}/edit")
	public String editWarehouseForm(@PathVariable Long id, Model model) {
		Warehouse warehouse = warehouseRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid warehouse ID"));
		model.addAttribute("warehouse", warehouse);
		return "warehouse/form";
	}

	@PostMapping("/{id}")
	public String updateWarehouse(@PathVariable Long id, @ModelAttribute Warehouse warehouse) {
		warehouse.setId(id); // 確認のため、IDを設定しておく
		warehouseRepository.save(warehouse);
		return "redirect:/warehouses";
	}

	@GetMapping("/{id}/delete")
	public String deleteWarehouse(@PathVariable Long id) {
		warehouseRepository.deleteById(id);
		return "redirect:/warehouses";
	}
}
