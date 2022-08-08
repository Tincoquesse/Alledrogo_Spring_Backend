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
import pl.alledrogo.alledrogo_spring_lab.model.AppUser;
import pl.alledrogo.alledrogo_spring_lab.model.Basket;
import pl.alledrogo.alledrogo_spring_lab.model.Role;
import pl.alledrogo.alledrogo_spring_lab.repository.AppUserRepository;
import pl.alledrogo.alledrogo_spring_lab.repository.BasketRepository;
import pl.alledrogo.alledrogo_spring_lab.repository.RoleRepository;
import pl.alledrogo.alledrogo_spring_lab.security.filter.CustomAuthenticationFilter;
import pl.alledrogo.alledrogo_spring_lab.service.AppUserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class AppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AppUserService appUserService;

    @Autowired
    BasketRepository basketRepository;

    @Test
    public void shouldGetAppUsers() throws Exception {
        //GIVEN

        appUserService.saveRole(new Role("ROLE_USER"));

        basketRepository.save(new Basket("testOne"));
        basketRepository.save(new Basket("testTwo"));

        AppUser userOne = new AppUser("Kamil", "sound@gmail.com", "password", new ArrayList<>());
        AppUser userTwo = new AppUser("Rafal", "thunder@gmail.com", "password", new ArrayList<>());

        userOne.setBasket(basketRepository.findByBasketName("testOne"));
        userTwo.setBasket(basketRepository.findByBasketName("testTwo"));

        appUserRepository.save(userOne);
        appUserRepository.save(userTwo);

        appUserService.addRoleToUser("sound@gmail.com", "ROLE_USER");
        appUserService.addRoleToUser("thunder@gmail.com", "ROLE_USER");

        //WHEN
        String token = CustomAuthenticationFilter.get_admin_access_token("kamil.sound@gmail.com");
        MvcResult mvcResult = this.mockMvc.perform(get("/api/users")
                        .header("Authorization", "Bearer " + token))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        List<AppUser> users = Arrays.asList(objectMapper.readValue(contentAsString, AppUser[].class));

        //THEN
        assertNotNull(token);
        assertThat(users.size()).isEqualTo(2);
    }

    @Test
    public void shouldSaveUser() throws Exception {
        //GIVEN
        roleRepository.save(new Role("ROLE_USER"));
        AppUser appUser = new AppUser("Kamil", "sound@gmail.com", "password", new ArrayList<>());
        String json = objectMapper.writeValueAsString(appUser);

        //WHEN
        MvcResult mvcResult = this.mockMvc.perform(post("/api/user/save")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
//                        .servletPath("/localhost/api/user/save")
                )
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        //THEN
        assertThat(status).isEqualTo(201);
    }
}
