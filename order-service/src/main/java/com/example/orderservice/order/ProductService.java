package com.example.orderservice.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private static final String PRODUCTS_API_URL = "http://sc-product-service/api/v1/products";

    private final RestTemplate restTemplate;
    private final CircuitBreakerFactory circuitBreakerFactory;

    ProductService(RestTemplate restTemplate, CircuitBreakerFactory circuitBreakerFactory) {
        this.restTemplate = restTemplate;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    @Cacheable("Products")
    public List<Product> fetchProducts() {
        return circuitBreakerFactory.create("products").run(() ->
                        Arrays.asList(restTemplate.getForObject(PRODUCTS_API_URL, Product[].class)),
                throwable -> {
                    log.error("Call to product service failed, using empty product list as fallback", throwable);
                    return Collections.emptyList();
                });
    }
}