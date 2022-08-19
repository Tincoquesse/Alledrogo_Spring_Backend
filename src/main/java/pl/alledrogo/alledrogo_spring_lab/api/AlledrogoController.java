package pl.alledrogo.alledrogo_spring_lab.api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.alledrogo.alledrogo_spring_lab.model.*;
import pl.alledrogo.alledrogo_spring_lab.service.AlledrogoService;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class AlledrogoController {

    private final AlledrogoService alledrogoService;


    public AlledrogoController(AlledrogoService alledrogoService) {
        this.alledrogoService = alledrogoService;
    }

    @PostMapping("/product/add")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        return new  ResponseEntity<>(alledrogoService.addProduct(productDTO), HttpStatus.CREATED);
            }

    @GetMapping("/product/getAll")
    @ResponseBody
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok().body(alledrogoService.getAllProducts());
    }

    @GetMapping("/basket/getAll")
    @ResponseBody
    public ResponseEntity<List<Basket>> getAllBaskets() {
        return ResponseEntity.ok().body(alledrogoService.getAllBaskets());
    }

    @DeleteMapping("/product/remove/{name}")
    @ResponseBody
    public ResponseEntity<Void> removeProduct(@PathVariable String name) {
        alledrogoService.deleteProduct(name);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/product/removeFromBasket/{basketName}/{productName}")
    @ResponseBody
    public ResponseEntity<Void> removeProductFromBasket(@PathVariable String basketName, @PathVariable String productName) {
        alledrogoService.deleteProductFromBasket(basketName, productName);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/basket/remove/{name}")
    public ResponseEntity<Void> removeBasket(@PathVariable String name) {
        alledrogoService.deleteBasket(name);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/product/addToBasket/{basketName}/{productName}")
    public ResponseEntity<Void> addProductToBasket(@PathVariable String basketName, @PathVariable String productName) {
        alledrogoService.addProductToBasket(basketName, productName);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/product/getAllFromBasket/{basketName}")
    public ResponseEntity<List<Product>> getAllProductsFromBasket(@PathVariable String basketName) {
        return ResponseEntity.ok().body(alledrogoService.getALlProductsFromBasket(basketName));
    }

    @PostMapping("/order/save")
    public ResponseEntity<OrderCartDTO> addOrder(@RequestBody OrderCartDTO orderDTO) {
        return new  ResponseEntity<>(alledrogoService.addOrder(orderDTO), HttpStatus.CREATED);
    }

}
