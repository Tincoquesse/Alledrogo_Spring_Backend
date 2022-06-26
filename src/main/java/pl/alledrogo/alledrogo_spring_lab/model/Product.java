package pl.alledrogo.alledrogo_spring_lab.model;

import javax.persistence.*;
import java.util.List;


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
    private String imageURL;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Basket> baskets;

    public Product() {
    }

    public Product(String name, String description, Double price, String pictureURL){
        this.productName = name;
        this.productDescription = description;
        this.productPrice = price;
        this.imageURL = pictureURL;
    }

    public List<Basket> getBaskets() {
        return baskets;
    }

    public void addBasket(Basket basket) {
        this.baskets.add(basket);
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String pictureURL) {
        this.imageURL = pictureURL;
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
