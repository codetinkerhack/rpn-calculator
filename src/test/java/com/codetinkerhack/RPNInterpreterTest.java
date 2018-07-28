package com.codetinkerhack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by evgeniys on 9/05/2017.
 */
public class RPNInterpreterTest {
    RPNInterpreter ni;

    @Before
    public void init() {
        ni = new RPNInterpreter();
    }

    @Test
    public void shouldPushSingleElement() throws Exception {

        ni.interpretInput("1");
        Double[] expected = new Double[]{1D};
        assertArrayEquals(expected, ni.getStack().toArray());
    }

    @Test
    public void shouldPushTwoElement() throws Exception {

        ni.interpretInput("1 2");
        Double[] expected = new Double[]{2D, 1D};
        assertArrayEquals(expected, ni.getStack().toArray());
    }


    @Test
    public void shouldPushTwoElementAndSum() throws Exception {

        ni.interpretInput("1 2 +");
        Double[] expected = new Double[]{3D};
        assertArrayEquals(expected, ni.getStack().toArray());
    }

    @Test
    public void shouldPushTwoElementAndDiff() throws Exception {

        ni.interpretInput("1 2 -");
        Double[] expected = new Double[]{-1D};
        assertArrayEquals(expected, ni.getStack().toArray());
    }

    @Test
    public void shouldPushTwoElementAndMultiply() throws Exception {

        ni.interpretInput("2 3 *");
        Double[] expected = new Double[]{6D};
        assertArrayEquals(expected, ni.getStack().toArray());
    }

    @Test
    public void shouldPushTwoElementAndDivide() throws Exception {

        ni.interpretInput("1 2 /");
        Double[] expected = new Double[]{0.5D};
        assertArrayEquals(expected, ni.getStack().toArray());
    }

    @Test
    public void shouldPerformSquareRoot() throws Exception {

        ni.interpretInput("2 sqrt");
        Double[] expected = new Double[]{1.4142135623730951};
        assertArrayEquals(expected, ni.getStack().toArray());
    }

    @Test
    public void shouldPerformSquareRoot1() throws Exception {

        ni.interpretInput("9 sqrt");
        Double[] expected = new Double[]{3D};
        assertArrayEquals(expected, ni.getStack().toArray());
    }

    @Test
    public void shouldUndoOperation() throws Exception {

        ni.interpretInput("1 2 /");
        Double[] expected = new Double[]{2D, 1D};

        ni.interpretInput("undo");

        assertArrayEquals(expected, ni.getStack().toArray());
    }

    @Test
    public void shouldClearStack() throws Exception {

        ni.interpretInput("1 2 /");
        Double[] expected = new Double[]{};

        ni.interpretInput("clear");

        assertArrayEquals(expected, ni.getStack().toArray());
    }

    @Test
    public void shouldInterpretMultipleInput() throws Exception {

        ni.interpretInput("1 2 3 + + undo");
        Double[] expected = new Double[]{5D, 1D};

        assertArrayEquals(expected, ni.getStack().toArray());
    }

    @Test(expected = InputParseError.class)
    public void shouldThrowParseError() throws Exception {

        ni.interpretInput("1 2 3 A");
    }

    @Test(expected = InsufficientParametersError.class)
    public void shouldThrowInsufficientParametersError() throws Exception {

        ni.interpretInput("1 2 3 + + +");
    }

    @Test
    public void shouldThrowInsufficientParametersError1() throws Exception {
        try {
            ni.interpretInput("1 2 3 + + +");
        } catch (InsufficientParametersError e) {
            assertEquals(new Integer(11), e.getPosition());
            assertEquals("+", e.getOperator());
        }
    }

}