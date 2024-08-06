package com.mca.landmarketproject.service;

import com.mca.landmarketproject.dto.NotificationDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.Notification;

import java.util.List;

public interface NotificationService {
    List<NotificationDto> getAllNotifications() throws LandMarketException;
   
    String addNewNotification(Notification notification, Integer userId) throws LandMarketException;
    
   
}
