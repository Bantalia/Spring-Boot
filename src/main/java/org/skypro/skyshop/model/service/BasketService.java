package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BasketService {

    private final ProductBasket basket;
    private final StorageService storageService;

    public BasketService(ProductBasket basket, StorageService storageService) {
        this.basket = basket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        Optional<Product> productOptional = storageService.getProductById(id);

        if (productOptional.isEmpty()) {
            throw new IllegalArgumentException("Product with id " + id + " not found");
        }

        basket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> productsInBasket = basket.getProducts();

        List<BasketItem> items = productsInBasket.entrySet().stream()
                .map(e -> {
                    Product product = storageService.getProductById(e.getKey()).orElseThrow();
                    return new BasketItem(product, e.getValue());
                })
                .collect(Collectors.toList());

        return new UserBasket(items);
    }
}