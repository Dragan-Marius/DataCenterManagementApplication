package main.java.exception;

public class LocationException extends Exception {
    public LocationException(){
        super("LocationException: Country is missing.");
    }
    public LocationException(String message) {
        super(message);
    }
}
