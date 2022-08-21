package com.tamara.app.repository.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamara.app.model.OrderItem;
import com.tamara.app.repository.OrderItemsRepository;

@Service
@Transactional
public class OrderItemsService {
	

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    public void addOrderedProducts(OrderItem orderItem) {
        orderItemsRepository.save(orderItem);
    }

}
