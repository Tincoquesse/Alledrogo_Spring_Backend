package pl.alledrogo.alledrogo_spring_lab.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.alledrogo.alledrogo_spring_lab.repository.ProductsInMemoryProvider;
import pl.alledrogo.alledrogo_spring_lab.service.AlledrogoService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AlledrogoControllerTest {

    ProductsInMemoryProvider productsInMemoryProvider = new ProductsInMemoryProvider();
    AlledrogoService alledrogoService = new AlledrogoService(productsInMemoryProvider);
    AlledrogoController alledrogoController = new AlledrogoController(alledrogoService);

    @BeforeEach
    void clearProductsList() { productsInMemoryProvider.clearProductsList();}

    @Test
    void addProduct() {
    //given
        alledrogoController.addProduct("test1", "testowy", 1234.);
    //when
        int result= 1;
    //then
        assertThat(alledrogoController.getAllProducts().size()).isEqualTo(result);
    }

    @Test
    void addTwoSameNameProducts() {
        //given
        alledrogoController.addProduct("test1", "testowy", 1234.);
        alledrogoController.addProduct("test1", "testowy", 1234.);
        //when
        int result= 1;
        //then
        assertThat(alledrogoController.getAllProducts().size()).isEqualTo(result);
    }

    @Test
    void getAllProducts() {
        //given
        alledrogoController.addProduct("test1", "testowy", 1234.);
        alledrogoController.addProduct("test2", "testowy", 1234.);
        alledrogoController.addProduct("test3", "testowy", 1234.);
        alledrogoController.addProduct("test4", "testowy", 1234.);
        //when
        int result = 4;
        //then
        assertThat(alledrogoController.getAllProducts().size()).isEqualTo(result);
    }

    @Test
    void getAllProductsFromBasket() {
    }

    @Test
    void makeOrder() {

    }

    @Test
    void addProductToBasket() {
    }

    @Test
    void removeProductFromBasket() {
    }
}