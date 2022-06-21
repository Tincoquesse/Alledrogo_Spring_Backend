package pl.alledrogo.alledrogo_spring_lab.alledrogo_controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AlledrogoControllerTest {

    @Autowired
    AlledrogoController alledrogoJPAController;


    @Test
    void addProduct() {
    //given
        alledrogoJPAController.addProduct("test1", "testowy", 1234);
    //when
        int result= 1;
    //then
        assertThat(alledrogoJPAController.getAllProducts().size()).isEqualTo(result);
    }

    @Test
    void addTwoSameNameProducts() {
        //given
        alledrogoJPAController.addProduct("test1", "testowy", 1234.);
        alledrogoJPAController.addProduct("test1", "testowy", 1234.);
        //when
        int result= 2;
        //then
        assertThat(alledrogoJPAController.getAllProducts().size()).isEqualTo(result);
    }

    @Test
    void getAllProducts() {
        //given
        alledrogoJPAController.addProduct("test1", "testowy", 1234.);
        alledrogoJPAController.addProduct("test2", "testowy", 1234.);
        alledrogoJPAController.addProduct("test3", "testowy", 1234.);
        alledrogoJPAController.addProduct("test4", "testowy", 1234.);
        //when
        int result = 4;
        //then
        assertThat(alledrogoJPAController.getAllProducts().size()).isEqualTo(result);
    }

    @Test
    void getAllProductsFromBasket() {
    }

    @Test
    void makeOrder() {

    }

    @Test
    void addProductToBasket() {
        //given
        alledrogoJPAController.addProduct("test1", "testowy", 1234.);
        alledrogoJPAController.addProduct("test2", "testowy", 1234.);
        alledrogoJPAController.addBasket("koszyk");

        //when
        int result = 2;
        alledrogoJPAController.addProductToBasket("koszyk", "test1");
        alledrogoJPAController.addProductToBasket("koszyk", "test2");
        //then
        assertThat(alledrogoJPAController.getAllProductsFromBasket("koszyk").size()).isEqualTo(result);
    }

    @Test
    void removeProductFromBasket() {
    }
}