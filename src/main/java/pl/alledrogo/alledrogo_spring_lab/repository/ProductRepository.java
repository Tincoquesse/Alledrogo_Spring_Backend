package pl.alledrogo.alledrogo_spring_lab.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import pl.alledrogo.alledrogo_spring_lab.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByProductName(@NonNull String productName);



}
