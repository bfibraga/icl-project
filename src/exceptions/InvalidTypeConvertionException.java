package src.exceptions;

import src.type.Type;

public class InvalidTypeConvertionException extends LanguageException {

    private static final String DEFAULT_MSG = "Cannot convert from type %s to %s.";

    public InvalidTypeConvertionException(String from, String to){
        super(String.format(DEFAULT_MSG, from, to));
    }

    public InvalidTypeConvertionException(Type from, Type to){
        super(String.format(DEFAULT_MSG, from, to));
    }

    public InvalidTypeConvertionException(String from, String to, String in){
        super(String.format(DEFAULT_MSG + " Error in %s", from, to, in));
    }

    public InvalidTypeConvertionException(Type from, Type to, String in){
        super(String.format(DEFAULT_MSG + " Error in %s", from, to, in));
    }
}
