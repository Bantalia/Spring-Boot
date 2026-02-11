package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private int price;

    public SimpleProduct(UUID id, String name, int price) {
        super(id, name); // вызывает конструктор родительского класса
        if (price <= 0) { // Цена должна быть строго больше нуля
            throw new IllegalArgumentException("Цена продукта должна быть положительной величиной.");
        }

        this.price = price;
    }


    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + ": " + price;
    }
}


