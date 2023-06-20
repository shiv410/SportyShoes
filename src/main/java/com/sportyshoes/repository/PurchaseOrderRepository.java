package com.sportyshoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportyshoes.model.PurchaseOrder;


@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long>{

}
