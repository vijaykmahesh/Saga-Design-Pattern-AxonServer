package com.boot.orderservice.saga;

import java.math.BigDecimal;
import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import com.boot.core.commands.CreateInvoiceCommand;
import com.boot.core.commands.StartDeliveryCommand;
import com.boot.core.commands.UpdateOrderStatusCommand;
import com.boot.core.events.InvoiceCreatedEvent;
import com.boot.core.events.OrderCreatedEvent;
import com.boot.core.events.OrderDeliveryEvent;
import com.boot.core.events.OrderStatusUpdatedEvent;

import lombok.extern.slf4j.Slf4j;

 @Saga
 @Slf4j
public class OrderSaga {
	
	 @Autowired	
	 private CommandGateway commandGateway;
	 
	 @StartSaga
	 @SagaEventHandler(associationProperty = "orderId")
	public void handleOrderCreated(OrderCreatedEvent orderCreatedEvent) {
		
		// step1
		// call invoice service to create invoice
		 
		 String invoiceId = UUID.randomUUID().toString();
		 
		 SagaLifecycle.associateWith("invoiceId", invoiceId);
		 
		 log.info("handleOrderCreated", orderCreatedEvent.getOrderId());
		 
		 log.info("Raising CreateInvoiceCommand");
		 
		 commandGateway.send(new CreateInvoiceCommand(invoiceId, orderCreatedEvent.getOrderId()));
		
	}
	 
	 @SagaEventHandler(associationProperty = "invoiceId")
	 public void handleInvoiceCreatedEvent(InvoiceCreatedEvent invoiceCreatedEvent) {
		 
		 log.info("handleInvoiceCreatedEvent", invoiceCreatedEvent.getInvoiceId());
		 
		 String deliveryId = UUID.randomUUID().toString();
		 
		 SagaLifecycle.associateWith("deliveryId", deliveryId);
		 
		 log.info("inside handleInvoiceCreatedEvent");
		 
		 log.info("dispatch startdeliverycommand");
		 
		 // once invoice is done , raiuse delivery
		 
		 commandGateway.send(new StartDeliveryCommand(deliveryId, invoiceCreatedEvent.getOrderId(), invoiceCreatedEvent.getInvoiceId()));
		 
	 } 
	 
	 @SagaEventHandler(associationProperty = "deliveryId")
	 public void handleOrderDeliveryEvent(OrderDeliveryEvent orderDeliveryEvent) {
		 
		 
		 log.info("inside handleOrderDeliveryEvent", orderDeliveryEvent.getDeliveryId());
		 
		 SagaLifecycle.associateWith("orderId", orderDeliveryEvent.getOrderId());
		 // update order status 
		 commandGateway.send(new UpdateOrderStatusCommand(orderDeliveryEvent.getOrderId(), "Order Delivered"));

		 
		 
	 }
	 
	 @EndSaga
	 @SagaEventHandler(associationProperty = "orderId")
	 public void handleOrderStatusUpdatedEvent(OrderStatusUpdatedEvent orderStatusUpdatedEvent) {
		 
		 log.info("inside handleOrderStatusUpdatedEvent", orderStatusUpdatedEvent.getOrderStatus());

		 log.info("Saga Completed");
		 
	 }
	
	

}
