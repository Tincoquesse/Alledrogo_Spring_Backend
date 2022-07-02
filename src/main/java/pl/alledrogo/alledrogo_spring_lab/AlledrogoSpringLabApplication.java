package pl.alledrogo.alledrogo_spring_lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.alledrogo.alledrogo_spring_lab.api.AlledrogoController;
import pl.alledrogo.alledrogo_spring_lab.api.AppUserController;
import pl.alledrogo.alledrogo_spring_lab.model.AppUser;
import pl.alledrogo.alledrogo_spring_lab.model.Role;
import pl.alledrogo.alledrogo_spring_lab.service.AppUserServiceImpl;

import java.util.ArrayList;


@SpringBootApplication

public class AlledrogoSpringLabApplication{


    private final AlledrogoController controller;
    private final AppUserServiceImpl appUserService;

    public AlledrogoSpringLabApplication(AlledrogoController controller, AppUserServiceImpl appUserService) {
        this.controller = controller;
        this.appUserService = appUserService;

    }

    public static void main(String[] args) {
        SpringApplication.run(AlledrogoSpringLabApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        controller.addProduct("Lenowo 45D", "pierwszy", 111, "https://imgs.search.brave.com/ZMFeRlJFVSMMx0tMJw8vIguIHU-3TMD8SIaIpJqwyRs/rs:fit:150:150:1/g:ce/aHR0cDovL3N0YXRp/Yy5iaHBob3RvLmNv/bS9pbWFnZXMvaW1h/Z2VzMTUweDE1MC8x/NDQ1Mjc2MTE2MDAw/XzExODcxOTMuanBn");
        controller.addProduct("Asus TT", "drugi", 222, "https://image.ceneostatic.pl/data/products/106929535/f-xiaomi-mi-smart-clock-bialy.jpg");
        controller.addProduct("Hammer XS", "trzeci", 333, "https://prod-api.mediaexpert.pl/api/images/gallery_500_500/thumbnails/images/21/2145685/Smartfon-MYPHONE-Hammer-Energy-2-Pomaranczowy-tyl-front.jpg");
        controller.addProduct("IBM 3330", "czwarty", 444, "https://prod-api.mediaexpert.pl/api/images/gallery_500_500/thumbnails/images/22/2240435/Tablet-LENOVO-Tab-M8-HD-LTE-Szary-fronttyl.jpg");
        controller.addBasket("koszyk1");
        controller.addBasket("koszyk2");
        controller.addBasket("koszyk3");
        controller.addProductToBasket("koszyk1", "Lenowo 45D");
        controller.addProductToBasket("koszyk1", "Asus TT");
        controller.addProductToBasket("koszyk2", "Hammer XS");

        appUserService.saveAppUser(new AppUser(null, "Kamil", "user", "user", new ArrayList<>()));
        appUserService.saveAppUser(new AppUser(null, "Kamil", "u", "u", new ArrayList<>()));
        appUserService.saveRole(new Role(null, "ROLE_USER"));
        appUserService.addRoleToUser("user", "ROLE_USER");
        appUserService.addRoleToUser("u", "ROLE_USER");
    }

}
