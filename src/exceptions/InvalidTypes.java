package src.exceptions;

public class InvalidTypes extends RuntimeException {
    private static final String DEFAULT_MSG = "Invalid type: %s.";

    public InvalidTypes(String type){
        super(String.format(DEFAULT_MSG, type));
    }
}
