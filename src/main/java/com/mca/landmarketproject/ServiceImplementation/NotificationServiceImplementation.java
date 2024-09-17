package com.mca.landmarketproject.ServiceImplementation;

import com.mca.landmarketproject.dao.NotificationRepository;
import com.mca.landmarketproject.model.Notification;
import com.mca.landmarketproject.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImplementation implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // Fetch all notifications
    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll();
        }

    @Override
    public Optional<Notification> findById(Integer id) {
        return notificationRepository.findById(id);
    }

    // Create or update a notification
    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    // Delete a notification by ID
    @Override
    public void deleteById(Integer id) {
        notificationRepository.deleteById(id);
    }
}
