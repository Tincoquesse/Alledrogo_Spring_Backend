package pl.alledrogo.alledrogo_spring_lab.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.alledrogo.alledrogo_spring_lab.exceptions.BasketNotFoundException;
import pl.alledrogo.alledrogo_spring_lab.exceptions.UserAlreadyExistException;
import pl.alledrogo.alledrogo_spring_lab.exceptions.UserNotFoundException;
import pl.alledrogo.alledrogo_spring_lab.model.AppUser;
import pl.alledrogo.alledrogo_spring_lab.model.Basket;
import pl.alledrogo.alledrogo_spring_lab.model.Role;
import pl.alledrogo.alledrogo_spring_lab.repository.AppUserRepository;
import pl.alledrogo.alledrogo_spring_lab.repository.BasketRepository;
import pl.alledrogo.alledrogo_spring_lab.repository.RoleRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Service
@Transactional

public class AppUserServiceImpl implements AppUserService, UserDetailsService {
    @Autowired
    private JavaMailSender mailSender;

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final BasketRepository basketRepository;


    public AppUserServiceImpl(AppUserRepository appUserRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, BasketRepository basketRepository) {
        this.appUserRepository = appUserRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.basketRepository = basketRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username).orElseThrow(() ->
                new UserNotFoundException("User " + username + " was not found."));
        System.out.println("User found");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(role ->
        {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }

    @Override
    public void registerUser(AppUser user, String siteUR) throws MessagingException, UnsupportedEncodingException {
        if (appUserRepository.findByUsername(user.getUsername()).isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.getRoles().add(roleRepository.findByName("ROLE_USER"));
            String basketCustomName = RandomString.make(20);
            Basket basket = new Basket(basketCustomName);
            basketRepository.save(basket);
            user.setBasket(basketRepository.findByBasketName(basketCustomName).get());
            String randomCode = RandomString.make(64);
            user.setVerificationCode(randomCode);
            sendVerificationEmail(user, siteUR);
            appUserRepository.save(user);
        } else {
            throw new UserAlreadyExistException("User " + user.getUsername()+ " already exist.");
        }


    }

    @Override
    public AppUser saveAdmin(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleRepository.findByName("ROLE_ADMIN"));
        String basketCustomName = RandomString.make(20);
        Basket basket = new Basket(basketCustomName);
        basketRepository.save(basket);
        user.setBasket(basketRepository.findByBasketName(basketCustomName).orElseThrow(() ->
                new BasketNotFoundException("Basket " +  basketCustomName + " was not found.")));
        user.setVerified(true);
        return appUserRepository.save(user);
    }



    @Override
    public void refresh(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                AppUser user = getAppUser(username);
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

    private void sendVerificationEmail(AppUser user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getUsername();
        String fromAddress = "kamilforex87@gmail.com";
        String senderName = "Alledrogo";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Alledrogo.pl.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        content = content.replace("[[name]]", user.getName());
        String verifyURL = siteURL + "/api/verify?code=" + user.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);
        helper.setText(content, true);
        mailSender.send(message);

    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user = appUserRepository.findByUsername(username).orElseThrow(() ->
                new UserNotFoundException("User " + username + " was not found."));
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public AppUser getAppUser(String username) {
        return appUserRepository.findByUsername(username).orElseThrow();
    }

    @Override
    public List<AppUser> getAppUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public boolean verify(String verificationCode) {
        AppUser appUser = appUserRepository.findByVerificationCode(verificationCode);

        if (appUser == null || appUser.isVerified()) {
            return false;
        } else {
            appUser.setVerificationCode(null);
            appUser.setVerified(true);
            appUserRepository.save(appUser);
            return true;
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
