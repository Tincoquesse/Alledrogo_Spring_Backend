package pl.alledrogo.alledrogo_spring_lab.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.alledrogo.alledrogo_spring_lab.controllerJPA.AlledrogoJPAController;
import pl.alledrogo.alledrogo_spring_lab.service.AlledrogoService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AlledrogoControllerTest {

    @Autowired
    AlledrogoJPAController alledrogoJPAController;

    @Autowired
    AlledrogoService alledrogoService;

    @BeforeEach
    void clearProductsList() { alledrogoService.clearProductsList();}

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
    }

    @Test
    void removeProductFromBasket() {
    }
}