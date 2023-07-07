package programming_2.bnf_set_calculator.models;

public class IncorrectSetOperationSyntaxException extends Exception {
    public IncorrectSetOperationSyntaxException() {
    }

    public IncorrectSetOperationSyntaxException(String message) {
        super(message);
    }
}
