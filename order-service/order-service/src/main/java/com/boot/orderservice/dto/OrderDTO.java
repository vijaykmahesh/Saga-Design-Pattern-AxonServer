package com.boot.orderservice.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderDTO {
	
	private String orderId;
	private String pizzaName;
	private BigDecimal price;
	private String orderStatus;
	private String pizzaType;

}
