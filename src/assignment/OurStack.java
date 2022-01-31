package assignment;

import java.util.Stack;

/**
 * A class of stacks.
 *
 * @author Frank M. Carrano
 * @version 5.0
 */
public class OurStack<T> implements StackInterface<T> {
    private final Stack<T> theStack;

    // Implement the constructor with no parameter, push, peek, pop, isEmpty and clear
    // by using the private Stack theStack of type java.util.Stack

    /**
     * constructor
     * O(1)
     */
    public OurStack() {
        this.theStack = new Stack<>();
    }

    /**
     * O(1)
     * ignore null entry
     *
     * @param newEntry An object to be added to the stack.
     */
    @Override
    public void push(T newEntry) {
        if (newEntry == null)
            return;
        this.theStack.push(newEntry);
    }

    /**
     * O(1)
     * give the top element on the stack and remove it from the stack
     *
     * @return the top element (have been removed) of the stack
     */
    @Override
    public T pop() {
        return this.theStack.pop();
    }

    /**
     * O(1)
     * give the top element on the stack
     *
     * @return the top element of the stack
     */
    @Override
    public T peek() {
        return this.theStack.peek();
    }

    /**
     * O(1)
     *
     * @return check if the stack is empty
     */
    @Override
    public boolean isEmpty() {
        return this.theStack.isEmpty();
    }

    /**
     * clear the stack
     */
    @Override
    public void clear() {
        this.theStack.clear();
    }

} // end OurStack