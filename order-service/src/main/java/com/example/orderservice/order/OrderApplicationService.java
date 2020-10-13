package com.example.orderservice.order;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderApplicationService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    OrderApplicationService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    List<Order> fetchOrders() {
        return orderRepository.findAll();
    }

    Order createOrder(CreateOrderData createOrderData) {
        final Order order = Order.create(createOrderData.getProductId());

        final List<Product> products = productService.fetchProducts();
        assert order.isValid(products);
        orderRepository.save(order);
        return order;
    }
}
