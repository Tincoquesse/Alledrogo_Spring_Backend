package pl.alledrogo.alledrogo_spring_lab.serviceJPA;


import org.springframework.stereotype.Service;
import pl.alledrogo.alledrogo_spring_lab.modelJPA.ProductAlt;
import pl.alledrogo.alledrogo_spring_lab.repositoryJPA.ProductAltRepository;

@Service
public class AlledrogoJPAService {

    ProductAltRepository productAltRepository;

    public AlledrogoJPAService(ProductAltRepository productAltRepository) {
        this.productAltRepository = productAltRepository;
    }

    public void addProduct(ProductAlt productAlt) {
        productAltRepository.save(productAlt);
    }
}
