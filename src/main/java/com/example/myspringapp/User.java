package com.example.myspringapp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "user_name", nullable = false, length = 50)
	private String userName;

	// 空のコンストラクタ
	public User() {
	}

	//	// Getter、Setter
	//	public Integer getId() {
	//		return id;
	//	}
	//
	//	public void setId(Integer id) {
	//		this.id = id;
	//	}
	//
	//	public String getUserName() {
	//		return userName;
	//	}
	//
	//	public void setUserName(String userName) {
	//		this.userName = userName;
	//	}
}
