package pl.alledrogo.alledrogo_spring_lab.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.alledrogo.alledrogo_spring_lab.model.Basket;

import java.util.Optional;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Long> {

    Optional<Basket> findById(Long id);
    Optional<Basket> findByBasketName(String name);
}
