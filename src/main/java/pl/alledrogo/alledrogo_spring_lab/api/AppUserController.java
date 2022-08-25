package pl.alledrogo.alledrogo_spring_lab.api;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.alledrogo.alledrogo_spring_lab.model.AppUser;
import pl.alledrogo.alledrogo_spring_lab.model.Role;
import pl.alledrogo.alledrogo_spring_lab.model.RoleToUserForm;
import pl.alledrogo.alledrogo_spring_lab.repository.AppUserRepository;
import pl.alledrogo.alledrogo_spring_lab.service.AppUserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/api")
public class AppUserController {

    private final AppUserService appUserService;
    private final AppUserRepository appUserRepository;

    public AppUserController(AppUserService appUserService, AppUserRepository appUserRepository) {
        this.appUserService = appUserService;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("/user")
    @ResponseBody()
    public ResponseEntity<?> registerUser(@RequestBody AppUser appUser, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        appUserService.registerUser(appUser, getSiteURL(request));
        return ResponseEntity.created(uri).body(JSONObject.quote("User saved"));
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@Param("code") String code) {
        if (appUserService.verify(code)) {
            return ResponseEntity.ok().body("verify_success");
        } else {
            return ResponseEntity.badRequest().body("verify_fail");
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getAppUsers() {
        return ResponseEntity.ok().body(appUserService.getAppUsers());
    }

    @PostMapping("/role")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(appUserService.saveRole(role));
    }

    @PostMapping("/role/toUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        appUserService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                AppUser user = appUserService.getAppUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("basketName", findBasketName(user.getUsername()))
                        .withClaim("name", findName(user.getUsername()))
                        .withClaim("isVerified", isUserVerified(user.getUsername()))
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception exception) {

                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());

                Map<String, String> error = new HashMap<>();
                error.put("access_token", exception.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh Token is Missing");

        }
    }
    private Boolean isUserVerified(String username) {
        AppUser user = this.appUserRepository.findByUsername(username).orElseThrow();
        return user.isVerified();
    }

    String findBasketName(String username) {
        AppUser user = this.appUserRepository.findByUsername(username).orElseThrow();
        return user.getBasket().getBasketName();
    }

    String findName(String username) {
        AppUser user = this.appUserRepository.findByUsername(username).orElseThrow();
        return user.getName();
    }

}


