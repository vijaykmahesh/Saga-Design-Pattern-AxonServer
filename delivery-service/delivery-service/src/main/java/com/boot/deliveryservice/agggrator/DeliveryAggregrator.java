package com.boot.deliveryservice.agggrator;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.boot.core.commands.StartDeliveryCommand;
import com.boot.core.events.OrderDeliveryEvent;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aggregate
public class DeliveryAggregrator {
	
	@AggregateIdentifier
	private String deliveryId;
	private String orderId;
	private String invoiceId;
	
	@CommandHandler
	public DeliveryAggregrator(StartDeliveryCommand startDeliveryCommand) {
		
		log.info("executed StartDeliveryCommand");
		
		AggregateLifecycle.apply(new OrderDeliveryEvent(startDeliveryCommand.getDeliveryId(),startDeliveryCommand.getOrderId() ,startDeliveryCommand.getInvoiceId()));
		
	}
	
	@EventSourcingHandler
	public void on(OrderDeliveryEvent orderDeliveryEvent) {
		
		this.deliveryId = orderDeliveryEvent.getDeliveryId();
		this.orderId = orderDeliveryEvent.getOrderId();
		this.invoiceId = orderDeliveryEvent.getInvoiceId();
		
	}
	

}
