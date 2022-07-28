package pl.alledrogo.alledrogo_spring_lab.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.alledrogo.alledrogo_spring_lab.model.Product;
import pl.alledrogo.alledrogo_spring_lab.model.ProductCategory;
import pl.alledrogo.alledrogo_spring_lab.model.ProductDTO;
import pl.alledrogo.alledrogo_spring_lab.repository.ProductRepository;
import pl.alledrogo.alledrogo_spring_lab.service.ProductMapper;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
class AlledrogoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    ProductRepository productRepository;


    @Test
    public void shouldGetAllProducts() throws Exception {
        //GIVEN
        productRepository.save(new Product("Asus", "computer", 666., "link", ProductCategory.LAPTOP));
        productRepository.save(new Product("Hammer", "phone", 111., "link", ProductCategory.PHONE));

        //WHEN
        MvcResult mvcResult = this.mockMvc.perform(get("/shop/product/getAll")).andReturn();

        //THEN
        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        List<ProductDTO> products = Arrays.asList(objectMapper.readValue(contentAsString, ProductDTO[].class));

        assertThat(products.size()).isEqualTo(2);
    }

    @Test
    public void shouldGetTheSameProductsAsGiven() throws Exception {
        //GIVEN
        productRepository.save(new Product("Asus", "computer", 666., "link", ProductCategory.LAPTOP));
        productRepository.save(new Product("Hammer", "phone", 111., "link", ProductCategory.PHONE));

        //WHEN
        MvcResult mvcResult = this.mockMvc.perform(get("/shop/product/getAll")).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        List<ProductDTO> products = Arrays.asList(objectMapper.readValue(contentAsString, ProductDTO[].class));

        //THEN
        assertThat(products).containsExactlyInAnyOrder(
                new ProductDTO("Asus", "computer", 666., "link", ProductCategory.LAPTOP),
                new ProductDTO("Hammer", "phone", 111., "link", ProductCategory.PHONE));
    }

    @Test
    public void shouldAddProductToDatabase() throws Exception {
        //GIVEN
        ProductDTO productDTO =
                new ProductDTO("Asus", "computer", 666., "link", ProductCategory.LAPTOP);

        //WHEN
        String json = objectMapper.writeValueAsString(productDTO);
        MvcResult mvcResult = this.mockMvc.perform(post("/shop/product/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                        .andReturn();
        int status = mvcResult.getResponse().getStatus();

        //THEN
        assertThat(status).isEqualTo(201);
        assertThat(productDTO).isEqualTo(ProductMapper.fromEntity(productRepository.findByProductName("Asus").get()));
    }

    @Test
    public void shouldRemoveProductFromDatabase() throws Exception {
        //GIVEN
        productRepository.save(new Product("Asus", "computer", 666., "link", ProductCategory.LAPTOP));

        //WHEN
        MvcResult mvcResult = this.mockMvc.perform(delete("/shop/product/remove/Asus")).andReturn();
        int status = mvcResult.getResponse().getStatus();

        //THEN
        assertThat(status).isEqualTo(202);
        assertThat(productRepository.findAll().size()).isEqualTo(0);
    }
}
