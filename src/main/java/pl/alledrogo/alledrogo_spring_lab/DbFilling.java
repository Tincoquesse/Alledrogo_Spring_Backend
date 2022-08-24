package pl.alledrogo.alledrogo_spring_lab;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.alledrogo.alledrogo_spring_lab.api.AlledrogoController;
import pl.alledrogo.alledrogo_spring_lab.model.AppUser;
import pl.alledrogo.alledrogo_spring_lab.model.ProductCategory;
import pl.alledrogo.alledrogo_spring_lab.model.ProductDTO;
import pl.alledrogo.alledrogo_spring_lab.model.Role;
import pl.alledrogo.alledrogo_spring_lab.repository.AppUserRepository;
import pl.alledrogo.alledrogo_spring_lab.repository.BasketRepository;
import pl.alledrogo.alledrogo_spring_lab.repository.RoleRepository;
import pl.alledrogo.alledrogo_spring_lab.service.AppUserServiceImpl;

import java.util.ArrayList;

@Component
@Profile("prod")
public class DbFilling {

    private final AlledrogoController controller;
    private final AppUserServiceImpl appUserService;
    private final RoleRepository roleRepository;


    public DbFilling(AlledrogoController controller, AppUserServiceImpl appUserService, RoleRepository roleRepository, AppUserRepository appUserRepository, BasketRepository basketRepository) {
        this.controller = controller;
        this.appUserService = appUserService;
        this.roleRepository = roleRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {

        controller.addProduct(new ProductDTO("Lenovo 45D", "What", 111.65,
                "https://imgs.search.brave.com/ZMFeRlJFVSMMx0tMJw8vIguIHU-3TMD8SIaIpJqwyRs/rs:fit:150:150:1/g:ce/aHR0cDovL3N0YXRp/Yy5iaHBob3RvLmNv/bS9pbWFnZXMvaW1h/Z2VzMTUweDE1MC8x/NDQ1Mjc2MTE2MDAw/XzExODcxOTMuanBn",
                ProductCategory.WATCH));
        controller.addProduct(new ProductDTO("Asus TT", "Say", 222.89,
                "https://image.ceneostatic.pl/data/products/106929535/f-xiaomi-mi-smart-clock-bialy.jpg",
                ProductCategory.WATCH));
        controller.addProduct(new ProductDTO("Hammer XS", "You", 333.21,
                "https://prod-api.mediaexpert.pl/api/images/gallery_500_500/thumbnails/images/21/2145685/Smartfon-MYPHONE-Hammer-Energy-2-Pomaranczowy-tyl-front.jpg",
                ProductCategory.PHONE));
        controller.addProduct(new ProductDTO("IBM 3330", "Gimli", 444.34,
                "https://prod-api.mediaexpert.pl/api/images/gallery_500_500/thumbnails/images/22/2240435/Tablet-LENOVO-Tab-M8-HD-LTE-Szary-fronttyl.jpg",
                ProductCategory.TABLET));

        controller.addProduct(new ProductDTO("Lenovo 22", "Oin", 416.11,
                "https://f01.esfr.pl/foto/4/82436851785/e909154cfea48a21b919fbdafa207832/samsung-smartfon-galaxy-a32-128gb-black-samsung,82436851785_8.jpg",
                ProductCategory.WATCH));
        controller.addProduct(new ProductDTO("Asus 753", "Gloin", 222.23,
                "https://f01.esfr.pl/foto/3/60661691841/5a044345156f254a8710068c91f494dc/xiaomi-mi-robot-vacuum-mop-pro-bialy,60661691841_8.jpg",
                ProductCategory.WATCH));
        controller.addProduct(new ProductDTO("Hammer 778", "Thorin", 364.78,
                "https://f01.esfr.pl/foto/9/90018353577/1f90249bc1a94512a9af27e0137eb2a0/edifier-sluchawki-tws-x6-czarne,90018353577_8.jpg",
                ProductCategory.PHONE));
        controller.addProduct(new ProductDTO("IBM bv4", "Baldur", 4434.20,
                "https://f01.esfr.pl/foto/4/103954976745/ffcc720715baa88cb79b9c21f6495d69/lenovo-laptop-l5-r7-5800-16gb512ssd-rtx3060-w11,103954976745_8.jpg",
                ProductCategory.TABLET));

        controller.addProduct(new ProductDTO("Lenovo 66", "Lorem", 46.11,
                "https://f01.esfr.pl/foto/5/90723699369/e9b059f67336ed848a646881fb5eb072/philips-65oled806-oled-android-ambilight,90723699369_8.jpg",
                ProductCategory.WATCH));
        controller.addProduct(new ProductDTO("Asus hj7", "Ipsum", 275.23,
                "https://f01.esfr.pl/foto/6/72005636625/1990dd889e0ec5592a1f92ca3adfec78/reinston-uchwyt-plaski-40-90-reinston,72005636625_8.jpg",
                ProductCategory.WATCH));
        controller.addProduct(new ProductDTO("Hammer as4", "Dolor", 36.78,
                "https://f01.esfr.pl/foto/8/108976533097/51a002f387f1c728cd27fca8218a09de/samsung-smartw-galaxy-watch5-40mm-bt-sv-sams,108976533097_8.jpg",
                ProductCategory.PHONE));
        controller.addProduct(new ProductDTO("IBM 655", "Robor", 634.20,
                "https://f01.esfr.pl/foto/5/107851722289/1d64ec3d1f829c883f84803cccbee51e/siemens-eq-700-tq705r03,107851722289_8.jpg",
                ProductCategory.TABLET));

        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");
        roleRepository.save(admin);
        roleRepository.save(user);

        AppUser adminUser = new AppUser("Kamil", "admin", "admin", new ArrayList<>());
        appUserService.saveAdmin(adminUser);
    }
}
