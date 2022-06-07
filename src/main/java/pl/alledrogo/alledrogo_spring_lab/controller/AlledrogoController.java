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

    @PostMapping("/addProduct")
    void addProduct(@RequestBody Product product) {
        alledrogoService.addProduct(product);
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
    void addProductToBasket(@PathVariable String name) {
        alledrogoService.addProductToBasket(name);
    }

    @PostMapping("/removeProductFromBasket/{name}")
    void removeProductFromBasket(@PathVariable String name) {
        alledrogoService.removeProductFromBasket(name);
    }
}
