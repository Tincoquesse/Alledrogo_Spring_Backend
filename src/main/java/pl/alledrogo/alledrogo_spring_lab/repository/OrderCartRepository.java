package pl.alledrogo.alledrogo_spring_lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.alledrogo.alledrogo_spring_lab.model.AppUser;
import pl.alledrogo.alledrogo_spring_lab.model.OrderCart;

import java.util.Optional;

@Repository
public interface OrderCartRepository extends JpaRepository<OrderCart, Long> {

    Optional<OrderCart> findByAppUser(AppUser appUser);
}
