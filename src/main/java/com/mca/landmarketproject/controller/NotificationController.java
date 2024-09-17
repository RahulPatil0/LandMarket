
package com.mca.landmarketproject.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mca.landmarketproject.model.Notification;
import com.mca.landmarketproject.model.User;
import com.mca.landmarketproject.service.NotificationService;
import com.mca.landmarketproject.service.UserService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    // Get all notifications
    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.findAll();
        return ResponseEntity.ok(notifications);
    }

    // Get notification by id
    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Integer id) {
    	String message = " ";
    	try {
			
    		Optional<Notification> notification = notificationService.findById(id);
    		if (notification.isPresent()) {
    			return ResponseEntity.ok(notification.get());
    		} else {
    			return ResponseEntity.notFound().build();
    		}
		} catch (Exception e) {
			// TODO: handle exception
			 message = e.getLocalizedMessage();
		}
    	return ResponseEntity.notFound().build();
    }

    // Create a new notification
    @PostMapping
    public ResponseEntity<Notification> createNotification(
            @RequestParam Integer userId,
            @RequestParam Integer ownerId,
            @RequestParam String propertyStatus) {

        Optional<User> user = userService.findById(userId);
        Optional<User> owner = userService.findById(ownerId);

        if (user.isPresent() && owner.isPresent()) {
            Notification notification = new Notification();
            notification.setUser(user.get());
            notification.setOwner(owner.get());
            notification.setPropertyStatus(propertyStatus);
            notification.setIsRead("false"); // Default to unread

            Notification savedNotification = notificationService.save(notification);
            return ResponseEntity.ok(savedNotification);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Update notification (mark as read)
    @PutMapping("/{id}/read")
    public ResponseEntity<Notification> markAsRead(@PathVariable Integer id) {
        Optional<Notification> notification = notificationService.findById(id);
        if (notification.isPresent()) {
            Notification updatedNotification = notification.get();
            updatedNotification.setIsRead("true");
            notificationService.save(updatedNotification);
            return ResponseEntity.ok(updatedNotification);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete notification by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Integer id) {
        Optional<Notification> notification = notificationService.findById(id);
        if (notification.isPresent()) {
            notificationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
