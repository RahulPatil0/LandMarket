package com.mca.landmarketproject.dto;

import java.io.Serializable;

public class NotificationDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer notificationId;
    private Boolean isRead; // Changed to Boolean for better representation
    private String propertyStatus;
    private Integer userId;

    public NotificationDto() {
        // Default constructor
    }

    public NotificationDto(Integer notificationId, Boolean isRead, String propertyStatus, Integer userId) {
        this.notificationId = notificationId;
        this.isRead = isRead;
        this.propertyStatus = propertyStatus;
        this.userId = userId;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public String getPropertyStatus() {
        return propertyStatus;
    }

    public void setPropertyStatus(String propertyStatus) {
        this.propertyStatus = propertyStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "NotificationDto{" +
                "notificationId=" + notificationId +
                ", isRead=" + isRead +
                ", propertyStatus='" + propertyStatus + '\'' +
                ", userId=" + userId +
                '}';
    }
}
