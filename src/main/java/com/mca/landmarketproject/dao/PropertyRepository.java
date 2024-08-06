package com.mca.landmarketproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mca.landmarketproject.model.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

}
