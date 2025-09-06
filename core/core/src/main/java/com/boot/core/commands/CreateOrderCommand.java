package com.boot.core.commands;

import java.math.BigDecimal;
import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateOrderCommand {
	
	@TargetAggregateIdentifier
	private String orderId;
	private String pizzaName;
	private BigDecimal price;
	private String orderStatus;
	private String pizzaType;

}
