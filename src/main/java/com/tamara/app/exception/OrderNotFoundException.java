package com.tamara.app.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long id) {

        super(String.format("Order with Id %d not found", id));
    }

	public OrderNotFoundException() {
		// TODO Auto-generated constructor stub
	}
}