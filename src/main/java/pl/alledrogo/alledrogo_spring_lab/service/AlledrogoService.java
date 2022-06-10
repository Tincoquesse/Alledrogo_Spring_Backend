package pl.alledrogo.alledrogo_spring_lab.service;

import org.springframework.stereotype.Service;
import pl.alledrogo.alledrogo_spring_lab.model.Basket;
import pl.alledrogo.alledrogo_spring_lab.model.Product;
import pl.alledrogo.alledrogo_spring_lab.repository.BasketRepository;
import pl.alledrogo.alledrogo_spring_lab.repository.ProductRepository;

import java.util.List;

@Service
public class AlledrogoService {

    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;


    public AlledrogoService(ProductRepository productRepository, BasketRepository basketRepository) {
        this.productRepository = productRepository;
        this.basketRepository = basketRepository;
    }

    public void addProduct(Product productAlt) {
        productRepository.save(productAlt);
    }

    public List<Product> getAll() {
        return (List<Product>) productRepository.findAll();
    }

    public Product findProductByName(String name) {
        return productRepository.findByProductName(name);
    }

    public void deleteProduct(Product productAlt) {
        productRepository.delete(productAlt);
    }

    public void clearProductsList() {
        productRepository.deleteAll();
    }

    public void addBasket(Basket basket) {
        basketRepository.save(basket);
    }

    public void addProductToBasket(String basketName, String productName) {
     basketRepository.findByBasketName(basketName).orElse(new Basket("default"))
             .addProductToBasket(productRepository.findByProductName(productName));

    }

    public List<Product> getALlProductsFromBasket(String basketName) {
        return basketRepository.findByBasketName(basketName)
                .orElseThrow(() -> new RuntimeException("No basket: " + basketName)).getProducts();
    }
}
