package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.searchable.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;


    // Заполнение коллекций тестовыми данными
    public StorageService() {

        products = new HashMap<>();
        articles = new HashMap<>();

        initializeData();
    }

    private void initializeData() {
        Product p1 = new SimpleProduct(UUID.randomUUID(), "Яблоко", 50);
        Product p2 = new DiscountedProduct(UUID.randomUUID(), "Хлеб", 30, 20);
        Product p3 = new FixPriceProduct(UUID.randomUUID(), "Молоко");
        Article a1 = new Article(UUID.randomUUID(), "Грузия", "Голд");

        products.put(p1.getId(), p1);
        products.put(p2.getId(), p2);
        articles.put(a1.getId(), a1);
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public Collection<Article> getAllArticles() {
        return articles.values();
    }

    public Collection<Searchable> getAllSearchables() {
        return Stream.concat(getAllProducts().stream(), getAllArticles().stream())
                .collect(Collectors.toList());
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }
}
