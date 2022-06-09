package pl.alledrogo.alledrogo_spring_lab.service;


import org.springframework.stereotype.Service;
import pl.alledrogo.alledrogo_spring_lab.model.Product;
import pl.alledrogo.alledrogo_spring_lab.repository.ProductRepository;

import java.util.List;

@Service
public class AlledrogoService {

    ProductRepository productAltRepository;

    public AlledrogoService(ProductRepository productAltRepository) {
        this.productAltRepository = productAltRepository;
    }

    public void addProduct(Product productAlt) {
        productAltRepository.save(productAlt);
    }

    public List<Product> getAll() {
        return (List<Product>) productAltRepository.findAll();
    }

    public Product findProductByName(String name) {
        return productAltRepository.findByProductName(name);
    }

    public void deleteProduct(Product productAlt) {
        productAltRepository.delete(productAlt);
    }
}
