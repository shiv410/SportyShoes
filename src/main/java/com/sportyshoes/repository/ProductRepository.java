package com.sportyshoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sportyshoes.model.Product;
import com.sportyshoes.model.ProductCategory;



@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product>findByProductCategory(ProductCategory productCategory);
	
	
	@Query(value = "SELECT * FROM product JOIN product_category ON product.product_category_fk=product_category.category_id AND product_category.brand_name =:brand_name",nativeQuery = true)
	List<Product> findAllProductsByBrand(@Param("brand_name") String brand_name);
	
	
	@Query(value = "SELECT * FROM product JOIN product_category ON product.product_category_fk=product_category.category_id AND product_category.style =:style",nativeQuery = true)
	List<Product> findAllProductsByStyle(@Param("style") String style);
	
	
	@Query(value = "SELECT * FROM product JOIN product_category ON product.product_category_fk=product_category.category_id AND product_category.target_group =:target_group",  nativeQuery = true)
	List<Product> findAllProductsByTargetGroup(@Param("target_group") String target_group);


	List<Product> findAllByProductCategory_Id(int id);


}
