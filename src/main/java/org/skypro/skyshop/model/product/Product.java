package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.searchable.Searchable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    protected String name;
    private final UUID id;

    public Product(UUID id,String name) {
        if (name == null || name.isBlank()) { // проверка, что название не является null или пустой строкой
            throw new IllegalArgumentException("Название продукта не может быть пустым.");
        }

        this.name = name;
        this.id= id;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return this.id;
    }


    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return name; // или нужная реализация
    }

    @Override
    public String getType() {
        return "PRODUCT";
    }




    public int getPrice() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}

