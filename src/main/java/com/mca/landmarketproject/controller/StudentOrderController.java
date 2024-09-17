package com.mca.landmarketproject.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mca.landmarketproject.model.StudentOrder;
import com.mca.landmarketproject.service.StudentOrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class StudentOrderController {
	@Autowired
	private StudentOrderService orderService;

//	@GetMapping("/")
//	public String home() {
//		return "redirect:/index.html";
//	}

	@PostMapping(value = "/create-order", produces = "application/json")
	public ResponseEntity<StudentOrder> createOrder(@RequestBody StudentOrder studentOrder) throws Exception {
		StudentOrder createdOrder = orderService.createOrder(studentOrder);
		return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
	}

	@PostMapping("/handle-payment-callback")
	public String handlePaymentCallback(@RequestParam Map<String, String> respPayload) {
		System.out.println("Received Payment Callback Payload: " + respPayload);
		orderService.updateOrder(respPayload);
		return "Successfully paid";
	}

}
