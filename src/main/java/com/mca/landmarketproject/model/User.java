package com.mca.landmarketproject.model;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="email",nullable=false,unique=true)
	private String email;
	
	@Column(name="phone_number",nullable=false,unique=true)
	private String phoneNumber;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name = "password", length = 64, nullable = false)
	private String password;
	
	 @Column(name = "is_google_user")
	private Boolean isGoogleUser;
	 
	 private LocalDateTime createAt;
	 
	 private LocalDateTime lastModifiedAt;
	 
	 private Boolean isEmailVarified;
	 
	 private Boolean isPhoneNumberVarified;
	 

		@OneToMany(mappedBy="user")
		private List<Review> reviews;
		
		@OneToMany(mappedBy="user")
		private List<Property> properties;
		
		@OneToMany(mappedBy="user")
		private List<Transaction> transactions;
		
		@OneToMany(mappedBy="buyer")
		private List<UserRequest> buyerRequests;
		
		@OneToMany(mappedBy="owner")
		private List<UserRequest> ownerRequests;
		
		
		
//		@OneToMany(mappedBy="user")
//		private List<Notification> notifications;
		@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
		@JoinTable(name = "users_roles", 
		joinColumns = {@JoinColumn(name = "user_id")}, 
		inverseJoinColumns = {@JoinColumn(name = "role_id")})
		private Set<Role> roles = new HashSet<>();
		
	 
	
	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public LocalDateTime getLastModifiedAt() {
		return lastModifiedAt;
	}

	public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	public Boolean getIsEmailVarified() {
		return isEmailVarified;
	}

	public void setIsEmailVarified(Boolean isEmailVarified) {
		this.isEmailVarified = isEmailVarified;
	}

	public Boolean getIsPhoneNumberVarified() {
		return isPhoneNumberVarified;
	}

	public void setIsPhoneNumberVarified(Boolean isPhoneNumberVarified) {
		this.isPhoneNumberVarified = isPhoneNumberVarified;
	}

	public Boolean getIsGoogleUser() {
		return isGoogleUser;
	}
	

	public void setIsGoogleUser(Boolean isGoogleUser) {
		this.isGoogleUser = isGoogleUser;
	}

	private boolean enabled;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	Getters Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(id, email, firstName, lastName, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id)  && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", phoneNumber=" + phoneNumber + ", firstName="
				+ firstName + ", lastName=" + lastName + "]";
	}

	
	}