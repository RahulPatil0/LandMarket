//package com.mca.landmarketproject.dto;
//
//public class PropertyDto {
//
//	private Integer id;
//	private String propertyAddress;
//	private String propertyCity;
//	private String propertyState;
//	private String propertyZipCode;
//	private String propertySize;
//	private String propertyStatus;
//	private String propertyPrice;
//
//	private Integer userId;
//
//	public Integer getUserId() {
//		return userId;
//	}
//
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getPropertyAddress() {
//		return propertyAddress;
//	}
//
//	public void setPropertyAddress(String propertyAddress) {
//		this.propertyAddress = propertyAddress;
//	}
//
//	public String getPropertyCity() {
//		return propertyCity;
//	}
//
//	public void setPropertyCity(String propertyCity) {
//		this.propertyCity = propertyCity;
//	}
//
//	public String getPropertyState() {
//		return propertyState;
//	}
//
//	public void setPropertyState(String propertyState) {
//		this.propertyState = propertyState;
//	}
//
//	public String getPropertyZipCode() {
//		return propertyZipCode;
//	}
//
//	public void setPropertyZipCode(String propertyZipCode) {
//		this.propertyZipCode = propertyZipCode;
//	}
//
//	public String getPropertySize() {
//		return propertySize;
//	}
//
//	public void setPropertySize(String propertySize) {
//		this.propertySize = propertySize;
//	}
//
//	public String getPropertyStatus() {
//		return propertyStatus;
//	}
//
//	public void setPropertyStatus(String propertyStatus) {
//		this.propertyStatus = propertyStatus;
//	}
//
//	public String getPropertyPrice() {
//		return propertyPrice;
//	}
//
//	public void setPropertyPrice(String propertyPrice) {
//		this.propertyPrice = propertyPrice;
//	}
//
//}
package com.mca.landmarketproject.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PropertyDto {

    private Integer id;
    private String propertyAddress;
    private String propertyCity;
    private String propertyState;
    private String propertyZipCode;
    private String propertySize;
    private String propertyStatus;
    private String propertyPrice;

    private Integer userId;
    
    private LocalDateTime dateAdded;  // Add this line

    private List<PropertyImageDto> propertyImages;
    
    public List<PropertyImageDto> getPropertyImages() {
		return propertyImages;
	}

	public void setPropertyImages(List<PropertyImageDto> propertyImages) {
		this.propertyImages = propertyImages;
	}

	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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

    public void setPropertyCity(String propertyCity) {
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

    public String getPropertyPrice() {
        return propertyPrice;
    }

    public void setPropertyPrice(String propertyPrice) {
        this.propertyPrice = propertyPrice;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;  // Add this method
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;  // Add this method
    }
}
