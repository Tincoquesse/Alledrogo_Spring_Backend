package pl.alledrogo.alledrogo_spring_lab.controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.alledrogo.alledrogo_spring_lab.service.AlledrogoService;

@RestController
@RequestMapping("alledrogo")
public class AlledrogoController {

    AlledrogoService alledrogoService;


    public AlledrogoController(AlledrogoService alledrogoService) {
        this.alledrogoService = alledrogoService;
    }
}
