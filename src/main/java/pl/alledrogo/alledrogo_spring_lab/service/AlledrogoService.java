package pl.alledrogo.alledrogo_spring_lab.service;

import pl.alledrogo.alledrogo_spring_lab.model.*;

import java.util.List;

public interface AlledrogoService {

    ProductDTO addProduct(ProductDTO productAlt);

    List<ProductDTO> getAllProducts();

    List<Basket> getAllBaskets();

    void deleteProduct(String name);

    void deleteBasket(String name);

    void deleteProductFromBasket(String basket, String productName);

    void clearProductsList();

    void addBasket(Basket basket);

    void addProductToBasket(String basketName, String productName);

    List<Product> getALlProductsFromBasket(String basketName);

    OrderCartDTO addOrder(OrderCartDTO orderDTO);
}
