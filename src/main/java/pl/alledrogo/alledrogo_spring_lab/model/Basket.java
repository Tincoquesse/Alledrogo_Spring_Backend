package pl.alledrogo.alledrogo_spring_lab.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
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
        System.out.println(product.toString() + ". Poziom encji Basket");
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
