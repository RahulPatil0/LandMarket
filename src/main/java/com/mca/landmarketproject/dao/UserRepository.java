package com.mca.landmarketproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.mca.landmarketproject.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}

