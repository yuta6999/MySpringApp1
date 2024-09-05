package com.example.myspringapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.myspringapp.entity.Role;
import com.example.myspringapp.entity.User;
import com.example.myspringapp.entity.Warehouse;
import com.example.myspringapp.repository.RoleRepository;
import com.example.myspringapp.repository.UserRepository;
import com.example.myspringapp.repository.WarehouseRepository;

@Configuration
public class DataInitializer {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private WarehouseRepository warehouseRepository;

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

			// 倉庫が存在しない場合、デフォルトの倉庫を作成
			if (warehouseRepository.count() == 0) {
				Warehouse warehouse1 = new Warehouse();
				warehouse1.setName("第一倉庫");
				warehouse1.setLocation("東京");
				warehouseRepository.save(warehouse1);

				Warehouse warehouse2 = new Warehouse();
				warehouse2.setName("第二倉庫");
				warehouse2.setLocation("神奈川");
				warehouseRepository.save(warehouse2);

				Warehouse warehouse3 = new Warehouse();
				warehouse3.setName("第三倉庫");
				warehouse3.setLocation("埼玉");
				warehouseRepository.save(warehouse3);
			}
		};
	}
}
