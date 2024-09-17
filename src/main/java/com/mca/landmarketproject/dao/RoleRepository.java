package com.mca.landmarketproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mca.landmarketproject.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String string);

}
