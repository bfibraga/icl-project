package src.exceptions;

public class WrongNumberFuntionParametersException extends LanguageException {

    private static final String DEFAULT_MSG = "Invalid number of parameters, %d != %d.";

    public WrongNumberFuntionParametersException(int expectedSize, int trueSize) {
        super(String.format(DEFAULT_MSG, expectedSize, trueSize));
    }
}
