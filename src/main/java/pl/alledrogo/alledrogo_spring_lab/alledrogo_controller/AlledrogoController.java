package pl.alledrogo.alledrogo_spring_lab.alledrogo_controller;


import org.springframework.web.bind.annotation.*;

import pl.alledrogo.alledrogo_spring_lab.alledrogo_model.Basket;
import pl.alledrogo.alledrogo_spring_lab.alledrogo_model.Product;
import pl.alledrogo.alledrogo_spring_lab.alledrogo_service.AlledrogoService;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class AlledrogoController {

    private final AlledrogoService jpaService;


    public AlledrogoController(AlledrogoService jpaService) {
        this.jpaService = jpaService;
    }

    @PostMapping("/product/add/{name}/{description}/{price}")
    public String addProduct(@PathVariable String name, @PathVariable String description, @PathVariable double price) {
        Product product1 = new Product(name, description, price);
        jpaService.addProduct(product1);
        return "SAVED";
    }

    @PostMapping("/basket/add/{name}")
    public String addBasket(@PathVariable String name) {
        Basket basket= new Basket();
        basket.setBasketName(name);
        jpaService.addBasket(basket);
        return "SAVED";
    }

    @GetMapping("/product/getAll")
    @ResponseBody
    public List<Product> getAllProducts() {
        return jpaService.getAllProducts();
    }

    @GetMapping("/basket/getAll")
    @ResponseBody
    public List<Basket> getAllBaskets() {
        return jpaService.getAllBaskets();
    }

    @PostMapping("/product/remove/{name}")
    void removeProduct(@PathVariable String name) {
        jpaService.deleteProduct(name);
    }

    @PostMapping("/product/removeFromBasket/{basket}/{product}")
    void removeProduct(@PathVariable String basket, @PathVariable String product) {
        jpaService.deleteProductFromBasket(basket, product);
    }

    @PostMapping("/basket/remove/{name}")
    void removeBasket(@PathVariable String name) {
        jpaService.deleteBasket(name);
    }

    @PostMapping("/product/addToBasket/{basketName}/{product}")
    public String addProductToBasket(@PathVariable String basketName, @PathVariable String product) {
        jpaService.addProductToBasket(basketName, product);
        return "Product: " + product + ", added to basket: " + basketName;
    }

    @GetMapping("/product/getAllFromBasket/{basketName}")
    @ResponseBody
    public List<Product> getAllProductsFromBasket(@PathVariable String basketName) {
        return jpaService.getALlProductsFromBasket(basketName);
    }

}
