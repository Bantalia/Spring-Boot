package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private int basePrice;
    private int discountPercent; // 0..100

    public DiscountedProduct(UUID id, String name, int basePrice, int discountPercent) {
        super(id, name);

        if (discountPercent < 0 || discountPercent > 100) { // скидка должна быть от 0% до 100%
            throw new IllegalArgumentException("Процент скидки должен находиться в пределах от 0 до 100 включительно.");
        }
        this.discountPercent = discountPercent;
        this.basePrice = basePrice;
    }

    public int getDiscountPercentage() {
        return discountPercent;
    }


    @Override
    public int getPrice() {
        return basePrice * (100 - discountPercent) / 100;
    }

    @Override
    public String toString() {
        return name + ": " + getPrice() + " (" + discountPercent + "%)";
    }
}