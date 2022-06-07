package pl.alledrogo.alledrogo_spring_lab.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.alledrogo.alledrogo_spring_lab.model.Product;
import pl.alledrogo.alledrogo_spring_lab.repository.ProductsInMemoryProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlledrogoServiceTest {



    @Autowired
    AlledrogoService alledrogoService;


    @Test
    void productIsSaved() {
        //given
        Product product1 = new Product("dupa", "dupa", 1234);
        Product product2 = new Product("dupa2", "dupa2", 1234);

        //when
        alledrogoService.addProduct(product1);
        alledrogoService.addProduct(product2);
        int result = 2;

        //then
        assertThat(alledrogoService.getAllProducts().size()).isEqualTo(result);

    }
    @Test
    void isSavedInBasket() {
        //given
        Product product1 = new Product("dupa", "dupa", 1234);
        alledrogoService.addProduct(product1);

        //when
        alledrogoService.addProductToBasket("dupa");

        //then
        assertThat(alledrogoService.getALlProductsFromBasket().size()).isEqualTo(1);
    }

    @Test
    void productFromOrderSameAsGIven () {
        //given
        Product product1 = new Product("dupa", "dupa", 1234);
        alledrogoService.addProduct(product1);

        //when
        alledrogoService.addProductToBasket("dupa");


    }
}