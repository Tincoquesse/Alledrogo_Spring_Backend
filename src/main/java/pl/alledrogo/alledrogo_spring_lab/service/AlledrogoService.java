package pl.alledrogo.alledrogo_spring_lab.service;

import pl.alledrogo.alledrogo_spring_lab.model.Basket;
import pl.alledrogo.alledrogo_spring_lab.model.Product;

import java.util.List;

public interface AlledrogoService {

    void addProduct(Product productAlt);

    List<Product> getAllProducts();

    List<Basket> getAllBaskets();

    void deleteProduct(String name);

    void deleteBasket(String name);

    void deleteProductFromBasket(String basket, String productName);

    void clearProductsList();

    void addBasket(Basket basket);

    Product addProductToBasket(String basketName, String productName);

    List<Product> getALlProductsFromBasket(String basketName);

}