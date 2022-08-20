package com.tamara.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tamara.app.model.OrderItem;

/**
 * @author Aravind
 * This is responsible to persist the Order Items to DB [Underlying H2]
 */
public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {

}
