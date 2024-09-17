package com.mca.landmarketproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mca.landmarketproject.model.ContactOwner;

@Repository
public interface ContactOwnerRepository extends JpaRepository<ContactOwner, Long> {
    // Additional custom query methods can be added here if necessary
}
