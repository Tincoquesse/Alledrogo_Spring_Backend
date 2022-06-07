package pl.alledrogo.alledrogo_spring_lab.repository;


import org.springframework.stereotype.Repository;
import pl.alledrogo.alledrogo_spring_lab.model.Basket;
import pl.alledrogo.alledrogo_spring_lab.model.Order;
import pl.alledrogo.alledrogo_spring_lab.model.Product;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class ProductsInMemoryProvider {

    private final Map<String, Product> productList = new ConcurrentHashMap<>();
    private final Basket basket = new Basket();
    private Order order;

    public void addProduct(Product product) {
        productList.put(product.getName(), product);
    }

    public Optional<Product> getProduct(String name) {
        return productList.values().stream()
                .filter(product -> product.getName().equals(name))
                .findFirst();
    }
    public void addProductToBasket(String name) {
        basket.addProduct(productList.get(name));
    }

    public void removeProductFromBasket(String name) {
        basket.removeProduct(name);
    }
    public List<Product> getAllProducts() {
        return Optional.ofNullable(productList.values().stream().collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
    public List<Product> getAllProductsFromBasket() {
        return Optional.ofNullable(basket.getAllProducts()).orElse(Collections.emptyList());
    }
    public Order makeOrder(String shipmentAddress){
        Order order = new Order();
        order.addProducts(getAllProductsFromBasket());
        order.setShipmentAddress(shipmentAddress);
        basket.clear();
        return order;
    }
}
