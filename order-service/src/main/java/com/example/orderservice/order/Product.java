package com.example.orderservice.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
class Product implements Serializable {
    private int id;

    Product() {
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }
}
