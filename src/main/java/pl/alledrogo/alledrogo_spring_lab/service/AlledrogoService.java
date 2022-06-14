package pl.alledrogo.alledrogo_spring_lab.service;

import org.springframework.stereotype.Service;
import pl.alledrogo.alledrogo_spring_lab.model.Basket;
import pl.alledrogo.alledrogo_spring_lab.model.Product;
import pl.alledrogo.alledrogo_spring_lab.repository.BasketRepository;
import pl.alledrogo.alledrogo_spring_lab.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;

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
        return (List<Product>) productRepository.findAll();
    }

    public List<Basket> getAllBaskets() {
        return basketRepository.findAll();
    }

    public Product findProductByName(String name) {
        return productRepository.findByProductName(name).orElseThrow(NullPointerException::new);
    }

    public void deleteProduct(String name) {
        productRepository.deleteByProductName(name);
    }
    public void deleteBasket(String name) {
        basketRepository.deleteByBasketName(name);
    }

    public void clearProductsList() {
        productRepository.deleteAll();
    }

    public void addBasket(Basket basket) {
        basketRepository.save(basket);
    }

    public void addProductToBasket(String basketName, String productName) {
     basketRepository.findByBasketName(basketName).orElseThrow()
             .addProductToBasket(productRepository.findByProductName(productName).get());

    }

    public List<Product> getALlProductsFromBasket(String basketName) {
        return basketRepository.findByBasketName(basketName)
                .get().getProductsList();

    }
}
