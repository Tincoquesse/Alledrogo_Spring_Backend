package pl.alledrogo.alledrogo_spring_lab.alledrogo_repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.alledrogo.alledrogo_spring_lab.alledrogo_model.Basket;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    Optional<Basket> findById(Long id);
    Optional<Basket> findByBasketName(String name);
    void deleteByBasketName(String name);
}
