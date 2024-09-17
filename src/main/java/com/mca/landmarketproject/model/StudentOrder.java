package com.mca.landmarketproject.model;

import java.util.Objects;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity

@Table(name = "student_orders")

public class StudentOrder {

	@Id

	@GeneratedValue

	private Integer orderId;

	private String name;

	private String email;

	private String phoneNumber;

	private String course;

	private Double amount;

	private String orderStatus;

	private String razorPayOrderID;

	public Integer getOrderId() {

		return orderId;

	}

	public void setOrderId(Integer orderId) {

		this.orderId = orderId;

	}

	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

	public String getEmail() {

		return email;

	}

	public void setEmail(String email) {

		this.email = email;

	}

	public String getPhoneNumber() {

		return phoneNumber;

	}

	public void setPhoneNumber(String phoneNumber) {

		this.phoneNumber = phoneNumber;

	}

	public String getCourse() {

		return course;

	}

	public void setCourse(String course) {

		this.course = course;

	}

	public Double getAmount() {

		return amount;

	}

	public void setAmount(Double amount) {

		this.amount = amount;

	}

	public String getOrderStatus() {

		return orderStatus;

	}

	public void setOrderStatus(String orderStatus) {

		this.orderStatus = orderStatus;

	}

	public String getRazorPayOrderID() {

		return razorPayOrderID;

	}

	public void setRazorPayOrderID(String razorPayOrderID) {

		this.razorPayOrderID = razorPayOrderID;

	}

	

	@Override
	public String toString() {
		return "StudentOrder [orderId=" + orderId + ", name=" + name + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", course=" + course + ", amount=" + amount + ", orderStatus=" + orderStatus
				+ ", razorPayOrderID=" + razorPayOrderID + "]";
	}

	@Override

	public int hashCode() {

		return Objects.hash(amount, course, email, name, orderId, orderStatus, phoneNumber, razorPayOrderID);

	}

	@Override

	public boolean equals(Object obj) {

		if (this == obj)

			return true;

		if (obj == null)

			return false;

		if (getClass() != obj.getClass())

			return false;

		StudentOrder other = (StudentOrder) obj;

		return Objects.equals(amount, other.amount) && Objects.equals(course, other.course)

				&& Objects.equals(email, other.email) && Objects.equals(name, other.name)

				&& Objects.equals(orderId, other.orderId) && Objects.equals(orderStatus, other.orderStatus)

				&& Objects.equals(phoneNumber, other.phoneNumber)

				&& Objects.equals(razorPayOrderID, other.razorPayOrderID);

	}
}
