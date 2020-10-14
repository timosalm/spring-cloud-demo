package com.example.orderservice.order;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderApplicationService {

    private static final String ORDER_QUEUE_NAME = "order-queue";

    @Bean
    Queue orderQueue() {
        return new Queue(ORDER_QUEUE_NAME, false);
    }

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final RabbitTemplate rabbitTemplate;

    OrderApplicationService(OrderRepository orderRepository, ProductService productService,
                            RabbitTemplate rabbitTemplate) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.rabbitTemplate = rabbitTemplate;
    }

    List<Order> fetchOrders() {
        return orderRepository.findAll();
    }

    Order createOrder(CreateOrderData createOrderData) {
        final Order order = Order.create(createOrderData.getProductId());

        final List<Product> products = productService.fetchProducts();
        assert order.isValid(products);
        orderRepository.save(order);

        rabbitTemplate.convertAndSend(ORDER_QUEUE_NAME, "Hello, world!");

        return order;
    }

    @RabbitListener(queues = ORDER_QUEUE_NAME)
    public void listen(String in) {
        System.out.println("Message read from myQueue : " + in);
    }

}
