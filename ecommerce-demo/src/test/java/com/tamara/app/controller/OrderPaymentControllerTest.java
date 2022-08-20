package com.tamara.app.controller;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tamara.app.exception.OrderNotFoundException;
import com.tamara.app.repository.controller.OrderPaymentController;
import com.tamara.app.repository.service.OrderService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderPaymentControllerTest {
	@InjectMocks
	OrderPaymentController orderPaymentController;
	
	@Mock
	OrderService orderService;
	

	@Test
	public void whenInvalidOrderIDIsPosted_returnError() {
		Long orderId = Long.valueOf(100);
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(orderService.payOrder(orderId)).thenThrow(new OrderNotFoundException(orderId));

		Exception exception = assertThrows(OrderNotFoundException.class, () -> {
			orderPaymentController.payOrder(orderId);
		});

		String expectedMessage = "Order with Id 100 not found";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}
	
	@Test
	public void whenValidOrderIDIsPosted_return201() {
		Long orderId = Long.valueOf(100);
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(orderService.payOrder(orderId)).thenReturn(new ResponseEntity<Object>(Mockito.any(), HttpStatus.OK));
		ResponseEntity<?> response = orderPaymentController.payOrder(orderId);
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED,response.getStatusCode());

	}
	
	@Test
	public void whenDownStreamHasError_return503() {
		Long orderId = Long.valueOf(100);
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(orderService.payOrder(orderId)).thenReturn(new ResponseEntity<Object>(Mockito.any(), HttpStatus.INTERNAL_SERVER_ERROR));
		ResponseEntity<?> response = orderPaymentController.payOrder(orderId);
		assertNotNull(response);
		assertEquals(HttpStatus.SERVICE_UNAVAILABLE,response.getStatusCode());

	}
}

