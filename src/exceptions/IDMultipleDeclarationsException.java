package src.exceptions;

public class IDMultipleDeclarationsException extends LanguageException {

    private static final String DEFAULT_MSG = "%s declared multiple times.";

    public IDMultipleDeclarationsException(String id){
        super(String.format(DEFAULT_MSG, id));
    }
}
