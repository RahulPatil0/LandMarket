package com.mca.landmarketproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mca.landmarketproject.model.UserRequest;

public interface UserRequestRepository extends JpaRepository<UserRequest, Integer> {
    // JpaRepository provides findById, deleteById, and other methods.
}
