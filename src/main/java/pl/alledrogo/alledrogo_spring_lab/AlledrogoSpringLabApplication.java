package pl.alledrogo.alledrogo_spring_lab;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.alledrogo.alledrogo_spring_lab.model.Product;
import pl.alledrogo.alledrogo_spring_lab.service.AlledrogoService;


@SpringBootApplication

public class AlledrogoSpringLabApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AlledrogoSpringLabApplication.class, args);
    }

    AlledrogoService alledrogoService;

    public AlledrogoSpringLabApplication(AlledrogoService alledrogoService) {
        this.alledrogoService = alledrogoService;
    }
    @Bean

    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Override

    public void run(String... args) throws Exception {

        alledrogoService.addProduct(new Product("dupa", "dupa", 123));
        alledrogoService.addProduct(new Product("dupa1", "dupa1", 234));
        alledrogoService.addProduct(new Product("dupa2", "dupa2", 345));
        alledrogoService.addProduct(new Product("dupa3", "dupa3", 456));

    }
}
