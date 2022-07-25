package pl.alledrogo.alledrogo_spring_lab.exceptions;

public class UserNotFoundException extends RuntimeException{

        public UserNotFoundException(String message) {
            super(message);
        }
    }

