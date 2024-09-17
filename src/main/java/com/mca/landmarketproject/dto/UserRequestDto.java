package com.mca.landmarketproject.dto;

public class UserRequestDto {
	private Integer id;
  

	private String name;
    private String phone;
    private String email;
    private String message;
    private String status;
    
    private Integer buyerId;
    private Integer ownerId;

    // Getters and Setters
    public Integer getId() {
  		return id;
  	}

  	public void setId(Integer id) {
  		this.id = id;
  	}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
