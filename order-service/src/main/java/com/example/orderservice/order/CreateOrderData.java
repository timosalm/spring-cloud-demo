package com.example.orderservice.order;

import java.util.UUID;

public class CreateOrderData {

    private UUID productId;
    private String shippingAddress;

    public CreateOrderData() {
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }
}
