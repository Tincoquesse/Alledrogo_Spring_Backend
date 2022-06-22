package pl.alledrogo.alledrogo_spring_lab.alledrogo_API;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.alledrogo.alledrogo_spring_lab.alledrogo_model.Basket;
import pl.alledrogo.alledrogo_spring_lab.alledrogo_model.Product;
import pl.alledrogo.alledrogo_spring_lab.alledrogo_service.AlledrogoService;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class AlledrogoController {

    private final AlledrogoService alledrogoService;


    public AlledrogoController(AlledrogoService alledrogoService) {
        this.alledrogoService = alledrogoService;
    }

    @PostMapping("/product/add/{name}/{description}/{price}")
    public String addProduct(@PathVariable String name, @PathVariable String description, @PathVariable double price) {
        Product product1 = new Product(name, description, price);
        alledrogoService.addProduct(product1);
        return "SAVED";
    }

    @PostMapping("/basket/add/{name}")
    public String addBasket(@PathVariable String name) {
        Basket basket= new Basket();
        basket.setBasketName(name);
        alledrogoService.addBasket(basket);
        return "SAVED";
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

    @PostMapping("/product/removeFromBasket/{basket}/{product}")
    void removeProduct(@PathVariable String basket, @PathVariable String product) {
        alledrogoService.deleteProductFromBasket(basket, product);
    }

    @PostMapping("/basket/remove/{name}")
    void removeBasket(@PathVariable String name) {
        alledrogoService.deleteBasket(name);
    }

    @PostMapping("/product/addToBasket/{basketName}/{product}")
    public ResponseEntity<Product> addProductToBasket(@PathVariable String basketName, @PathVariable String product) {
       return ResponseEntity.ok().body(alledrogoService.addProductToBasket(basketName, product));
    }

    @GetMapping("/product/getAllFromBasket/{basketName}")
    @ResponseBody
    public ResponseEntity<List<Product>> getAllProductsFromBasket(@PathVariable String basketName) {
        return ResponseEntity.ok().body(alledrogoService.getALlProductsFromBasket(basketName));
    }

}
