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
import com.example.myspringapp.entity.Product;
import com.example.myspringapp.service.InventoryService;
import com.example.myspringapp.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private InventoryService inventoryService;

	@GetMapping
	public String listProducts(Model model) {
		model.addAttribute("products", productService.findAll());
		return "product/list";
	}

	@GetMapping("/{id}")
	public String viewProduct(@PathVariable Long id, Model model) {
		Product product = productService.findById(id);
		model.addAttribute("product", product);
		model.addAttribute("inventories", inventoryService.findByProduct(product));
		return "product/view";
	}

	@GetMapping("/new")
	public String newProductForm(Model model) {
		model.addAttribute("product", new Product());
		return "product/form";
	}

	@PostMapping
	public String saveProduct(@ModelAttribute Product product) {
		productService.save(product);
		return "redirect:/products";
	}

	@GetMapping("/{id}/delete")
	public String deleteProduct(@PathVariable Long id) {
		productService.delete(id);
		return "redirect:/products";
	}

	@GetMapping("/{productId}/inventories/new")
	public String newInventoryForm(@PathVariable Long productId, Model model) {
		Product product = productService.findById(productId);
		Inventory inventory = new Inventory();
		inventory.setProduct(product);
		model.addAttribute("inventory", inventory);
		return "inventory/form";
	}

	@PostMapping("/{productId}/inventories")
	public String saveInventory(@PathVariable Long productId, @ModelAttribute Inventory inventory) {
		Product product = productService.findById(productId);
		inventory.setProduct(product);
		inventoryService.save(inventory);
		return "redirect:/products/" + productId;
	}

	@GetMapping("/{productId}/inventories/{inventoryId}/delete")
	public String deleteInventory(@PathVariable Long productId, @PathVariable Long inventoryId) {
		inventoryService.delete(inventoryId);
		return "redirect:/products/" + productId;
	}
}
