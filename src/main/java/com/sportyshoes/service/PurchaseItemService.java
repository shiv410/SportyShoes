package com.sportyshoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sportyshoes.model.PurchaseItem;
import com.sportyshoes.model.PurchaseOrder;
import com.sportyshoes.model.User;
import com.sportyshoes.repository.PurchaseItemRepository;

@Service
public class PurchaseItemService {

	@Autowired
	PurchaseItemRepository purchaseItemRepository;

	public PurchaseItem getPurchaseItemById(Long id) {
		return null;
	}

	public List<PurchaseItem> getAllItemsByPurchaseOrder(PurchaseOrder order) {
		return null;
	}

	public List<PurchaseItem> getAllPurchaseItemByUserId(User userId) {
		return null;
	}

	public PurchaseItem savePurchaseItem(PurchaseItem purchaseItem) {

		purchaseItemRepository.save(purchaseItem);
		return purchaseItem;
	}

	public List<PurchaseItem> getAllPurchaseItemList() {
		return purchaseItemRepository.findAll(Sort.by(Sort.Direction.ASC, "purchaseOrder_ID"));
	}

}
