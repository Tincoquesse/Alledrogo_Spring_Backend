package pl.alledrogo.alledrogo_spring_lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.alledrogo.alledrogo_spring_lab.service.AlledrogoService;

@SpringBootApplication

public class AlledrogoSpringLabApplication{

    public static void main(String[] args) {
        SpringApplication.run(AlledrogoSpringLabApplication.class, args);
    }

    AlledrogoService alledrogoService;

    public AlledrogoSpringLabApplication(AlledrogoService alledrogoService) {
        this.alledrogoService = alledrogoService;
    }

}
