package pl.alledrogo.alledrogo_spring_lab.exceptions;

public class BasketNotFoundException extends RuntimeException {

    public BasketNotFoundException(String message) {
        super(message);
    }
}
