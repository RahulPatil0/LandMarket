package com.mca.landmarketproject.model;
import java.time.LocalDateTime;
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
public class Review {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="rating")
	private Integer rating;
	
	@Column(name="review_description")
	private String reviewDescription;
	
	@Column(name="review_date")
	private LocalDateTime reviewDate;
	
	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName = "id", nullable=false)
	private User user;
	

	@ManyToOne
	@JoinColumn(name="property_id", referencedColumnName = "id",nullable=false)
	private Property properties;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getReviewDescription() {
		return reviewDescription;
	}

	public void setReviewDescription(String reviewDescription) {
		this.reviewDescription = reviewDescription;
	}

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

	public LocalDateTime getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDateTime reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, rating, reviewDate, reviewDescription);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return Objects.equals(id, other.id) && Objects.equals(rating, other.rating)
				&& Objects.equals(reviewDate, other.reviewDate)
				&& Objects.equals(reviewDescription, other.reviewDescription);
	}

	@Override
	public String toString() {
		return "Reviews [id=" + id + ", rating="
				+ rating + ", reviewDescription=" + reviewDescription + ", reviewDate=" + reviewDate + "]";
	}
	
	

}
