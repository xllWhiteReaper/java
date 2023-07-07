package programming_2.bnf_set_calculator.models;

public class IncorrectSetSyntaxException extends Exception {
    public IncorrectSetSyntaxException() {
    }

    public IncorrectSetSyntaxException(String message) {
        super(message);
    }
}
