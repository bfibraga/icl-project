package src.exceptions;

public class UndeclaredID extends RuntimeException {
    private static final String DEFAULT_MSG = "%s undeclared.";

    public UndeclaredID(String id){
        super(String.format(DEFAULT_MSG, id));
    }
}
