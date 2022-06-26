package pl.alledrogo.alledrogo_spring_lab.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.alledrogo.alledrogo_spring_lab.service.AlledrogoService;

import javax.transaction.Transactional;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AlledrogoControllerTest {

    @Autowired
    AlledrogoController alledrogoJPAController;
    @Autowired
    AlledrogoService alledrogoService;

    @BeforeEach
    void clear() {
        alledrogoService.clearProductsList();
    }

    @Test
    void addProduct() {
    //given
        alledrogoJPAController.addProduct("test1", "testowy", 1234, "ccc");
    //when
        int result= 1;
    //then
        assertThat(alledrogoJPAController.getAllProducts().getBody().size()).isEqualTo(result);
    }

    @Test
    void addTwoSameNameProducts() {
        //given
        alledrogoJPAController.addProduct("test1", "testowy", 1234., "aaa");
        alledrogoJPAController.addProduct("test1", "testowy", 1234., "bbb");
        //when
        int result= 2;
        //then
        assertThat(alledrogoJPAController.getAllProducts().getBody().size()).isEqualTo(result);
    }

    @Test
    void getAllProducts() {
        //given
        alledrogoJPAController.addProduct("test1", "testowy", 1234., "ddd");
        alledrogoJPAController.addProduct("test2", "testowy", 1234., "eee");
        alledrogoJPAController.addProduct("test3", "testowy", 1234., "fff");
        alledrogoJPAController.addProduct("test4", "testowy", 1234., "ggg");
        //when
        int result = 4;
        //then
        assertThat(alledrogoJPAController.getAllProducts().getBody().size()).isEqualTo(result);
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
        alledrogoJPAController.addProduct("test1", "testowy", 1234., "hhh");
        alledrogoJPAController.addProduct("test2", "testowy", 1234., "iii");
        alledrogoJPAController.addBasket("koszyk");

        //when
        int result = 2;
        alledrogoJPAController.addProductToBasket("koszyk", "test1");
        alledrogoJPAController.addProductToBasket("koszyk", "test2");
        //then
        assertThat(alledrogoJPAController.getAllProductsFromBasket("koszyk").getBody().size()).isEqualTo(result);
    }

    @Test
    void removeProductFromBasket() {
    }
}