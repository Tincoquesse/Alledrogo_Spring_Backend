package pl.alledrogo.alledrogo_spring_lab.service;

import org.springframework.stereotype.Service;
import pl.alledrogo.alledrogo_spring_lab.exceptions.BasketNotFoundException;
import pl.alledrogo.alledrogo_spring_lab.exceptions.ProductAlreadyExistException;
import pl.alledrogo.alledrogo_spring_lab.exceptions.ProductNotFoundException;
import pl.alledrogo.alledrogo_spring_lab.model.*;
import pl.alledrogo.alledrogo_spring_lab.repository.BasketRepository;
import pl.alledrogo.alledrogo_spring_lab.repository.OrderCartRepository;
import pl.alledrogo.alledrogo_spring_lab.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AlledrogoServiceImpl implements AlledrogoService {

    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;
    private final OrderCartRepository orderCartRepository;


    public AlledrogoServiceImpl(ProductRepository productRepository, BasketRepository basketRepository, OrderCartRepository orderCartRepository) {
        this.productRepository = productRepository;
        this.basketRepository = basketRepository;
        this.orderCartRepository = orderCartRepository;
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        if (productRepository.findByProductName(productDTO.getProductName()).isPresent()){
            throw new ProductAlreadyExistException("Product Already Exist");
        } else {
            Product save = productRepository.save(ProductMapper.fromDTO(productDTO));
            return ProductMapper.fromEntity(save);
        }
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::fromEntity)
                .collect(Collectors.toList());
    }

    public List<Basket> getAllBaskets() {
        return basketRepository.findAll();
    }

    public void deleteProduct(String name) {
        productRepository.findByProductName(name).orElseThrow(() ->
                new ProductNotFoundException("Product not found"));
        productRepository.deleteByProductName(name);
    }

    public void deleteBasket(String name) {
        basketRepository.deleteByBasketName(name);
    }

    public void deleteProductFromBasket(String basket, String productName) {
        Basket basketEntity = basketRepository.findByBasketName(basket).orElseThrow(()
                -> new BasketNotFoundException("Basket: " + basket + ", was not found"));
        Product byProductName = productRepository.findByProductName(productName).orElseThrow(()
                -> new ProductNotFoundException("Product: " + productName + " is not present in database."));
        if (!basketEntity.getProducts().contains(byProductName)) {
            throw new ProductNotFoundException("Product: " + productName + " is not present.");
        }
        basketEntity.removeProductFromBasket(byProductName);
        basketRepository.save(basketEntity);
    }

    public void clearProductsList() {
        productRepository.deleteAll();
    }

    public void addBasket(Basket basket) {
        basketRepository.save(basket);
    }

    public void addProductToBasket(String basketName, String productName) {
        Basket basket = basketRepository.findByBasketName(basketName).orElseThrow(()
                -> new BasketNotFoundException("Basket" + basketName + "was not found"));
        Product product = productRepository.findByProductName(productName).orElseThrow(
                () -> new ProductNotFoundException("Product: " + productName + ", was not found"));
        basket.getProducts().add(product);
        basketRepository.save(basket);
    }

    public List<Product> getALlProductsFromBasket(String basketName) {
        return basketRepository.findByBasketName(basketName)
                .orElseThrow(() -> new BasketNotFoundException("Basket: " + basketName + ", was not found."))
                .getProducts();
    }

    public OrderCartDTO addOrder(OrderCartDTO orderDTO) {
        System.out.println("add order method");
        OrderCart save = orderCartRepository.save(OrderCartMapper.fromDTO(orderDTO));
        return OrderCartMapper.fromEntity(save);
    }

}
