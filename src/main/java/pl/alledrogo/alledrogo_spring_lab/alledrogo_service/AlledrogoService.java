package pl.alledrogo.alledrogo_spring_lab.alledrogo_service;

import org.springframework.stereotype.Service;
import pl.alledrogo.alledrogo_spring_lab.exceptions.BasketNotFoundException;
import pl.alledrogo.alledrogo_spring_lab.exceptions.ProductNotFoundException;
import pl.alledrogo.alledrogo_spring_lab.alledrogo_model.Basket;
import pl.alledrogo.alledrogo_spring_lab.alledrogo_model.Product;
import pl.alledrogo.alledrogo_spring_lab.alledrogo_repository.BasketRepository;
import pl.alledrogo.alledrogo_spring_lab.alledrogo_repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Basket> getAllBaskets() {
        return basketRepository.findAll();
    }

    public void deleteProduct(String name) {
        productRepository.deleteByProductName(name);
    }

    public void deleteBasket(String name) {
        basketRepository.deleteByBasketName(name);
    }

    public void deleteProductFromBasket(String basket, String productName) {
        basketRepository.findByBasketName(basket).orElseThrow(()
                        -> new BasketNotFoundException("Basket: " + basket + ", was not found"))
                .removeProductFromBasket(productRepository.findByProductName(productName).orElseThrow(()
                        -> new ProductNotFoundException("Product: " + productName + ", was not found")));
    }

    public void clearProductsList() {
        productRepository.deleteAll();
    }

    public void addBasket(Basket basket) {
        basketRepository.save(basket);
    }

    public Product addProductToBasket(String basketName, String productName) {
        basketRepository.findByBasketName(basketName).orElseThrow()
                .addProductToBasket(productRepository.findByProductName(productName).orElseThrow(
                        () -> new ProductNotFoundException("Product: " + productName + ", was not found")));

        return productRepository.findByProductName(productName).orElseThrow();
    }

    public List<Product> getALlProductsFromBasket(String basketName) {
        return basketRepository.findByBasketName(basketName)
                .orElseThrow(() -> new BasketNotFoundException("Basket: " + basketName + ", was not found."))
                .getProductsList();

    }

}
