package com.boot.core.commands;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateOrderStatusCommand {
	
	@TargetAggregateIdentifier
	private String orderId;
	private String orderStatus;

}
