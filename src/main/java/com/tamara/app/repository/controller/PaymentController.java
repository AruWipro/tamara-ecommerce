package com.tamara.app.repository.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tamara.app.model.Payment;
import com.tamara.app.repository.service.IOrderService;

/**
 * @author apple
 * This Rest Controller takes care of hosting payment related APIs
 */
@RestController
@RequestMapping("/api/v1/pay")
public class PaymentController {
	
	IOrderService orderService;
	// Constructor Injection
	public PaymentController(IOrderService orderService) {
		this.orderService = orderService;
	}
	
	/**
	 * @param id
	 * @return
	 * Consumes OrderId and makes a call to another API -> Constructs response and posts it back
	 */
	@PostMapping("/{id}")
	public ResponseEntity<?> payOrder(@Valid @PathVariable Long id) {
		 ResponseEntity<Object> paymentRespose = orderService.payOrder(id);
		 if(paymentRespose.getStatusCodeValue() == 200) {
			 return new ResponseEntity<Object>(paymentRespose.getBody(),HttpStatus.CREATED);
		 }else {
			 return new ResponseEntity<Payment>(new Payment(id, "Service Unavilable"),HttpStatus.SERVICE_UNAVAILABLE);
		 }
	}
}
