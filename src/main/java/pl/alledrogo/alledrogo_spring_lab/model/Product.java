package pl.alledrogo.alledrogo_spring_lab.model;

import javax.persistence.*;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;
    private String productDescription;
    private Double productPrice;

    @ManyToOne
    Basket basketAlt;

    public Product() {
    }

    public Product(String name, String description, Double price){
        this.productName = name;
        this.productDescription = description;
        this.productPrice = price;
    }

    public Basket getBasketAlt() {
        return basketAlt;
    }

    public void setBasketAlt(Basket basketAlt) {
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
