package pl.alledrogo.alledrogo_spring_lab.alledrogo_model;

import javax.persistence.*;


@Entity
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "product_sequence")
    private Long id;

    private String productName;
    private String productDescription;
    private Double productPrice;

    @ManyToOne
//    @JoinColumn(nullable = false,
//                name = "basket_id")
    private Basket basket;

    public Product() {
    }

    public Product(String name, String description, Double price){
        this.productName = name;
        this.productDescription = description;
        this.productPrice = price;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
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

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice=" + productPrice +
//                ", basket=" + basket +
                '}';
    }
}
