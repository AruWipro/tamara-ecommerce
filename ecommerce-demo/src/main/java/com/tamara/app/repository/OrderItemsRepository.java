package com.tamara.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tamara.app.model.OrderItem;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {

}
