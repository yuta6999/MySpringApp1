package com.example.myspringapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myspringapp.entity.BOM;
import com.example.myspringapp.entity.ManufacturingOrder;
import com.example.myspringapp.entity.Product;
import com.example.myspringapp.entity.PurchaseOrder;
import com.example.myspringapp.entity.SalesOrder;
import com.example.myspringapp.repository.BOMRepository;
import com.example.myspringapp.repository.ManufacturingOrderRepository;
import com.example.myspringapp.repository.PurchaseOrderRepository;
import com.example.myspringapp.repository.SalesOrderRepository;

@Service
public class SalesOrderService {

	@Autowired
	private SalesOrderRepository salesOrderRepository;

	@Autowired
	private ManufacturingOrderRepository manufacturingOrderRepository;

	@Autowired
	private BOMRepository bomRepository;

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	public SalesOrder createSalesOrder(SalesOrder salesOrder) {
		salesOrder = salesOrderRepository.save(salesOrder);

		for (ManufacturingOrder mo : salesOrder.getManufacturingOrders()) {
			mo.setSalesOrder(salesOrder);

			// 製造オーダーの作成
			Product product = mo.getProduct();
			mo = manufacturingOrderRepository.save(mo);

			// BOMに基づいて発注を自動生成
			List<BOM> boms = bomRepository.findByProduct(product);
			for (BOM bom : boms) {
				PurchaseOrder po = new PurchaseOrder();
				po.setMaterial(bom.getMaterial());
				po.setManufacturingOrder(mo);
				po.setQuantity(bom.getQuantity() * mo.getQuantity()); // 必要数量を計算
				purchaseOrderRepository.save(po);
			}
		}

		return salesOrder;
	}
}
