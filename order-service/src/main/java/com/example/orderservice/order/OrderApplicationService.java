package com.example.orderservice.order;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderApplicationService {

    private final OrderRepository orderRepository;

    OrderApplicationService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    List<Order> fetchOrders() {
        return orderRepository.findAll();
    }

    Order createOrder(CreateOrderData createOrderData) {
        final Order order = Order.create();
        orderRepository.save(order);
        return order;
    }
}
