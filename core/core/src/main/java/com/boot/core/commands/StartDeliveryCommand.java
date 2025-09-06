package com.boot.core.commands;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StartDeliveryCommand {
	
	@TargetAggregateIdentifier
	private String deliveryId;
	private String orderId;
	private String invoiceId;
	

}
