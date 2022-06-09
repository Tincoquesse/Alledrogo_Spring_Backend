package pl.alledrogo.alledrogo_spring_lab.controllerJPA;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import pl.alledrogo.alledrogo_spring_lab.modelJPA.ProductAlt;
import pl.alledrogo.alledrogo_spring_lab.serviceJPA.AlledrogoJPAService;

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
}
