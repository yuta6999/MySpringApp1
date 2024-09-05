package com.example.myspringapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myspringapp.entity.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}
