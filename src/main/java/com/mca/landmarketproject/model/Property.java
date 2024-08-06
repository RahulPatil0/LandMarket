package com.mca.landmarketproject.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity

public class Property {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="property_address",nullable = false)
	private String propertyAddress;
	
	@Column(name="property_city")
	private String propertyCity;
	
	@Column(name="property_state")
	private String propertyState;
	
	@Column(name="property_zip_code")
	private String propertyZipCode;
	
	@Column(name="property_size")
	private String propertySize;
	
	@Column(name="property_status")
	 private String propertyStatus;
	
	@Column(name="property_price")
	 private String propertyPrice;
	
	@OneToMany(mappedBy="property")
	private List<PropertyImage> propertyImages;
	
	public String getPropertyPrice() {
		return propertyPrice;
	}

	public void setPropertyPrice(String propertyPrice) {
		this.propertyPrice = propertyPrice;
	}

	public void setPropertyCity(String propertyCity) {
		this.propertyCity = propertyCity;
	}
	
	@OneToMany(mappedBy="properties")
	private List<Transaction> transaction ;

	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName = "id",nullable = false)
	private User user;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany(mappedBy="properties")
	private List<Review> reviews;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	public String getPropertyCity() {
		return propertyCity;
	}

	public void setPropertyCcity(String propertyCity) {
		this.propertyCity = propertyCity;
	}

	public String getPropertyState() {
		return propertyState;
	}

	public void setPropertyState(String propertyState) {
		this.propertyState = propertyState;
	}

	public String getPropertyZipCode() {
		return propertyZipCode;
	}

	public void setPropertyZipCode(String propertyZipCode) {
		this.propertyZipCode = propertyZipCode;
	}

	public String getPropertySize() {
		return propertySize;
	}

	public void setPropertySize(String propertySize) {
		this.propertySize = propertySize;
	}

	public String getPropertyStatus() {
		return propertyStatus;
	}

	public void setPropertyStatus(String propertyStatus) {
		this.propertyStatus = propertyStatus;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id, propertyAddress, propertyCity, propertyPrice, propertySize, propertyState,
				propertyStatus, propertyZipCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Property other = (Property) obj;
		return Objects.equals(id, other.id) && Objects.equals(propertyAddress, other.propertyAddress)
				&& Objects.equals(propertyCity, other.propertyCity)
				&& Objects.equals(propertyPrice, other.propertyPrice)
				&& Objects.equals(propertySize, other.propertySize)
				&& Objects.equals(propertyState, other.propertyState)
				&& Objects.equals(propertyStatus, other.propertyStatus)
				&& Objects.equals(propertyZipCode, other.propertyZipCode);
	}

	@Override
	public String toString() {
		return "Property [id=" + id + ", propertyAddress=" + propertyAddress + ", propertyCity=" + propertyCity
				+ ", propertyState=" + propertyState + ", propertyZipCode=" + propertyZipCode + ", propertySize="
				+ propertySize + ", propertyStatus=" + propertyStatus + ", propertyPrice=" + propertyPrice + "]";
	}

	

}
