package com.boot.orderservice.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.orderservice.dto.OrderDTO;
import com.boot.orderservice.service.OrderService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class OrderController {
	
	private OrderService orderService;
	
	@PostMapping("/order")
	public CompletableFuture<String> createOrder(@RequestBody OrderDTO orderDTO){
		
		CompletableFuture<String> order = orderService.createOrder(orderDTO);
		
		return order;
	}

}
