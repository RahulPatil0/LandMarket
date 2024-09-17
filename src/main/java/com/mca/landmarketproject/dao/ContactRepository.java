package com.mca.landmarketproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mca.landmarketproject.model.ContactUs;

public interface ContactRepository extends JpaRepository <ContactUs, Long>{

}