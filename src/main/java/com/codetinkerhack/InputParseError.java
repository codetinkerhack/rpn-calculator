package com.codetinkerhack;

/**
 * Created by evgeniys on 10/05/2017.
 */
public class InputParseError extends RPNError {

    public InputParseError(String stackAsString, String operator, Integer position, Exception e) {
        super(e, operator, position, stackAsString);
    }

    @Override
    public String toString() {
        return String.format("operator %s (position: %d): error during parsing input", operator, position);
    }
}
