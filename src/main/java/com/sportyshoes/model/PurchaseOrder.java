package com.sportyshoes.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class PurchaseOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long ID;

	@ManyToOne
	@JoinColumn(name = "user_fk")
	private User user;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name = "gross_total")
	private BigDecimal total;

	public PurchaseOrder(Long iD, User user, Date date, BigDecimal total) {
		super();
		ID = iD;
		this.user = user;
		this.date = date;
		this.total = total;
	}

	public PurchaseOrder() {
		super();
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [ID=" + ID + ", user=" + user + ", date=" + date + ", total=" + total + "]";
	}
	
	

}
