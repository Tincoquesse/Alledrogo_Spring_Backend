package pl.alledrogo.alledrogo_spring_lab.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Product> basketProducts = new HashSet<>();

    private String basketName;

    public Basket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Product> getBasketProducts() {
        return basketProducts;
    }

    public void addProductToBasket(Product product) {
        basketProducts.add(product);
    }

    public void setBasketProducts(Set<Product> products) {
        this.basketProducts = products;
    }

    public void setBasketName(String basketName) {
        this.basketName = basketName;
    }

    public String getBasketName() {
        return basketName;
    }


}
