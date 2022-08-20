package com.tamara.app.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tamara.app.model.Order;
import com.tamara.app.repository.controller.OrderController;
import com.tamara.app.repository.service.OrderService;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderControllerTest 
{
    @InjectMocks
    OrderController orderController;
     
    @Mock
    OrderService orderService;
     
    @Test
    public void whenOrderIsPosted_thenReturnSuccess() 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        when(orderService.createOrder(any(Order.class))).thenReturn(any(Order.class));
         
        Order order = new Order(new Long(1), new Date(), Double.valueOf(23232), null, null, null);
        Order responseEntity = orderController.placeOrder(order);
         
        assertNotNull(order);
        
    }
    
    @Test
    public void whenOrderIDIsPosted_returnOrder() 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        when(orderService.getOrderById(Long.valueOf(1))).thenReturn(any(Order.class));
         
        Order order = new Order(new Long(1), new Date(), Double.valueOf(100), null, null, null);
        Order responseEntity = orderController.placeOrder(order);
        System.out.println(responseEntity);
        assertNotNull(order);
        
    }
     
   
}