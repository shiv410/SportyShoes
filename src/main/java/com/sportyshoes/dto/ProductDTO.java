package com.sportyshoes.dto;

import lombok.Data;

@Data
public class ProductDTO {

	private Long productId;

	private String productName;

	private Integer categoryId;

	private int size;

	private Double price;

	private String description;

	private String imageName;

	public ProductDTO(Long productId, String productName, Integer categoryId, int size, Double price, String description,
			String imageName) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.categoryId = categoryId;
		this.size = size;
		this.price = price;
		this.description = description;
		this.imageName = imageName;
	}

	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ProductDTO [productId=" + productId + ", productName=" + productName + ", categoryId=" + categoryId
				+ ", size=" + size + ", price=" + price + ", description=" + description + ", imageName=" + imageName
				+ "]";
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

    	

}
