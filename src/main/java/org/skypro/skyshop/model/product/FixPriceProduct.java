package org.skypro.skyshop.model.product;

import java.util.UUID;

public  class FixPriceProduct extends Product {

       private static final int FIXED_PRICE = 100; // пример фиксированной цены


    public FixPriceProduct(UUID id,String name) {
        super(id, name);
    }

    @Override
    public int getPrice() {
        return FIXED_PRICE;
    }

    @Override
    public String toString() {
        return name + ": Фиксированная цена " + FIXED_PRICE;
    }
}