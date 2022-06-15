package pl.alledrogo.alledrogo_spring_lab.config;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import pl.alledrogo.alledrogo_spring_lab.controller.AlledrogoController;

@Configuration
public class AlledrogoConfig {

final
AlledrogoController controller;

    public AlledrogoConfig(AlledrogoController controller) {
        this.controller = controller;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        controller.addProduct("produkt1", "pierwszy", 111);
        controller.addProduct("produkt2", "drugi", 222);
        controller.addProduct("produkt3", "trzeci", 333);
        controller.addBasket("koszyk1");
        controller.addBasket("koszyk2");
        controller.addProductToBasket("koszyk1", "produkt1");
        controller.addProductToBasket("koszyk1", "produkt2");
        controller.addProductToBasket("koszyk2", "produkt3");
    }
}
