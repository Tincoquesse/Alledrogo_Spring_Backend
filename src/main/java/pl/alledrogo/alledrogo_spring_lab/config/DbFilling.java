package pl.alledrogo.alledrogo_spring_lab.config;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.alledrogo.alledrogo_spring_lab.api.AlledrogoController;
import pl.alledrogo.alledrogo_spring_lab.model.Product;
import pl.alledrogo.alledrogo_spring_lab.model.ProductCategory;

@Component
@Profile("prod")
public class DbFilling {

    private final AlledrogoController controller;

    public DbFilling(AlledrogoController controller) {
        this.controller = controller;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {

        Product lenowo = new Product("Lenowo 45D", "pierwszy", 111.,
                "https://imgs.search.brave.com/ZMFeRlJFVSMMx0tMJw8vIguIHU-3TMD8SIaIpJqwyRs/rs:fit:150:150:1/g:ce/aHR0cDovL3N0YXRp/Yy5iaHBob3RvLmNv/bS9pbWFnZXMvaW1h/Z2VzMTUweDE1MC8x/NDQ1Mjc2MTE2MDAw/XzExODcxOTMuanBn",
                ProductCategory.WATCH);
        Product asus = new Product("Asus TT", "drugi", 222.,
                "https://image.ceneostatic.pl/data/products/106929535/f-xiaomi-mi-smart-clock-bialy.jpg",
                ProductCategory.WATCH);
        Product hammer = new Product("Hammer XS", "trzeci", 333.,
                "https://prod-api.mediaexpert.pl/api/images/gallery_500_500/thumbnails/images/21/2145685/Smartfon-MYPHONE-Hammer-Energy-2-Pomaranczowy-tyl-front.jpg",
                ProductCategory.PHONE);
        Product ibm = new Product("IBM 3330", "czwarty", 444.,
                "https://prod-api.mediaexpert.pl/api/images/gallery_500_500/thumbnails/images/22/2240435/Tablet-LENOVO-Tab-M8-HD-LTE-Szary-fronttyl.jpg",
                ProductCategory.TABLET);


        controller.addProduct(lenowo);
        controller.addProduct(asus);
        controller.addProduct(hammer);
        controller.addProduct(ibm);
    }
}
