package pl.alledrogo.alledrogo_spring_lab.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {

    private final Map<String, Product> productMap = new HashMap<>();

    public Basket() {
    }

    public void addProduct(Product product) {
        productMap.put(product.getName(), product);
    }

    public void removeProduct(String name) {
        productMap.remove(name);
    }
    public List<Product> getAllProducts() {
        return productMap.values()
                .stream()
                .toList();
    }
    public void clear() {
        productMap.clear();
    }
}
