package com.mca.landmarketproject.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mca.landmarketproject.model.StudentOrder;

@Repository
public interface StudentOrderRepository extends JpaRepository<StudentOrder, Integer>{
public StudentOrder findByRazorPayOrderID(String orderId);
}
