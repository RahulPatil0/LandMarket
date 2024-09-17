package com.mca.landmarketproject.service;

import com.mca.landmarketproject.model.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    List<Notification> findAll(); // Get all notifications
    Optional<Notification> findById(Integer id); // Get a notification by ID
    Notification save(Notification notification); // Create or update a notification
    void deleteById(Integer id); // Delete a notification by ID
}
