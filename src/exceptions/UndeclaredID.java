package src.exceptions;

public class UndeclaredID extends LanguageException {
    private static final String DEFAULT_MSG = "Identifier %s undeclared.";

    public UndeclaredID(String id){
        super(String.format(DEFAULT_MSG, id));
    }
}
