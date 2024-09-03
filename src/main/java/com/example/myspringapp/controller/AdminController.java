package com.example.myspringapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.myspringapp.entity.Role;
import com.example.myspringapp.entity.User;
import com.example.myspringapp.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/users")
	public String showUsers(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "admin/user/list";
	}

	@GetMapping("/users/new")
	public String createUser(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", userService.getAllRoles());
		return "admin/user/form";
	}

	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable Long id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		model.addAttribute("roles", userService.getAllRoles());
		return "admin/user/form";
	}

	@PostMapping("/users/save")
	public String saveUser(@ModelAttribute User user, @RequestParam List<Long> roles) {
		if (user.getId() == null) {
			// 新規ユーザー作成の場合、パスワードをエンコードする
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		} else {
			// 既存ユーザーのパスワード変更の場合、パスワードが変更された場合のみエンコードする
			if (user.getPassword() != null && !user.getPassword().isEmpty()) {
				// 新しいパスワードをエンコードして保存
				user.setPassword(passwordEncoder.encode(user.getPassword()));
			} else {
				// パスワードが空の場合は既存のパスワードを保持
				user.setPassword(userService.getUserById(user.getId()).getPassword());
			}
		}

		user.getRoles().clear(); // 既存のロールをクリア
		for (Long roleId : roles) {
			Role role = userService.getRoleById(roleId);
			user.addRole(role);
		}
		userService.saveUser(user);
		return "redirect:/admin/users";
	}

	@GetMapping("/roles")
	public String showRoles(Model model) {
		model.addAttribute("roles", userService.getAllRoles());
		return "admin/role/list";
	}

	@GetMapping("/roles/edit/{id}")
	public String editRole(@PathVariable Long id, Model model) {
		model.addAttribute("role", userService.getRoleById(id));
		return "admin/role/form";
	}

	@PostMapping("/roles/save")
	public String saveRole(@ModelAttribute Role role) {
		userService.saveRole(role);
		return "redirect:/admin/role/form";
	}
}
