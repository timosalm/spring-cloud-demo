package com.example.productservice.product;

public class Product {

    private int id;
    private String name;

    private Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Product create(int id, String name) {
        return new Product(id, name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
