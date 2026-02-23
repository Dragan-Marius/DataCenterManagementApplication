package main.java.exception;

public class MissingIpAddressException extends Exception {
    public MissingIpAddressException(){
        super("MissingIpAddressException: Server IP Address was not provided.");
    }
    public MissingIpAddressException(String message) {
        super(message);
    }
}
