package com.codetinkerhack;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by evgeniys on 9/05/2017.
 */
public class RPNInterpreter {

    private MementoStack<Double> stack = new MementoStack<>();

    public List<Double> interpretInput(String input) throws InsufficientParametersError, InputParseError {
        StringTokenizer st = new StringTokenizer(input);
        Integer position = 1;
        while (st.hasMoreTokens()) {
            interpretSingle(st.nextToken(), position);
            position += 2;
        }

        return getStack();
    }

    public List<Double> getStack() {
        return stack.getStack();
    }

    private void interpretSingle(String singleInput, Integer position) throws InsufficientParametersError, InputParseError {
        Double operand1;
        Double operand2;
        String operator = "";

        try {
            switch (singleInput) {
                case "+":
                    operator = "+";
                    stack.commit();
                    operand1 = stack.pop();
                    operand2 = stack.pop();
                    stack.push(operand1 + operand2);
                    break;
                case "-":
                    operator = "-";
                    stack.commit();
                    operand1 = stack.pop();
                    operand2 = stack.pop();
                    stack.push(operand2 - operand1);
                    break;
                case "*":
                    operator = "*";
                    stack.commit();
                    operand1 = stack.pop();
                    operand2 = stack.pop();
                    stack.push(operand1 * operand2);
                    break;
                case "/":
                    operator = "/";
                    stack.commit();
                    operand1 = stack.pop();
                    operand2 = stack.pop();
                    stack.push(operand2 / operand1);
                    break;
                case "sqrt":
                    operator = "sqrt";
                    stack.commit();
                    operand1 = stack.pop();
                    stack.push(Math.sqrt(operand1));
                    break;
                case "undo":
                    operator = "undo";
                    stack.undo();
                    break;
                case "clear":
                    stack.commit();
                    stack.clear();
                    break;
                default:
                    stack.commit();
                    stack.push(Double.valueOf(singleInput));

            }
        } catch (NoSuchElementException e) {
            stack.undo();
            throw new InsufficientParametersError(getStackAsString(), operator, position, e);
        } catch (NumberFormatException e) {
            stack.undo();
            throw new InputParseError(getStackAsString(), operator, position, e);
        }
    }


    public String getStackAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        formatter.setMaximumFractionDigits(10);
        formatter.setMinimumFractionDigits(0);
        formatter.setRoundingMode(RoundingMode.DOWN); // Worked out it has to be rounded down as per example 2

        LinkedList<Double> s = stack.getStack();

        // Reverse print the stack to make it human readable and format decimals
        while (s.size() > 0) {
            Double d = s.removeLast();
            stringBuilder.append(formatter.format(d));
            if (s.size() > 0) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

}
