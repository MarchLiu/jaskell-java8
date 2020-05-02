package jaskell.parsec;

/**
 * Created by march on 16/9/11.
 */
public class ParsecException extends RuntimeException {
    private final String message;
    private final Object status;

    public ParsecException(Object status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Object getStatus() {
        return status;
    }
}
