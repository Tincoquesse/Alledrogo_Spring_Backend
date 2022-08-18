package pl.alledrogo.alledrogo_spring_lab.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Entity

public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    private String password;

    @OneToOne(fetch = FetchType.EAGER)
    private Basket basket = new Basket();

    @OneToMany
    private List<OrderCart> orderCarts = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>() ;

    private boolean isVerified = false;
    private String verificationCode;

    public AppUser( String name, String username, String password, Collection<Role> roles) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public AppUser() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Optional<Basket> basket) {
        this.basket = basket.orElseThrow();
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public List<OrderCart> getOrderCarts() {
        return orderCarts;
    }

    public void setOrderCarts(List<OrderCart> orderCarts) {
        this.orderCarts = orderCarts;
    }
}
