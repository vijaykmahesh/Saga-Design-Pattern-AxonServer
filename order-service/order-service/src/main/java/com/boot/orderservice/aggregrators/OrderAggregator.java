package com.boot.orderservice.aggregrators;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.boot.core.commands.CreateOrderCommand;
import com.boot.core.commands.UpdateOrderStatusCommand;
import com.boot.core.events.OrderCreatedEvent;
import com.boot.core.events.OrderStatusUpdatedEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aggregate
public class OrderAggregator {

	@AggregateIdentifier
	private String orderId;
	private String pizzaName;
	private BigDecimal price;
	private String orderStatus;
	private String pizzaType;
	
	

	public OrderAggregator() {
	}

	@CommandHandler
	public OrderAggregator(CreateOrderCommand createOrderCommand) {

		log.info("Handling CreateOrderCommand");

		AggregateLifecycle.apply(new OrderCreatedEvent(createOrderCommand.getOrderId(),
				createOrderCommand.getPizzaName(), createOrderCommand.getPrice(), createOrderCommand.getOrderStatus(),
				createOrderCommand.getPizzaType()));

		log.info("Dispatching OrderCreatedEvent");

	}

	@EventSourcingHandler
	public void on(OrderCreatedEvent orderCreatedEvent) {

		this.orderId = orderCreatedEvent.getOrderId();
		this.pizzaName = orderCreatedEvent.getPizzaName();
		this.price = orderCreatedEvent.getPrice();
		this.orderStatus = "Order In Progress";
		this.pizzaType = orderCreatedEvent.getPizzaType();

	}

	@CommandHandler
	public void statusAggregrator(UpdateOrderStatusCommand updateOrderStatusCommand) {

		System.out.println("UpdateOrderStatusCommand");

		AggregateLifecycle.apply(new OrderStatusUpdatedEvent(updateOrderStatusCommand.getOrderId(),
				updateOrderStatusCommand.getOrderStatus()));

	}

	@EventSourcingHandler
	public void on(OrderStatusUpdatedEvent orderStatusUpdatedEvent) {

		this.orderId = orderStatusUpdatedEvent.getOrderId();
		this.orderStatus = "completed";

	}

}
