package pl.alledrogo.alledrogo_spring_lab.modelJPA;

import javax.persistence.*;


@Entity
public class ProductAlt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;
    private String productDescription;
    private Double productPrice;

    @ManyToOne
    BasketAlt basketAlt;

    public ProductAlt() {
    }

    public ProductAlt(String name, String description, Double price){
        this.productName = name;
        this.productDescription = description;
        this.productPrice = price;
    }

    public BasketAlt getBasketAlt() {
        return basketAlt;
    }

    public void setBasketAlt(BasketAlt basketAlt) {
        this.basketAlt = basketAlt;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

}
