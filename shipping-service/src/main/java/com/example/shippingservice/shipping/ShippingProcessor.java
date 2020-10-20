package com.example.shippingservice.shipping;

import com.example.shippingservice.ShippingServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ShippingProcessor {

    private static final Logger log = LoggerFactory.getLogger(ShippingServiceApplication.class);

    @Bean
    public Function<Order, OrderStatusUpdate> shipOrder() {
        return order -> {
            log.info("shipOrder called for order id: " + order.getId());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return OrderStatusUpdate.forDelivered(order.getId());
        };
    }
}
