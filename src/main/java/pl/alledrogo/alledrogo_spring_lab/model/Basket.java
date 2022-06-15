package pl.alledrogo.alledrogo_spring_lab.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Product> productsList = new ArrayList<>();

    private String basketName;

    public Basket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public void addProductToBasket(Product product) {
        productsList.add(product);
    }

    public void removeProductFromBasket (Product product) {
        productsList.remove(product);
    }

    public void setProductsList(List<Product> products) {
        this.productsList = products;
    }

    public void setBasketName(String basketName) {
        this.basketName = basketName;
    }

    public String getBasketName() {
        return basketName;
    }


}
