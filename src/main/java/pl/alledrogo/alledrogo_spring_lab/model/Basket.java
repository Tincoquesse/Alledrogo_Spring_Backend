package pl.alledrogo.alledrogo_spring_lab.model;


import javax.persistence.*;
import java.util.List;


@Entity
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    private List<Product> products;

    private String basketName;

    public Basket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProductToBasket(Product product) {
        products.add(product);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setBasketName(String basketName) {
        this.basketName = basketName;
    }

    public String getBasketName() {
        return basketName;
    }


}
