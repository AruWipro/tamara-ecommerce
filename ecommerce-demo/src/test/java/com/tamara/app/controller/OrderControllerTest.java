package com.tamara.app.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

import com.tamara.app.exception.OrderNotFoundException;
import com.tamara.app.model.Order;
import com.tamara.app.repository.controller.OrderController;
import com.tamara.app.repository.service.OrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderControllerTest {
	@InjectMocks
	OrderController orderController;

	@Mock
	OrderService orderService;

	@Test
	public void whenOrderIsPosted_thenReturnSuccess() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Order order = new Order(new Long(1), new Date(), Double.valueOf(23232), null, null, null);
		when(orderService.createOrder(any(Order.class))).thenReturn(order);

		Order responseEntity = orderController.placeOrder(order);

		assertNotNull(responseEntity);

	}

	@Test
	public void whenOrderIDIsPosted_returnOrder() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Order order = new Order(new Long(1), new Date(), null, null, null, null);

		when(orderService.getOrderById(anyLong())).thenReturn(order);

		Order responseEntity = orderController.getOrder(anyLong());
		System.out.println(responseEntity);
		assertNotNull(responseEntity);
		assertEquals(order.getId(), responseEntity.getId());

	}

	@Test
	public void whenInvalidOrderIDIsPosted_returnError() {
		Long orderId = Long.valueOf(100);
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(orderService.getOrderById(orderId)).thenThrow(new OrderNotFoundException(orderId));

		Exception exception = assertThrows(OrderNotFoundException.class, () -> {
			orderController.getOrder(orderId);
		});

		String expectedMessage = "Order with Id 100 not found";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

}