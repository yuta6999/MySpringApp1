package com.example.myspringapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myspringapp.entity.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}
