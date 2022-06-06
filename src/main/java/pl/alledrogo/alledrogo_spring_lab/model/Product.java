package pl.alledrogo.alledrogo_spring_lab.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    private final String name;
    private final String description;
    private final double price;

//    public Product(String name, String description, double price) {
//        this.name = name;
//        this.description = description;
//        this.price = price;
//    }
    @JsonCreator
    public Product(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("price") double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
