package com.example.myspringapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myspringapp.entity.ManufacturingOrder;

public interface ManufacturingOrderRepository extends JpaRepository<ManufacturingOrder, Long> {
}
