package org.skypro.skyshop.model.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.service.BasketService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;



@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    @Mock
    private ProductBasket basket;

    @Mock
    private StorageService storageService;

    private BasketService basketService;

    @BeforeEach
    void setUp() {
        basketService = new BasketService(basket, storageService);
    }

    @Test
    void addProduct_whenProductExists_invokesAddProduct() {
        UUID productId = UUID.randomUUID();
        Product product = Mockito.mock(Product.class);

        Mockito.when(storageService.getProductById(productId)).thenReturn(Optional.of(product));

        basketService.addProduct(productId);

        Mockito.verify(basket, Mockito.times(1)).addProduct(productId);
    }

    @Test
    void addProduct_whenProductNotFound_throwsException() {
        UUID productId = UUID.randomUUID();

        Mockito.when(storageService.getProductById(productId)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> basketService.addProduct(productId));

        Mockito.verify(basket, Mockito.never()).addProduct(Mockito.any());
    }

    @Test
    void getUserBasket_whenBasketHasProducts_returnsCorrectBasket() {
        UUID productId = UUID.randomUUID();
        int quantity = 2;
        Map<UUID, Integer> productMap = Map.of(productId, quantity);

        Product product = Mockito.mock(Product.class);
        BasketItem basketItem = new BasketItem(product, quantity);

        Mockito.when(basket.getProducts()).thenReturn(productMap);
        Mockito.when(storageService.getProductById(productId)).thenReturn(Optional.of(product));

        UserBasket userBasket = basketService.getUserBasket();

        // Проверка что в корзине ровно 1 товар с нужным продуктом и количеством
        Assertions.assertNotNull(userBasket);
        Assertions.assertEquals(1, userBasket.getItems().size());

        BasketItem item = userBasket.getItems().get(0);
        Assertions.assertEquals(product, item.getProduct());
        Assertions.assertEquals(quantity, item.getQuantity());
    }

    @Test
    void getUserBasket_whenBasketEmpty_returnsEmptyItemsList() {
        Mockito.when(basket.getProducts()).thenReturn(Collections.emptyMap());

        UserBasket userBasket = basketService.getUserBasket();

        Assertions.assertNotNull(userBasket);
        Assertions.assertTrue(userBasket.getItems().isEmpty());
    }
}
