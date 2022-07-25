package pl.alledrogo.alledrogo_spring_lab.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.alledrogo.alledrogo_spring_lab.model.Basket;
import pl.alledrogo.alledrogo_spring_lab.model.Product;
import pl.alledrogo.alledrogo_spring_lab.model.ProductCategory;
import pl.alledrogo.alledrogo_spring_lab.service.AlledrogoServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class AlledrogoController {

    private final AlledrogoServiceImpl alledrogoService;


    public AlledrogoController(AlledrogoServiceImpl alledrogoService) {
        this.alledrogoService = alledrogoService;
    }

    @PostMapping("/product/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok().body(alledrogoService.addProduct(product));

    }

    @GetMapping("/product/getAll")
    @ResponseBody
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(alledrogoService.getAllProducts());
    }

    @GetMapping("/basket/getAll")
    @ResponseBody
    public List<Basket> getAllBaskets() {
        return alledrogoService.getAllBaskets();
    }

    @PostMapping("/product/remove/{name}")
    void removeProduct(@PathVariable String name) {
        alledrogoService.deleteProduct(name);
    }

    @DeleteMapping("/product/removeFromBasket/{basket}/{product}")
    void removeProduct(@PathVariable String basket, @PathVariable String product) {
        alledrogoService.deleteProductFromBasket(basket, product);
    }

    @PostMapping("/basket/remove/{name}")
    void removeBasket(@PathVariable String name) {
        alledrogoService.deleteBasket(name);
    }

    @PostMapping("/product/addToBasket/{basketName}/{productName}")
    public ResponseEntity<Product> addProductToBasket(@PathVariable String basketName, @PathVariable String productName) {
        return ResponseEntity.ok().body(alledrogoService.addProductToBasket(basketName, productName));
    }

    @GetMapping("/product/getAllFromBasket/{basketName}")
    @ResponseBody
    public ResponseEntity<List<Product>> getAllProductsFromBasket(@PathVariable String basketName) {
        return ResponseEntity.ok().body(alledrogoService.getALlProductsFromBasket(basketName));
    }

}
