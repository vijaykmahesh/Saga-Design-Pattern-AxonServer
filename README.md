# Saga Orchestration with Axon Server (CQRS + Event Sourcing)

This project demonstrates a CQRS + Event Sourcing system using Spring Boot, Axon Framework, and Axon Server.
It shows how an Order Service, Invoice Service, and Delivery Service communicate through commands, events, and sagas to complete an order lifecycle.

The workflow:

Customer places an order (CreateOrderCommand)

Order emits OrderCreatedEvent → Saga starts

Saga sends CreateInvoiceCommand → Invoice created

Saga sends StartDeliveryCommand → Delivery starts

Saga sends CompleteOrderCommand → Order completed

All events are stored and published via Axon Server.

⚙️ Tech Stack
