package com.example.myspringapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.myspringapp.entity.Role;
import com.example.myspringapp.entity.User;
import com.example.myspringapp.repository.RoleRepository;
import com.example.myspringapp.repository.UserRepository;

@Configuration
public class DataInitializer {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	CommandLineRunner init() {
		return args -> {
			// ロールが存在しない場合、デフォルトのロールを作成
			if (roleRepository.count() == 0) {
				Role adminRole = new Role();
				adminRole.setName("ROLE_ADMIN");
				roleRepository.save(adminRole);

				Role userRole = new Role();
				userRole.setName("ROLE_USER");
				roleRepository.save(userRole);
			}

			// ユーザーが存在しない場合、デフォルトのユーザーを作成
			if (userRepository.count() == 0) {
				User admin = new User();
				admin.setUsername("admin");
				admin.setPassword(passwordEncoder.encode("123"));

				// デフォルトの管理者ロールを取得し、ユーザーに割り当て
				Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElseThrow();
				admin.getRoles().add(adminRole);

				userRepository.save(admin);
			}
		};
	}
}
