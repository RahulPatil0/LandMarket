package com.mca.landmarketproject.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mca.landmarketproject.dao.NotificationRepository;
import com.mca.landmarketproject.dao.UserRepository;
import com.mca.landmarketproject.dto.NotificationDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.Notification;
import com.mca.landmarketproject.model.User;
import com.mca.landmarketproject.service.NotificationService;
import com.mca.landmarketproject.util.NotificationUtil;

@Service
public class NotificationServiceImplementation implements NotificationService {
    
    @Autowired
    private NotificationRepository repository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<NotificationDto> getAllNotifications() throws LandMarketException {
        try {
            return repository.findAll().stream()
                    .map(NotificationUtil::convertNotificationEntityToDto)
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            throw new LandMarketException(exception.getLocalizedMessage());
        }
    }

    @Override
    public String addNewNotification(Notification notification, Integer userId) throws LandMarketException {
        try {
        	User user = userRepository.findById(userId).get();
        	if(user ==null) {
        		throw new LandMarketException("User not found");
        	}
        	notification.setUser(user);
            repository.save(notification);
            return "Notification added successfully";
        } catch (Exception exception) {
            throw new LandMarketException("Failed to add notification: " + exception.getLocalizedMessage());
        }
    }

    
}
