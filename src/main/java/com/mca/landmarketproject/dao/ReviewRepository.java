package com.mca.landmarketproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mca.landmarketproject.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
