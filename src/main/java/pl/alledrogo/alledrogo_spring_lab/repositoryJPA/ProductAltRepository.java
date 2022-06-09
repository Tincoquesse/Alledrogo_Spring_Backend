package pl.alledrogo.alledrogo_spring_lab.repositoryJPA;



import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import pl.alledrogo.alledrogo_spring_lab.modelJPA.ProductAlt;

import java.util.Optional;

@Repository
public interface ProductAltRepository extends CrudRepository<ProductAlt, Long> {
    ProductAlt findByProductName(@NonNull String productName);



}
