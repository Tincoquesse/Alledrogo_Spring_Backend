package pl.alledrogo.alledrogo_spring_lab.model;

public class OrderCartDTO {

    private String username;
    private String firstAndLastName;
    private String basketName;
    private String street;
    private String postalCode;
    private String city;
    private Integer phoneNumber;


    public OrderCartDTO(String username, String firstAndLastName,
                        String basketName, String street, String postalCode,
                        String city, Integer phoneNumber) {

        this.username = username;
        this.firstAndLastName = firstAndLastName;
        this.basketName = basketName;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstAndLastName() {
        return firstAndLastName;
    }

    public void setFirstAndLastName(String firstAndLastName) {
        this.firstAndLastName = firstAndLastName;
    }

    public String getBasketName() {
        return basketName;
    }

    public void setBasketName(String basketName) {
        this.basketName = basketName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
