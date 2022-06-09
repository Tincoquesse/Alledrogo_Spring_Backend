package pl.alledrogo.alledrogo_spring_lab.controllerJPA;


import org.springframework.web.bind.annotation.*;

import pl.alledrogo.alledrogo_spring_lab.modelJPA.ProductAlt;
import pl.alledrogo.alledrogo_spring_lab.serviceJPA.AlledrogoJPAService;

import java.util.List;

@RestController
@RequestMapping("jpa")
public class AlledrogoJPAController {

    AlledrogoJPAService jpaService;


    public AlledrogoJPAController(AlledrogoJPAService jpaService) {
        this.jpaService = jpaService;
    }

    @PostMapping("/addProduct/{name}/{description}/{price}")
    String addProduct(@PathVariable String name, @PathVariable String description, @PathVariable double price) {
        ProductAlt product1 = new ProductAlt(name, description, price);
        jpaService.addProduct(product1);
        return "SAVED";
    }

    @GetMapping("/getAll")
    @ResponseBody
    List<ProductAlt> getAllProducts() {
        return jpaService.getAll();
    }

    @PostMapping("/removeProduct/{name}")
    void removeProduct(@PathVariable String name) {
        jpaService.deleteProduct(jpaService.findProductByName(name));
    }
}
