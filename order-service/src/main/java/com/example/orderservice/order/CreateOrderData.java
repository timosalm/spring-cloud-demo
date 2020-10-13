package com.example.orderservice.order;

public class CreateOrderData {

    private int productId;
    private String shippingAddress;

    public CreateOrderData() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
