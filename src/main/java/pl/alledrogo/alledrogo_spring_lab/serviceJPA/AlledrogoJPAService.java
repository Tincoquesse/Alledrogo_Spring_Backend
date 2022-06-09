package pl.alledrogo.alledrogo_spring_lab.serviceJPA;


import org.springframework.stereotype.Service;
import pl.alledrogo.alledrogo_spring_lab.modelJPA.ProductAlt;
import pl.alledrogo.alledrogo_spring_lab.repositoryJPA.ProductAltRepository;

import java.util.List;

@Service
public class AlledrogoJPAService {

    ProductAltRepository productAltRepository;

    public AlledrogoJPAService(ProductAltRepository productAltRepository) {
        this.productAltRepository = productAltRepository;
    }

    public void addProduct(ProductAlt productAlt) {
        productAltRepository.save(productAlt);
    }

    public List<ProductAlt> getAll() {
        return (List<ProductAlt>) productAltRepository.findAll();
    }

    public ProductAlt findProductByName(String name) {
        return productAltRepository.findByProductName(name);
    }

    public void deleteProduct(ProductAlt productAlt) {
        productAltRepository.delete(productAlt);
    }
}
