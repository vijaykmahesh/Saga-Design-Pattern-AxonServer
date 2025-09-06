package com.boot.core.events;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderCreatedEvent {
	
	private String orderId;
	private String pizzaName;
	private BigDecimal price;
	private String orderStatus;
	private String pizzaType;


}
