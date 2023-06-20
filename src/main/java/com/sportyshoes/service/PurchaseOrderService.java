package com.sportyshoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.model.PurchaseOrder;
import com.sportyshoes.repository.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {

	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;

	public PurchaseOrder getPurchaseById(long id) {
		return null;
	}

	public List<PurchaseOrder> getAllPurchaseOrders() {
		return null;
	}

	public List<PurchaseOrder> getAllPurchaseOrderByUserId(Long userId) {
		return null;
	}

	public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder) {
		purchaseOrderRepository.save(purchaseOrder);
		return purchaseOrder;
	}
}
