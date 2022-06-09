package pl.alledrogo.alledrogo_spring_lab.repositoryJPA;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.alledrogo.alledrogo_spring_lab.modelJPA.ProductAlt;

@Repository
public interface ProductAltRepository extends CrudRepository<ProductAlt, Long> {

}
