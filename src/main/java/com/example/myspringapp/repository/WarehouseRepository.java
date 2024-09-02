package com.example.myspringapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myspringapp.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
