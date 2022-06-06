package pl.alledrogo.alledrogo_spring_lab.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;


public class Order {

    private Map<String,Product> productMap;
    private String shipmentAddress;

    @JsonCreator Order(
            @JsonProperty("productMap")Map<String, Product> productMap,
            @JsonProperty("shipmentAddress") String shipmentAddress) {

        this.productMap = productMap;
        this.shipmentAddress = shipmentAddress;
    }
}
