package pl.alledrogo.alledrogo_spring_lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.alledrogo.alledrogo_spring_lab.model.OrderCart;
import pl.alledrogo.alledrogo_spring_lab.model.OrderCartDTO;
import pl.alledrogo.alledrogo_spring_lab.repository.AppUserRepository;
import pl.alledrogo.alledrogo_spring_lab.repository.BasketRepository;

import java.time.LocalDateTime;

@Component
public class OrderCartMapper {

    private static BasketRepository basketRepository;

    private static AppUserRepository appUserRepository;

    public OrderCartMapper(BasketRepository basketRepository, AppUserRepository appUserRepository) {
        OrderCartMapper.basketRepository = basketRepository;
        OrderCartMapper.appUserRepository = appUserRepository;
    }

    public static OrderCart fromDTO(OrderCartDTO orderCartDTO) {

        return new OrderCart(basketRepository.findByBasketName(orderCartDTO.getBasketName()).get().getProducts(),
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

}
