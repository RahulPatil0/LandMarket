package com.mca.landmarketproject.dto;

public class NotificationDto {
	private Integer notificationId;
	private String isRead;
	private String propertyStatus;
	
	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getNotificationId() {
		return notificationId;

	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
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

}
