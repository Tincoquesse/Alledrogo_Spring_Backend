package pl.alledrogo.alledrogo_spring_lab.config;


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
import pl.alledrogo.alledrogo_spring_lab.repository.RoleRepository;
import pl.alledrogo.alledrogo_spring_lab.service.AppUserServiceImpl;

import java.util.Collections;

@Component
@Profile("prod")
public class DbFilling {

    private final AlledrogoController controller;
    private final AppUserServiceImpl appUserService;
    private final RoleRepository roleRepository;
    private final AppUserRepository appUserRepository;

    public DbFilling(AlledrogoController controller, AppUserServiceImpl appUserService, RoleRepository roleRepository, AppUserRepository appUserRepository) {
        this.controller = controller;
        this.appUserService = appUserService;
        this.roleRepository = roleRepository;
        this.appUserRepository = appUserRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {

        ProductDTO lenovo = new ProductDTO("Lenowo 45D", "pierwszy", 111.,
                "https://imgs.search.brave.com/ZMFeRlJFVSMMx0tMJw8vIguIHU-3TMD8SIaIpJqwyRs/rs:fit:150:150:1/g:ce/aHR0cDovL3N0YXRp/Yy5iaHBob3RvLmNv/bS9pbWFnZXMvaW1h/Z2VzMTUweDE1MC8x/NDQ1Mjc2MTE2MDAw/XzExODcxOTMuanBn",
                ProductCategory.WATCH);
        ProductDTO asus = new ProductDTO("Asus TT", "drugi", 222.,
                "https://image.ceneostatic.pl/data/products/106929535/f-xiaomi-mi-smart-clock-bialy.jpg",
                ProductCategory.WATCH);
        ProductDTO hammer = new ProductDTO("Hammer XS", "trzeci", 333.,
                "https://prod-api.mediaexpert.pl/api/images/gallery_500_500/thumbnails/images/21/2145685/Smartfon-MYPHONE-Hammer-Energy-2-Pomaranczowy-tyl-front.jpg",
                ProductCategory.PHONE);
        ProductDTO ibm = new ProductDTO("IBM 3330", "czwarty", 444.,
                "https://prod-api.mediaexpert.pl/api/images/gallery_500_500/thumbnails/images/22/2240435/Tablet-LENOVO-Tab-M8-HD-LTE-Szary-fronttyl.jpg",
                ProductCategory.TABLET);

        controller.addProduct(lenovo);
        controller.addProduct(asus);
        controller.addProduct(hammer);
        controller.addProduct(ibm);

        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");
        AppUser adminUser = new AppUser("Kamil", "kamil.sound@gmail.com", "password", Collections.emptyList());

        roleRepository.save(admin);
        roleRepository.save(user);
        appUserRepository.save(adminUser);
        appUserService.saveAdmin(adminUser);

    }
}
