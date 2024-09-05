package com.example.myspringapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myspringapp.entity.SalesOrder;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {
}
