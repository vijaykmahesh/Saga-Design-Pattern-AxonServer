package com.boot.core.events;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderStatusUpdatedEvent {
	
	private String orderId;
	private String orderStatus;

}
