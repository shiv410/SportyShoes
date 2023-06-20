package com.sportyshoes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class ProductCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id")
	private int id;

	private String brandName;

	private String style;        
	
	private String targetGroup;  // gender: Male/Female/Kids==>boys/girls

	public ProductCategory(int id, String brandName, String style, String targetGroup) {
		super();
		this.id = id;
		this.brandName = brandName;
		this.style = style;
		this.targetGroup = targetGroup;
	}

	public ProductCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getTargetGroup() {
		return targetGroup;
	}

	public void setTargetGroup(String targetGroup) {
		this.targetGroup = targetGroup;
	}

	@Override
	public String toString() {
		return "ProductCategory [id=" + id + ", brandName=" + brandName + ", style=" + style + ", targetGroup="
				+ targetGroup + "]";
	}

		

}
