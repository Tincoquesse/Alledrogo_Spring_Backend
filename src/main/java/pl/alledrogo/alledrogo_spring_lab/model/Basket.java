package pl.alledrogo.alledrogo_spring_lab.model;


import javax.persistence.*;
import java.util.List;


@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    List<Product> productAltList;

    public Basket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProductAltList() {
        return productAltList;
    }

    public void AddProductToBasket(Product productAlt) {
        productAltList.add(productAlt);
    }
}
