package src.exceptions;

public class UndeclaredIDException extends LanguageException {
    private static final String DEFAULT_MSG = "Identifier %s undeclared.";

    public UndeclaredIDException(String id){
        super(String.format(DEFAULT_MSG, id));
    }
}
