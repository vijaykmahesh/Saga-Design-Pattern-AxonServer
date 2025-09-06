package com.boot.orderservice.service;

import java.util.concurrent.CompletableFuture;

import com.boot.orderservice.dto.OrderDTO;

public interface OrderService {
	
	CompletableFuture<String> createOrder(OrderDTO orderDTO);

}
