package pl.alledrogo.alledrogo_spring_lab.service;

import org.springframework.stereotype.Service;
import pl.alledrogo.alledrogo_spring_lab.model.Order;
import pl.alledrogo.alledrogo_spring_lab.model.Product;
import pl.alledrogo.alledrogo_spring_lab.repository.ProductsInMemoryProvider;

import java.util.List;

@Service
public class AlledrogoService {

    ProductsInMemoryProvider productsInMemoryProvider;

    public AlledrogoService(ProductsInMemoryProvider products) {
        this.productsInMemoryProvider = products;
    }

    public void addProduct(Product product) {
        productsInMemoryProvider.addProduct(product);
    }

    public void addProductToBasket(String name) {
        productsInMemoryProvider.addProductToBasket(name);
    }

    public void removeProductFromBasket(String name) {
        productsInMemoryProvider.removeProductFromBasket(name);
    }

    public  List<Product> getAllProducts() {
        return productsInMemoryProvider.getAllProducts();
    }
    public List<Product> getALlProductsFromBasket() {
        return productsInMemoryProvider.getAllProductsFromBasket();
    }
    public Order makeOrder(String shipmentAddress) {
        return productsInMemoryProvider.makeOrder(shipmentAddress);
    }
}
