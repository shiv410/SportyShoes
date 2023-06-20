package com.sportyshoes.model;

import java.math.BigDecimal;

//pojo
//@Entity
public class CartItem {

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long cartId;

	private Long productId;
	private String name;
	private BigDecimal rate; // per item
	private int quantity;
	private BigDecimal price; // total
	private String imageName;

	public CartItem(Long productId, String name, BigDecimal rate, int quantity, BigDecimal price, String imageName) {
		super();
		this.productId = productId;
		this.name = name;
		this.rate = rate;
		this.quantity = quantity;
		this.price = price;
		this.imageName = imageName;
	}

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CartItem [productId=" + productId + ", name=" + name + ", rate=" + rate + ", quantity=" + quantity
				+ ", price=" + price + ", imageName=" + imageName + "]";
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

}
