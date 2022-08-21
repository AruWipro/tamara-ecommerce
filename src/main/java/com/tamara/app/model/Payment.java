package com.tamara.app.model;

public class Payment {
	private Long orderId;
	private String message;
	
	public Payment() {}
	public Payment(Long orderId, String message) {
		super();
		this.orderId = orderId;
		this.message = message;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
