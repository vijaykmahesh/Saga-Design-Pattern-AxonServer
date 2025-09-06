package com.boot.orderservice.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import com.boot.core.commands.CreateOrderCommand;
import com.boot.orderservice.dto.OrderDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
	
	CommandGateway commandGateway;

	@Override
	public CompletableFuture<String> createOrder(OrderDTO orderDTO) {
		
		CompletableFuture<String> ret = commandGateway.send(new CreateOrderCommand(UUID.randomUUID().toString(), orderDTO.getPizzaName(), orderDTO.getPrice(),orderDTO.getOrderStatus() ,orderDTO.getPizzaType()));
		
		return ret;
	}

}
