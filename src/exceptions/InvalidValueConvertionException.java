package src.exceptions;

public class InvalidValueConvertionException extends LanguageException {
    private static final String DEFAULT_MSG = "Invalid type: %s.";

    public InvalidValueConvertionException(String type){
        super(String.format(DEFAULT_MSG, type));
    }
}
