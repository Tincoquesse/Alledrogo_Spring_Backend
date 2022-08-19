package pl.alledrogo.alledrogo_spring_lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.alledrogo.alledrogo_spring_lab.model.OrderCart;
import pl.alledrogo.alledrogo_spring_lab.model.OrderCartDTO;
import pl.alledrogo.alledrogo_spring_lab.model.Product;
import pl.alledrogo.alledrogo_spring_lab.repository.AppUserRepository;
import pl.alledrogo.alledrogo_spring_lab.repository.BasketRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderCartMapper {

    private static BasketRepository basketRepository;

    private static AppUserRepository appUserRepository;

    public OrderCartMapper(BasketRepository basketRepository, AppUserRepository appUserRepository) {
        OrderCartMapper.basketRepository = basketRepository;
        OrderCartMapper.appUserRepository = appUserRepository;
    }

    public static OrderCart fromDTO(OrderCartDTO orderCartDTO) {

        return new OrderCart(mappingProductsFromBasket(orderCartDTO.getBasketName()),
                orderCartDTO.getStreet(),
                orderCartDTO.getPostalCode(),
                orderCartDTO.getCity(),
                orderCartDTO.getPhoneNumber(),
                LocalDateTime.now(),
                appUserRepository.findByUsername(orderCartDTO.getUsername()),
                orderCartDTO.getFirstAndLastName()
        );
    }

    public static OrderCartDTO fromEntity(OrderCart orderCart) {

        return new OrderCartDTO(orderCart.getAppUser().getUsername(),
                orderCart.getFirstAndLastName(),
                orderCart.getAppUser().getBasket().getBasketName(),
                orderCart.getStreet(),
                orderCart.getPostalCode(),
                orderCart.getCity(),
                orderCart.getPhoneNumber());
    }

    private static List<Product> mappingProductsFromBasket(String basketName) {
        return new ArrayList<>(basketRepository.findByBasketName(basketName).get().getProducts());
    }

}
