package src.exceptions;

public class IDMultipleDeclarations extends RuntimeException {

    private static final String DEFAULT_MSG = "%s declared multiple times.";

    public IDMultipleDeclarations(String id){
        super(String.format(DEFAULT_MSG, id));
    }
}
