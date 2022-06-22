package pl.alledrogo.alledrogo_spring_lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.alledrogo.alledrogo_spring_lab.alledrogo_API.AlledrogoController;


@SpringBootApplication

public class AlledrogoSpringLabApplication{

    final
    AlledrogoController controller;

    public AlledrogoSpringLabApplication(AlledrogoController controller) {
        this.controller = controller;
    }

    public static void main(String[] args) {
        SpringApplication.run(AlledrogoSpringLabApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        controller.addProduct("produkt1", "pierwszy", 111);
        controller.addProduct("produkt2", "drugi", 222);
        controller.addProduct("produkt3", "trzeci", 333);
        controller.addProduct("produkt4", "czwarty", 444);
        controller.addBasket("koszyk1");
        controller.addBasket("koszyk2");
        controller.addBasket("koszyk3");
        controller.addProductToBasket("koszyk1", "produkt1");
        controller.addProductToBasket("koszyk1", "produkt2");
        controller.addProductToBasket("koszyk2", "produkt3");
    }

}
