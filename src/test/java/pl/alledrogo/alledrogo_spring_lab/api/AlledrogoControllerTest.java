package pl.alledrogo.alledrogo_spring_lab.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.alledrogo.alledrogo_spring_lab.model.ProductCategory;
import pl.alledrogo.alledrogo_spring_lab.service.AlledrogoServiceImpl;

import javax.transaction.Transactional;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AlledrogoControllerTest {

    @Autowired
    AlledrogoController alledrogoJPAController;
    @Autowired
    AlledrogoServiceImpl alledrogoService;

    @BeforeEach
    void clear() {
        alledrogoService.clearProductsList();
    }

    @Test
    void addProduct() {
    //given
        alledrogoJPAController.addProduct("test1", "testowy", 1234, "ccc", ProductCategory.PHONE);
    //when
        int result= 1;
    //then
        assertThat(alledrogoJPAController.getAllProducts().getBody().size()).isEqualTo(result);
    }

    @Test
    void addTwoSameNameProducts() {
        //given
        alledrogoJPAController.addProduct("test1", "testowy", 1234., "aaa", ProductCategory.PHONE);
        alledrogoJPAController.addProduct("test1", "testowy", 1234., "bbb", ProductCategory.WATCH);
        //when
        int result= 2;
        //then
        assertThat(alledrogoJPAController.getAllProducts().getBody().size()).isEqualTo(result);
    }

    @Test
    void getAllProducts() {
        //given
        alledrogoJPAController.addProduct("test1", "testowy", 1234., "ddd",
                ProductCategory.TABLET);
        alledrogoJPAController.addProduct("test2", "testowy", 1234., "eee",
                ProductCategory.LAPTOP);
        alledrogoJPAController.addProduct("test3", "testowy", 1234., "fff",
                ProductCategory.TABLET);
        alledrogoJPAController.addProduct("test4", "testowy", 1234., "ggg",
                ProductCategory.TABLET);
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
        alledrogoJPAController.addProduct("test1", "testowy", 1234., "hhh", ProductCategory.PHONE);
        alledrogoJPAController.addProduct("test2", "testowy", 1234., "iii", ProductCategory.WATCH);

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