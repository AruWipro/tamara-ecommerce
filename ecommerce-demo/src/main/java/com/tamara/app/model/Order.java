package com.tamara.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="Orders")

public class Order {
		
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_amount")
    @NotNull(message = "Total Amount is mandatory field")
    private Double totalAmount;
    

    @Column(name = "total_tax")
    private Double totalTax;
    
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "discount_amount")
    private Double discountAmount;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "order_id",referencedColumnName = "id",insertable = false,updatable = false)
    @Fetch(value = FetchMode.SUBSELECT)
    @NotNull
    @Valid
    private List<OrderItem> orderItems;
    
    public Order() {}

	public Order(Long id, Date createdDate, @NotBlank Double totalAmount, Double totalTax, Double discountAmount,
			List<OrderItem> orderItems) {
		super();
		this.id = id;
		this.createdDate = createdDate;
		this.totalAmount = totalAmount;
		this.totalTax = totalTax;
		this.discountAmount = discountAmount;
		this.orderItems = orderItems;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Double getTotalAmount() {
		return totalAmount;
	}



	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}



	public Double getTotalTax() {
		return totalTax;
	}



	public void setTotalTax(Double totalTax) {
		this.totalTax = totalTax;
	}



	public Date getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}



	public Double getDiscountAmount() {
		return discountAmount;
	}



	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}



	public List<OrderItem> getOrderItems() {
		return orderItems;
	}



	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	
	   
}
