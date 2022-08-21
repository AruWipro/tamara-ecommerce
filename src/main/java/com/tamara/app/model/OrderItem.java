package com.tamara.app.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "orderitems")

public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemId;
    
    @NotNull(message = "SkuID is mandatory field")
    @Column(name = "skuid")
    private @NotNull Long skuId;

    @NotNull(message = "Quantity is mandatory field")
    @Column(name = "quantity")
    private @NotNull int quantity;

    @NotNull(message = "Item Price is mandatory field")
    @Column(name = "price")
    private @NotNull double price;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "created_date")
    private Date createdDate;
    public OrderItem() {
    	
    }
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id", updatable = false, insertable = false)
    @JsonBackReference
    private Order order;
    
    public OrderItem(Long orderId, @NotNull Long skuId, @NotNull int quantity, @NotNull double price) {
        this.skuId = skuId;
        this.quantity = quantity;
        this.price = price;
        this.orderId=orderId;
        this.createdDate = new Date();
    }

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
    
    
    
}
