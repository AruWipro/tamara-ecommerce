package com.tamara.app.repository.service;

import org.springframework.http.ResponseEntity;

import com.tamara.app.model.Order;

/**
 * @author apple
 *
 */
public interface OrderService {
	/**
	 * @param orderId
	 * @return
	 */
	public Order getOrderById(Long orderId);
	
	public Order createOrder(Order order);
	
	public ResponseEntity<Object> payOrder(Long orderId);
	
}
