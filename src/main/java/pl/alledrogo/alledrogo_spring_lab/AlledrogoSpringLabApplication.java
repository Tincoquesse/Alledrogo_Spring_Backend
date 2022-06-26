package pl.alledrogo.alledrogo_spring_lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.alledrogo.alledrogo_spring_lab.api.AlledrogoController;


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
        controller.addProduct("Lenowo 45D", "pierwszy", 111, "https://rivieramaison.com/media/catalog/product/cache/f14a4baae2ebe06b9127e65ce5758f37/4/8/482160_2.jpg");
        controller.addProduct("Asus TT", "drugi", 222, "https://image.ceneostatic.pl/data/products/106929535/f-xiaomi-mi-smart-clock-bialy.jpg");
        controller.addProduct("Hammer XS", "trzeci", 333, "https://prod-api.mediaexpert.pl/api/images/gallery_500_500/thumbnails/images/21/2145685/Smartfon-MYPHONE-Hammer-Energy-2-Pomaranczowy-tyl-front.jpg");
        controller.addProduct("IBM 3330", "czwarty", 444, "https://prod-api.mediaexpert.pl/api/images/gallery_500_500/thumbnails/images/22/2240435/Tablet-LENOVO-Tab-M8-HD-LTE-Szary-fronttyl.jpg");
        controller.addBasket("koszyk1");
        controller.addBasket("koszyk2");
        controller.addBasket("koszyk3");
        controller.addProductToBasket("koszyk1", "Lenowo 45D");
        controller.addProductToBasket("koszyk1", "Asus TT");
        controller.addProductToBasket("koszyk2", "Hammer XS");
    }

}
