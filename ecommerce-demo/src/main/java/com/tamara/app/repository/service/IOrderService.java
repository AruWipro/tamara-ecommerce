package com.tamara.app.repository.service;

import org.springframework.http.ResponseEntity;

import com.tamara.app.exception.OrderNotFoundException;
import com.tamara.app.model.Order;

/**
 * @author Aravind Piratla
 *
 */
public interface IOrderService {
	/**
	 * @param orderId
	 * @return Order
	 * This method returns the order associated with the Id. In case if the Order isn't there it throws an exception
	 */
	public Order getOrderById(Long orderId) throws OrderNotFoundException;
	
	
	/**
	 * @param order
	 * @return Order
	 * This method is responsible to create a new order. Returns back the order object with created ID
	 */
	public Order createOrder(Order order);
	
	
	
	/**
	 * @param orderId
	 * @return ResponseEntity<Object>
	 * It makes a call to third party interface and responds as per their response. 
	 */
	public ResponseEntity<Object> payOrder(Long orderId);
	
}
