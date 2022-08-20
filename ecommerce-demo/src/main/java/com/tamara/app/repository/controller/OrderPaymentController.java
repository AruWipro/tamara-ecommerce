package com.tamara.app.repository.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tamara.app.model.Payment;
import com.tamara.app.repository.service.OrderService;

@RestController
@RequestMapping("/api/v1/pay")
public class OrderPaymentController {
	OrderService orderService;
	public OrderPaymentController(OrderService orderService) {
		this.orderService = orderService;
	}
	@PostMapping("/{id}")
	public Object payOrder(@Valid @PathVariable Long id) {
		System.out.println("Id is"+ id);
		 ResponseEntity<Object> paymentRespose = orderService.payOrder(id);
		 if(paymentRespose.getStatusCodeValue() == 200) {
			 return new ResponseEntity<Object>(paymentRespose.getBody(),HttpStatus.CREATED);
		 }else {
			 return new ResponseEntity<Payment>(new Payment(id, "Service Unavilable"),HttpStatus.SERVICE_UNAVAILABLE);
		 }
	}
}
