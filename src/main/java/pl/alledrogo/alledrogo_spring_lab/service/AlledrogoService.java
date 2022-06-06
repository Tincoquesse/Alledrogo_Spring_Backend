package pl.alledrogo.alledrogo_spring_lab.service;

import org.springframework.stereotype.Service;
import pl.alledrogo.alledrogo_spring_lab.repository.ProductsInMemoryProvider;

@Service
public class AlledrogoService {

    ProductsInMemoryProvider products;

    public AlledrogoService(ProductsInMemoryProvider products) {
        this.products = products;
    }
}
