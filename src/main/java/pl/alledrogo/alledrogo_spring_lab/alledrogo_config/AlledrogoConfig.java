package pl.alledrogo.alledrogo_spring_lab.alledrogo_config;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import pl.alledrogo.alledrogo_spring_lab.alledrogo_API.AlledrogoController;

import java.util.Arrays;

@Configuration
@EnableTransactionManagement
public class AlledrogoConfig {

final
AlledrogoController controller;

    public AlledrogoConfig(AlledrogoController controller) {
        this.controller = controller;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedMethods(Arrays.asList(CorsConfiguration.ALL));
        corsConfiguration.setAllowedHeaders(Arrays.asList(CorsConfiguration.ALL));
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
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
