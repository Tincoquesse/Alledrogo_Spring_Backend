package pl.alledrogo.alledrogo_spring_lab.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Basket {

    @Id
    @SequenceGenerator(
            name = "basket_sequence",
            sequenceName = "basket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "basket_sequence")
    private Long id;
    private String basketName;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Product> products = new ArrayList<>();


    public Basket() {
    }

    public Basket(String basketName) {
        this.basketName = basketName;

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

    public void removeProductFromBasket (Product product) {
        products.remove(product);
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
