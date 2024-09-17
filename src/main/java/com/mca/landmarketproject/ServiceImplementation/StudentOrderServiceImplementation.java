package com.mca.landmarketproject.ServiceImplementation;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mca.landmarketproject.dao.StudentOrderRepository;
import com.mca.landmarketproject.model.StudentOrder;
import com.mca.landmarketproject.service.StudentOrderService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import jakarta.transaction.Transactional;

@Service
public class StudentOrderServiceImplementation implements StudentOrderService {

	@Autowired
	private StudentOrderRepository orderRepository;

	@Value("${razorpay.key.id}")
	private String razorPayKey;

	@Value("${razorpay.secret.key}")
	private String razorPaySecret;

// to communicate with razorPay- predefined
	private RazorpayClient client;

	public StudentOrder createOrder(StudentOrder stuOrder) throws Exception {
		JSONObject orderReq = new JSONObject();
		orderReq.put("amount", stuOrder.getAmount() * 100); // amount in paisa
		orderReq.put("currency", "INR");
		orderReq.put("receipt", stuOrder.getEmail());

		this.client = new RazorpayClient(razorPayKey, razorPaySecret);

// create order in razorpay
		Order razorpayOrder = client.orders.create(orderReq);
		System.out.println(razorpayOrder);

		stuOrder.setRazorPayOrderID(razorpayOrder.get("id"));
		stuOrder.setOrderStatus(razorpayOrder.get("status"));

		orderRepository.save(stuOrder);

		return stuOrder;

	}

	@Transactional
	public StudentOrder updateOrder(Map<String,String> responsePayLoad) {
		String razorPayOrderId=responsePayLoad.get("razorpay_order_id");
		StudentOrder order=orderRepository.findByRazorPayOrderID(razorPayOrderId);
		if (order == null) {
		        System.err.println("Order not found for Razorpay Order ID: " +
		razorPayOrderId);
		        throw new IllegalArgumentException("Order not found for Razorpay Order ID: " + razorPayOrderId);
		    }

		order.setOrderStatus("PAYMENT_COMPLETED");
//		StudentOrder updatedOrder=orderRepository.save(order);
		return order;
		}


}
