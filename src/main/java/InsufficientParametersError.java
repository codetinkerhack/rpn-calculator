/**
 * Created by evgeniys on 9/05/2017.
 */
public class InsufficientParametersError extends RPNError {

    public InsufficientParametersError(String stackAsString, String operator, Integer position, Exception e) {
        super(e, operator, position, stackAsString);
    }

    @Override
    public String toString() {
        return String.format("operator %s (position: %d): insufficient parameters", operator, position);
    }
}
