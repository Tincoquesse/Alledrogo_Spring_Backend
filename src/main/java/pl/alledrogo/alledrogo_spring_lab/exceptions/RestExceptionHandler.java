package pl.alledrogo.alledrogo_spring_lab.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = BasketNotFoundException.class)
    public ResponseEntity<String> handleBasketNotFound(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFound(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
