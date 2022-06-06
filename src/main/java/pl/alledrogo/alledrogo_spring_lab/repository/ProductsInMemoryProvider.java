package pl.alledrogo.alledrogo_spring_lab.repository;


import org.springframework.stereotype.Repository;
import pl.alledrogo.alledrogo_spring_lab.model.Basket;
import pl.alledrogo.alledrogo_spring_lab.model.Order;
import pl.alledrogo.alledrogo_spring_lab.model.Product;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ProductsInMemoryProvider {

    private final Map<String, Product> productList = new ConcurrentHashMap<>();
    Basket basket = new Basket();
    Order order;

    public void addProduct(Product product) {
        productList.put(product.getName(), product);
    }

    Optional<Product> getProduct(String name) {
        return productList.values().stream()
                .filter(product -> product.getName().equals(name))
                .findFirst();
    }
    void addProductToBasket(String name) {
        basket.addProduct(productList.get(name));
    }
    void removeProductFromBasket(String name) {
        productList.remove(name);
    }

}
