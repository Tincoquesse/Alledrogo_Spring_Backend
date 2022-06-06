package pl.alledrogo.alledrogo_spring_lab.model;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Basket {

    private final Map<String, Product> productMap = new ConcurrentHashMap();

    public Basket() {
    }

    public void addProduct(Product product) {
        productMap.put(product.getName(), product);
    }
    public void removeProduct(Product product) {
        productMap.remove(product.getName());
    }
    public List<Product> getAllProducts() {
        return productMap.values()
                .stream()
                .toList();
    }
}
