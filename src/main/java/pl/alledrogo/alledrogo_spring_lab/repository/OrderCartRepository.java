package pl.alledrogo.alledrogo_spring_lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.alledrogo.alledrogo_spring_lab.model.OrderCart;

public interface OrderCartRepository extends JpaRepository<OrderCart, Long> {
}
