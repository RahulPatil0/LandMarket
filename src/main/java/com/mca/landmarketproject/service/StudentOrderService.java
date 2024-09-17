package com.mca.landmarketproject.service;

import java.util.Map;

import com.mca.landmarketproject.model.StudentOrder;



public interface StudentOrderService {
	StudentOrder createOrder(StudentOrder stuOrder) throws Exception;
	
	StudentOrder updateOrder(Map<String,String> responsePayLoad);

}
