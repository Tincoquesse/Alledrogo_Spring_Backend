package pl.alledrogo.alledrogo_spring_lab.exceptions;

public class ProductAlreadyExistException extends RuntimeException{

    public ProductAlreadyExistException(String message) {
        super(message);
    }
}
