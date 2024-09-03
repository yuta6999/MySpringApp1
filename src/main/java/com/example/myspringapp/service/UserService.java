package com.example.myspringapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myspringapp.entity.Role;
import com.example.myspringapp.entity.User;
import com.example.myspringapp.repository.RoleRepository;
import com.example.myspringapp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	public Role getRoleById(Long id) {
		return roleRepository.findById(id).orElse(null);
	}

	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	public void deleteRole(Long id) {
		roleRepository.deleteById(id);
	}
}
