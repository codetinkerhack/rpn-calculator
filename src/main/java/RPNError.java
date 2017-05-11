/**
 * Created by evgeniys on 10/05/2017.
 */
public class RPNError extends Exception {

    protected final String stackAsString;
    protected final String operator;
    protected final Integer position;

    public RPNError(Exception e, String operator, Integer position, String stackAsString) {
        super(e);
        this.operator = operator;
        this.position = position;
        this.stackAsString = stackAsString;
    }

    public String getOperator() {
        return operator;
    }

    public Integer getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return String.format("operator %s (position: %d): error: ", operator, position, getMessage());
    }

    @Override
    public String getMessage() {
        return toString();
    }
}
