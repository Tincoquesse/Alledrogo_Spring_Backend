package pl.alledrogo.alledrogo_spring_lab.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.alledrogo.alledrogo_spring_lab.model.AppUser;
import pl.alledrogo.alledrogo_spring_lab.model.Role;
import pl.alledrogo.alledrogo_spring_lab.repository.AppUserRepository;
import pl.alledrogo.alledrogo_spring_lab.repository.RoleRepository;
import pl.alledrogo.alledrogo_spring_lab.security.filter.CustomAuthenticationFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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


    @Test
    public void shouldGetAppUsers() throws Exception {
        //GIVEN
        Role user = new Role("ROLE_USER");
        roleRepository.save(user);
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ROLE_ADMIN"));
        AppUser adminUser = new AppUser("Kamil", "kamil.sound@gmail.com", "password", roles);
        appUserRepository.save(adminUser);

        //WHEN
        String token = CustomAuthenticationFilter.get_admin_access_token("john");
        MvcResult mvcResult = this.mockMvc.perform(get("/api/users")
                .header("Authorization", token))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        List<AppUser> products = Arrays.asList(objectMapper.readValue(contentAsString, AppUser[].class));

        //THEN
        assertNotNull(token);
    }
}
