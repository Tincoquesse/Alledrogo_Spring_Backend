package pl.alledrogo.alledrogo_spring_lab.service;

import pl.alledrogo.alledrogo_spring_lab.model.Product;
import pl.alledrogo.alledrogo_spring_lab.model.ProductDTO;

public class ProductMapper {

    public static Product fromDTO(ProductDTO productDTO) {
        return new Product(productDTO.getProductName(), productDTO.getProductDescription(), productDTO.getProductPrice(), productDTO.getImageURL(), productDTO.getCategory());
    }

    public static ProductDTO fromEntity(Product product) {
        return new ProductDTO(product.getProductName(), product.getProductDescription(), product.getProductPrice(), product.getImageURL(), product.getCategory());
    }
}
