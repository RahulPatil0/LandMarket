package com.mca.landmarketproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mca.landmarketproject.dto.NotificationDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.Notification;
import com.mca.landmarketproject.response.LandMarketRespones;
import com.mca.landmarketproject.service.NotificationService;

@RestController
@RequestMapping(path = "/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<LandMarketRespones> getAllNotifications() {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "";
        try {
            List<NotificationDto> listOfDto = notificationService.getAllNotifications();
            status = HttpStatus.OK;
            return ResponseEntity.ok(new LandMarketRespones(listOfDto, status));
        } catch (LandMarketException exception) {
            message = "Failed to retrieve notifications data: " + exception.getLocalizedMessage();
        } catch (Exception exception) {
            message = "Internal Server Error: " + exception.getLocalizedMessage();
        }
        return ResponseEntity.status(status).body(new LandMarketRespones(message, status));
    }

    @PostMapping("user-id/{userId}")
    public ResponseEntity<LandMarketRespones> addNewNotification(@PathVariable(name = "userId") Integer userId,
                                                                 @RequestBody Notification notification) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "";
        try {
            message = notificationService.addNewNotification(notification, userId);
            status = HttpStatus.OK;
        } catch (LandMarketException exception) {
            message = "Failed to add notification: " + exception.getLocalizedMessage();
        } catch (Exception exception) {
            message = "Internal Server Error: " + exception.getLocalizedMessage();
        }
        return ResponseEntity.status(status).body(new LandMarketRespones(message, status));
    }

   
}
