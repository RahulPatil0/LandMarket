package com.mca.landmarketproject.model;

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
public class Notification {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="is_read")
	private String isRead;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name="property_status")
	private String propertyStatus;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName = "id",nullable = false)
	private User user;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getPropertyStatus() {
		return propertyStatus;
	}

	public void setPropertyStatus(String propertyStatus) {
		this.propertyStatus = propertyStatus;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id, isRead, propertyStatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notification other = (Notification) obj;
		return Objects.equals(id, other.id) && Objects.equals(isRead, other.isRead)
				 && Objects.equals(propertyStatus, other.propertyStatus);
				
	}

	@Override
	public String toString() {
		return "Notification [notificationId=" +id + ", isRead=" + isRead + ", propertyStatus=" + propertyStatus + "]";
	}


}
	
	
	