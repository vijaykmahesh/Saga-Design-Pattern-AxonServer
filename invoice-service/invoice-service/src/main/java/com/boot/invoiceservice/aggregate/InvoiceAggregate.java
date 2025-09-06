package com.boot.invoiceservice.aggregate;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.boot.core.commands.CreateInvoiceCommand;
import com.boot.core.events.InvoiceCreatedEvent;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Aggregate
public class InvoiceAggregate {
	
	@AggregateIdentifier
	private String invoiceId;
	private String orderId;
	
	@CommandHandler
	public InvoiceAggregate(CreateInvoiceCommand createInvoiceCommand) {
		
		log.info("Handling CreateInvoiceCommand");
		
		AggregateLifecycle.apply(new InvoiceCreatedEvent(createInvoiceCommand.getInvoiceId(), createInvoiceCommand.getOrderId()));
		
		// log.info("Handling CreateInvoiceCommand");
	}
	
	@EventSourcingHandler
	public void on(InvoiceCreatedEvent invoiceCreatedEvent) {
		
		this.invoiceId = invoiceCreatedEvent.getInvoiceId();
		this.orderId = invoiceCreatedEvent.getOrderId();
		
	}
	
	
	
	

}
