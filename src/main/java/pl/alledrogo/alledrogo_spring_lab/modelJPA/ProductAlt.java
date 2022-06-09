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

//    @ManyToOne
//    BasketAlt basketAlt;


    public ProductAlt() {
    }

    public ProductAlt(String name, String description, Double price){

        this.productName = name;
        this.productDescription = description;
        this.productPrice = price;
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
