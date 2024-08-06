package com.mca.landmarketproject.model;
import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Transaction {
	
	@Id
	@GeneratedValue
	@Column(name="transaction_id")
	private Integer id;
	
	@Column(name="transaction_date")
	private Date transactionDate;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="payment_method")
	private String paymentMethod;
	
	@Column(name="payment_status")
	private String paymentStatus;
	
	@ManyToOne
	@JoinColumn(name="buyer_id",referencedColumnName = "id",nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name="property_id",referencedColumnName = "id",nullable = false)
	private Property properties;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Property getProperties() {
		return properties;
	}

	public void setProperties(Property properties) {
		this.properties = properties;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String  paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(id, amount, paymentMethod, paymentStatus, transactionDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Objects.equals(id, other.id) && Objects.equals(amount, other.amount)
				&& Objects.equals(paymentMethod, other.paymentMethod)
				&& Objects.equals(paymentStatus, other.paymentStatus)
				&& Objects.equals(transactionDate, other.transactionDate);
	}

	@Override
	public String toString() {
		return "Transaction [Id=" +id +", transactionDate=" + transactionDate + ", amount=" + amount
				+ ", paymentMethod=" + paymentMethod + ", paymentStatus=" + paymentStatus + "]";
	}

	
	
	

}
