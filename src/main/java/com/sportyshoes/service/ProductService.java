package com.sportyshoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.model.Product;
import com.sportyshoes.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductCategoryService productCategoryService;
	

	// To get the all products
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	// To add a new product
	public void addProduct(Product product) {
		productRepository.save(product);
	}

	// Find the product by id
	public Product getProductById(Long productId) {
		return productRepository.findById(productId).get();
	}

	// To update a product
	public void updateProduct(Product product) {
		if (productRepository.findById(product.getProductId()).get() != null)
			productRepository.save(product);
	}
	
	
	
//	public void updateProduct(ProductDTO productDTO) throws NotFoundException {
//	    Product product = productService.getProductById(productDTO.getProductId()).orElseThrow(NotFoundException::new);
//
//	    // Update the product fields with the values from the productDTO
//	    product.setProductName(productDTO.getProductName());
//	    product.setSize(productDTO.getSize());
//	    product.setPrice(productDTO.getPrice());
//	    product.setDescription(productDTO.getDescription());
//	    product.setImageName(productDTO.getImageName());
//
//	    // Set the product category
//	    ProductCategory category = productCategoryService.getCategoryById(productDTO.getCategoryId()).orElseThrow(NotFoundException::new);
//	    product.setProductCategory(category);
//
//	    // Save the updated product
//	    productService.saveProduct(product);
//	}

	

	public void deleteProductById(Long id) {
		productRepository.deleteById(id);
	}

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	public List<Product> getAllProductsByCategoryId(int id) {
		return productRepository.findAllByProductCategory_Id(id);
	}
	
	
	

}
