package pl.alledrogo.alledrogo_spring_lab.modelJPA;


import javax.persistence.*;
import java.util.List;


@Entity
public class BasketAlt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    List<ProductAlt> productAltList;

    public BasketAlt() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductAlt> getProductAltList() {
        return productAltList;
    }

    public void AddProductToBasket(ProductAlt productAlt) {
        productAltList.add(productAlt);
    }
}
