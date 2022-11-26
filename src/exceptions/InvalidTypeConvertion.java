package src.exceptions;

public class InvalidTypeConvertion extends LanguageException {

    private static final String DEFAULT_MSG = "Cannot convert from type %s to %s.";

    public InvalidTypeConvertion(String from, String to){
        super(String.format(DEFAULT_MSG, from, to));
    }

    public InvalidTypeConvertion(String from, String to, String in){
        super(String.format(DEFAULT_MSG + " Error in %s", from, to, in));
    }
}
