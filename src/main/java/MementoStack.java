import java.util.LinkedList;

/**
 * Created by evgeniys on 9/05/2017.
 * <p>
 * Unlimited undo Stack of Stacks
 * Can be changed to maintain rolling window of N operations
 */
public class MementoStack<T> {

    private LinkedList<LinkedList<T>> stackOfStacks = new LinkedList<>();
    private LinkedList<T> currentStack;

    public MementoStack() {
        currentStack = new LinkedList<>();
        stackOfStacks.push(currentStack);
    }

    public T pop() {
        return currentStack.pop();
    }

    public void push(T v) {
        currentStack.push(v);
    }

    public void commit() {
        LinkedList<T> newStack = new LinkedList<>(currentStack);
        stackOfStacks.push(newStack);
    }

    public void undo() {
        currentStack = stackOfStacks.pop();
    }

    public void clear() {
        currentStack.clear();
    }

    public LinkedList<T> getStack() {
        return new LinkedList<>(currentStack);
    }
}
