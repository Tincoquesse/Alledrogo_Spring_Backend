package pl.alledrogo.alledrogo_spring_lab.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.utility.RandomString;
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
import pl.alledrogo.alledrogo_spring_lab.model.RoleToUserForm;
import pl.alledrogo.alledrogo_spring_lab.repository.AppUserRepository;
import pl.alledrogo.alledrogo_spring_lab.repository.BasketRepository;
import pl.alledrogo.alledrogo_spring_lab.repository.RoleRepository;
import pl.alledrogo.alledrogo_spring_lab.security.filter.CustomAuthenticationFilter;
import pl.alledrogo.alledrogo_spring_lab.service.AppUserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
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

        userOne.setBasket(basketRepository.findByBasketName("testOne").get());
        userTwo.setBasket(basketRepository.findByBasketName("testTwo").get());

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
        MvcResult mvcResult = this.mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                        .servletPath("/api/user"))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        //THEN
        assertThat(status).isEqualTo(201);
        assertThat(appUserRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    public void shouldVerifyUser() throws Exception {
        //GIVEN
        appUserService.saveRole(new Role("ROLE_USER"));
        basketRepository.save(new Basket("testOne"));
        AppUser userOne = new AppUser("Kamil", "sound@gmail.com", "password", new ArrayList<>());
        userOne.setBasket(basketRepository.findByBasketName("testOne").get());
        String randomCode = RandomString.make(64);
        userOne.setVerificationCode(randomCode);
        appUserRepository.save(userOne);
        appUserService.addRoleToUser("sound@gmail.com", "ROLE_USER");

        //WHEN
        MvcResult mvcResult = this.mockMvc.perform(get("/api/verify?code=" + randomCode)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String body = mvcResult.getResponse().getContentAsString();

        //THEN
        assertThat(status).isEqualTo(200);
        assertThat(body).isEqualTo("verify_success");
    }

    @Test
    public void shouldAddRole() throws Exception {
        //GIVEN
        Role role = new Role("ROLE_USER");
        String json = objectMapper.writeValueAsString(role);

        //WHEN
        String token = CustomAuthenticationFilter.get_admin_access_token("kamil.sound@gmail.com");
        MvcResult mvcResult = this.mockMvc.perform(post("/api/role")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        //THEN
        assertThat(status).isEqualTo(201);
        assertThat(roleRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    public void shouldAddRoleToUser() throws Exception {
        //GIVEN
        Role role = new Role("ROLE_ADMIN");
        roleRepository.save(role);
        basketRepository.save(new Basket("testOne"));

        AppUser user = new AppUser("Kamil", "sound@gmail.com", "password", new ArrayList<>());
        user.setBasket(basketRepository.findByBasketName("testOne").get());
        appUserRepository.save(user);

        String json = objectMapper.writeValueAsString(new RoleToUserForm("sound@gmail.com", "ROLE_ADMIN"));

        //WHEN
        String token = CustomAuthenticationFilter.get_admin_access_token("kamil.sound@gmail.com");
        MvcResult mvcResult = this.mockMvc.perform(post("/api/role/toUser")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        //THEN
        assertThat(status).isEqualTo(200);
        assertThat(roleRepository.findByName("ROLE_ADMIN")).isSameAs(role);
    }
}
