package com.example.orderservice.order;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class ShippingService {

    private static final String ORDER_QUEUE_INPUT_NAME = "order-in-queue";
    private static final String ORDER_EXCHANGE_NAME = "order-delivered-exchange";
    private static final String ORDER_SHIPPING_EXCHANGE_NAME = "order-shipping-exchange";

    private final RabbitTemplate rabbitTemplate;
    private Consumer<OrderStatusUpdate> orderStatusUpdateConsumer;

    ShippingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Bean
    Queue orderInQueue() {
        return new Queue(ORDER_QUEUE_INPUT_NAME, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(ORDER_EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("#");
    }

    void shipOrder(Order order) {
        rabbitTemplate.convertAndSend(ORDER_SHIPPING_EXCHANGE_NAME, "#", order);
    }

    @RabbitListener(queues = ORDER_QUEUE_INPUT_NAME)
    private void updateStatus(OrderStatusUpdate statusUpdate) {
        System.out.println("updateStatus called for order id: " + statusUpdate.getId() + " with status "
                + statusUpdate.getStatus());
        if (orderStatusUpdateConsumer != null) {
            orderStatusUpdateConsumer.accept(statusUpdate);
        }
    }

    void setOrderStatusUpdateConsumer(Consumer<OrderStatusUpdate> orderStatusUpdateConsumer) {
        this.orderStatusUpdateConsumer = orderStatusUpdateConsumer;
    }
}
