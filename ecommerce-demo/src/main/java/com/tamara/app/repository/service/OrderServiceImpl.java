package com.tamara.app.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tamara.app.exception.OrderNotFoundException;
import com.tamara.app.exception.RestTemplateResponseErrorHandler;
import com.tamara.app.model.Order;
import com.tamara.app.model.OrderItem;
import com.tamara.app.repository.OrderRepository;

@Service
public class OrderServiceImpl implements IOrderService {
	
    @Autowired
	private OrderItemsService orderItemsService;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    

	@Override
	public Order getOrderById(Long orderId) {
		return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
	}

	@Override
	public Order createOrder(Order order) {
		// first save the order with mandatory params and then persist the line items
		Order savedOrder = saveOrder(order);
		List<OrderItem> orderItemsList = order.getOrderItems();
		for(OrderItem item: orderItemsList) {
			item.setOrderId(savedOrder.getId());
			orderItemsService.addOrderedProducts(item);
		}
		return savedOrder;
		
    }

	public Order saveOrder(Order order){
	    
	    return orderRepository.save(order);
	}


	@Override
	public ResponseEntity<Object> payOrder(Long orderId) {
		String URL = "https://httpbin.org/post";
		Order order = getOrderById(orderId);
		RestTemplate restTemplate = this.restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
		ResponseEntity<Object> result = restTemplate.postForEntity(URL, order, Object.class);
		return result;

	}

}
