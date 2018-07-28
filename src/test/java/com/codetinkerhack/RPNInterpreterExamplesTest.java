package com.codetinkerhack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by evgeniys on 10/05/2017.
 */
public class RPNInterpreterExamplesTest {
    RPNInterpreter ni;

    @Before
    public void init() {
        ni = new RPNInterpreter();
    }

    @Test
    public void exampleTest1() throws Exception {

        ni.interpretInput("5 2");
        assertEquals("5 2", ni.getStackAsString());
    }

    @Test
    public void exampleTest2() throws Exception {

        ni.interpretInput("2 sqrt");
        assertEquals("1.4142135623", ni.getStackAsString());

        ni.interpretInput("clear 9 sqrt");
        assertEquals("3", ni.getStackAsString());
    }

    @Test
    public void exampleTest3() throws Exception {
        ni.interpretInput("5 2 -");
        assertEquals("3", ni.getStackAsString());

        ni.interpretInput("3 -");
        assertEquals("0", ni.getStackAsString());

        ni.interpretInput("clear");
        assertEquals("", ni.getStackAsString());
    }

    @Test
    public void exampleTest4() throws Exception {
        ni.interpretInput("5 4 3 2");
        assertEquals("5 4 3 2", ni.getStackAsString());

        ni.interpretInput("undo undo *");
        assertEquals("20", ni.getStackAsString());

        ni.interpretInput("5 *");
        assertEquals("100", ni.getStackAsString());

        ni.interpretInput("undo");
        assertEquals("20 5", ni.getStackAsString());
    }

    @Test
    public void exampleTest5() throws Exception {
        ni.interpretInput("7 12 2 /");
        assertEquals("7 6", ni.getStackAsString());

        ni.interpretInput("*");
        assertEquals("42", ni.getStackAsString());

        ni.interpretInput("4 /");
        assertEquals("10.5", ni.getStackAsString());
    }

    @Test
    public void exampleTest6() throws Exception {
        ni.interpretInput("1 2 3 4 5");
        assertEquals("1 2 3 4 5", ni.getStackAsString());

        ni.interpretInput("*");
        assertEquals("1 2 3 20", ni.getStackAsString());

        ni.interpretInput("clear 3 4 -");
        assertEquals("-1", ni.getStackAsString());
    }

    @Test
    public void exampleTest7() throws Exception {
        ni.interpretInput("1 2 3 4 5");
        assertEquals("1 2 3 4 5", ni.getStackAsString());

        ni.interpretInput("* * * *");
        assertEquals("120", ni.getStackAsString());
    }

    @Test
    public void exampleTest8() throws Exception {
        try {
            ni.interpretInput("1 2 3 * 5 + * * 6 5");
        } catch (RPNError e) {
            assertEquals("operator * (position: 15): insufficient parameters", e.getMessage());
        }
        assertEquals("11", ni.getStackAsString());
    }
}
