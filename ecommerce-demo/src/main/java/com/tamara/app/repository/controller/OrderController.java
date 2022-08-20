package com.tamara.app.repository.controller;


import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tamara.app.model.Order;
import com.tamara.app.repository.service.OrderService;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
	@Autowired
	OrderService orderService;

	@PostMapping(path = "/orders", consumes = "application/json", produces = "application/json")
	public Order placeOrder(@Valid @NotEmpty @RequestBody Order order) {
		return orderService.createOrder(order);
	}

	@GetMapping(path = "/orders/{id}", produces = "application/json")
	public Order getOrder(@Valid @PathVariable String id) {
		System.out.println("Id is"+ id);
		return orderService.getOrderById(new Long(id));
	}
}
