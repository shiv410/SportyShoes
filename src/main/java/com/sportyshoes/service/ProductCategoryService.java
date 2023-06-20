package com.sportyshoes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.model.ProductCategory;
import com.sportyshoes.repository.ProductCategoryRepository;



@Service
public class ProductCategoryService {
	
	@Autowired
	ProductCategoryRepository productCategoryRepository;
	
	public List<ProductCategory> getAllCategories() {
		return productCategoryRepository.findAll();		
	}
	
	
	public ProductCategory addProductCategory(ProductCategory productCategory) {
		return productCategoryRepository.save(productCategory);
	}
	

	public Optional<ProductCategory> getCategoryById(Integer id) {
		return productCategoryRepository.findById(id);
	}
	
	public void updateCategory(ProductCategory productCategory) {
		productCategoryRepository.save(productCategory);
	}
	
	public void deleteCategory(Integer id) {
		productCategoryRepository.deleteById(id);		
	}	

}
