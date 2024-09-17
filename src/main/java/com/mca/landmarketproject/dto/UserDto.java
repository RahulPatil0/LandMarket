package com.mca.landmarketproject.dto;
public class UserDto {
	private Integer id;
    private String email;
	private String phoneNumber;
	private String firstName;
	private String lastName;
	private String password;
	private String isGoogleUser;
    private String enabled;
	
public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsGoogleUser() {
		return isGoogleUser;
	}
	public void setIsGoogleUser(String isGoogleUser) {
		this.isGoogleUser = isGoogleUser;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	//	Getters and Setters
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
	public void setIsGoogleUser(Boolean true1) {
		// TODO Auto-generated method stub
		
	}

	
}
