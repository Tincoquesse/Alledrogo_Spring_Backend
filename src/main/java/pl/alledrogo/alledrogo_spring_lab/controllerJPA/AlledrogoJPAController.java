package pl.alledrogo.alledrogo_spring_lab.controllerJPA;


import org.springframework.web.bind.annotation.*;

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
    String addProduct(@PathVariable String name, @PathVariable String description, @PathVariable double price) {
        Product product1 = new Product(name, description, price);
        jpaService.addProduct(product1);
        return "SAVED";
    }

    @GetMapping("/getAll")
    @ResponseBody
    List<Product> getAllProducts() {
        return jpaService.getAll();
    }

    @PostMapping("/removeProduct/{name}")
    void removeProduct(@PathVariable String name) {
        jpaService.deleteProduct(jpaService.findProductByName(name));
    }
}
