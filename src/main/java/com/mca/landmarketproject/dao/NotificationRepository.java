package com.mca.landmarketproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mca.landmarketproject.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}
