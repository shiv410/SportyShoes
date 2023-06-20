package com.sportyshoes.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportyshoes.model.PurchaseItem;
import com.sportyshoes.model.User;


@Repository
public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {
	
	List<PurchaseItem> findByUser(User user, Sort sort);

}
