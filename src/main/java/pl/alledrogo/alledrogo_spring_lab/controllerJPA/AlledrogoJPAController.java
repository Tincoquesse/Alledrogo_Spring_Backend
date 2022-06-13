package pl.alledrogo.alledrogo_spring_lab.controllerJPA;


import org.springframework.web.bind.annotation.*;

import pl.alledrogo.alledrogo_spring_lab.model.Basket;
import pl.alledrogo.alledrogo_spring_lab.model.Product;
import pl.alledrogo.alledrogo_spring_lab.service.AlledrogoService;

import java.util.List;

@RestController
@RequestMapping("jpa")
public class AlledrogoJPAController {

    AlledrogoService jpaService;


    public AlledrogoJPAController(AlledrogoService jpaService) {
        this.jpaService = jpaService;
    }

    @PostMapping("/addProduct/{name}/{description}/{price}")
    public String addProduct(@PathVariable String name, @PathVariable String description, @PathVariable double price) {
        Product product1 = new Product(name, description, price);
        jpaService.addProduct(product1);
        return "SAVED";
    }

    @PostMapping("/addBasket/{name}")
    public String addBasket(@PathVariable String name) {
        Basket basket= new Basket();
        basket.setBasketName(name);
        jpaService.addBasket(basket);
        return "SAVED";
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<Product> getAllProducts() {
        return jpaService.getAllProducts();
    }

    @GetMapping("/getAllBaskets")
    @ResponseBody
    public List<Basket> getAllBaskets() {
        return jpaService.getAllBaskets();
    }

    @PostMapping("/removeProduct/{name}")
    void removeProduct(@PathVariable String name) {
        jpaService.deleteProduct(jpaService.findProductByName(name));
    }

    @PostMapping("/addProductToBasket/{basketName}/{product}")
    public String addProductToBasket(@PathVariable String basketName, @PathVariable String product) {
        jpaService.addProductToBasket(basketName, product);
        return "Product: " + basketName + ", added to basket!";
    }

    @GetMapping("/getAllFromBasket/{basketName}")
    @ResponseBody
    public List<Product> getAllProductsFromBasket(@PathVariable String basketName) {
        return jpaService.getALlProductsFromBasket(basketName);
    }

}
