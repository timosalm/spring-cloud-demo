package com.example.orderservice.order;

import java.util.UUID;

class OrderStatusUpdate {

    private Long id;
    private OrderStatus status;

    private OrderStatusUpdate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
