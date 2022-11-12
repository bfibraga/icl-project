package src.exceptions;

public class InvalidTypeConvertion extends RuntimeException {

    private static final String DEFAULT_MSG = "Cannot convert from %s to %s.";

    public InvalidTypeConvertion(String from, String to){
        super(String.format(DEFAULT_MSG, from, to));
    }
}
