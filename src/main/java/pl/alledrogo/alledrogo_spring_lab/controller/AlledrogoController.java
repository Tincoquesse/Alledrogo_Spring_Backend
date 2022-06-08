package pl.alledrogo.alledrogo_spring_lab.controller;

import org.springframework.web.bind.annotation.*;
import pl.alledrogo.alledrogo_spring_lab.model.Order;
import pl.alledrogo.alledrogo_spring_lab.model.Product;
import pl.alledrogo.alledrogo_spring_lab.service.AlledrogoService;


import java.util.List;

@RestController

@RequestMapping("alledrogo")
public class AlledrogoController {

    AlledrogoService alledrogoService;

    public AlledrogoController(AlledrogoService alledrogoService) {
        this.alledrogoService = alledrogoService;
    }

    @PostMapping("/addProduct/{name}/{description}/{price}")
    String addProduct(@PathVariable String name, @PathVariable String description,@PathVariable double price) {
        Product product1 = new Product(name, description, price);
        alledrogoService.addProduct(product1);
        return "SAVED";
    }

    @GetMapping("/getAll")
    @ResponseBody
    List<Product> getAllProducts() {
       return alledrogoService.getAllProducts();
    }

    @GetMapping("/getAllFromBasket")
    @ResponseBody
    List<Product> getAllProductsFromBasket() {
        return alledrogoService.getALlProductsFromBasket();
    }

    @GetMapping("/makeOrder/{address}")
    @ResponseBody
    Order makeOrder(@PathVariable String address) {
        return alledrogoService.makeOrder(address);
    }

    @PostMapping("/addProductToBasket/{name}")
    String addProductToBasket(@PathVariable String name) {
        alledrogoService.addProductToBasket(name);
        return "Product: " + name + " added to basket!";
    }

    @PostMapping("/removeProductFromBasket/{name}")
    void removeProductFromBasket(@PathVariable String name) {
        alledrogoService.removeProductFromBasket(name);
    }
}
