package pl.alledrogo.alledrogo_spring_lab.controller;



import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/add")
    void addProduct(@RequestBody Product product) {
        alledrogoService.addProduct(product);
    }
    @GetMapping("/get")
    @ResponseBody
    List<Product> getProducts() {
       return alledrogoService.getAllProducts();
    }


}
