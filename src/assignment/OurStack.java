package assignment;
/*
 * I affirm that I have carried out my academic endeavors with full academic honesty.
 * [Signed, Khai Dong]
 */
import java.util.Stack;

/**
 * A class of stacks.
 *
 * @author Frank M. Carrano
 * @author Khai Dong modified
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

    // smoke test - basic
    public static void main(String[] args) {
        OurStack<String> stack = new OurStack<String>();
        stack.push("A");

        System.out.printf("peek, expected: A, Actual: %s%n", stack.peek());
        System.out.printf("isEmpty, expected: false, Actual: %s%n", stack.isEmpty());
        System.out.printf("peek, expected: A, Actual: %s%n", stack.pop());
        System.out.printf("isEmpty, expected: true, Actual: %s%n", stack.isEmpty());
    }

} // end OurStack