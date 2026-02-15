package main.java;

public class UserException extends Exception {
    public UserException(){
        super("UserException: Name and role can't be empty.");
    }
    public UserException(String message) {
        super(message);
    }
}
