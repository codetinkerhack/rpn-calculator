package com.codetinkerhack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by evgeniys on 10/05/2017.
 */
public class MementoStackTest {

    MementoStack<Double> mementoStack;

    @Before
    public void init() {
        mementoStack = new MementoStack<>();
    }

    @Test
    public void shouldPopElement() throws Exception {
        mementoStack.push(1D);
        mementoStack.push(2D);
        Double v = mementoStack.pop();

        assertEquals(new Double(2D), v);

        mementoStack.getStack();

        Double[] expected = new Double[]{1D};

        assertArrayEquals(expected, mementoStack.getStack().toArray());
    }

    @Test
    public void shouldPushElement() throws Exception {
        mementoStack.push(1D);
        mementoStack.push(2D);
        mementoStack.getStack();

        Double[] expected = new Double[]{2D, 1D};

        assertArrayEquals(expected, mementoStack.getStack().toArray());
    }

    @Test
    public void shouldCommitAndUndo() throws Exception {
        mementoStack.push(1D);
        mementoStack.push(2D);
        mementoStack.commit();
        mementoStack.getStack();

        Double[] expected = new Double[]{2D, 1D};
        assertArrayEquals(expected, mementoStack.getStack().toArray());

        mementoStack.push(3D);
        mementoStack.push(4D);

        mementoStack.getStack();

        Double[] expected1 = new Double[]{4D, 3D, 2D, 1D};
        assertArrayEquals(expected1, mementoStack.getStack().toArray());

        mementoStack.undo();
        mementoStack.getStack();

        assertArrayEquals(expected, mementoStack.getStack().toArray());

    }


    @Test
    public void shouldClearStack() throws Exception {
        mementoStack.push(1D);
        mementoStack.push(2D);
        mementoStack.clear();
        mementoStack.getStack();

        Double[] expected = new Double[]{};

        assertArrayEquals(expected, mementoStack.getStack().toArray());
    }

}