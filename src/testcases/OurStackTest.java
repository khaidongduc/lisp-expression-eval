package testcases;

import assignment.OurStack;
import assignment.StackInterface;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.EmptyStackException;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class OurStackTest {

    public static class ConstructorTest {
        @Test
        public void Constructor_GenericUseCase() {
            try {
                StackInterface<String> stack = new OurStack<>();
            } catch (Exception ignored) {
                fail();
            }
        }
    }

    public static class PushTest {
        @Test
        public void Push_NonNullElem_Successfully() {
            try {
                StackInterface<String> stack = new OurStack<>();
                stack.push("Hello");

                assertFalse("Stack should not be empty", stack.isEmpty());
                assertEquals("\"Hello\" should be on top of the stack", "Hello", stack.peek());
            } catch (Exception ignored) {
                fail();
            }
        }

        @Test
        public void Push_NullElem_Failed() {
            try {
                StackInterface<String> stack = new OurStack<>();
                stack.push(null);

                assertTrue("Stack should be empty", stack.isEmpty());
            } catch (Exception ignored) {
                fail();
            }
        }

        @Test
        public void Push_PushSeveralElems_Successfully() {
            try {
                StackInterface<Integer> stack = new OurStack<>();
                int batchSize = 100;
                for (int i = 0; i < batchSize; ++i) stack.push(i);

                assertFalse("Stack should not be empty", stack.isEmpty());
            } catch (Exception ignored) {
                fail();
            }
        }
    }

    public static class PopTest{
        @Test
        public void Pop_EmptyStack_ThrowEmptyStackException(){
            try {
                StackInterface<String> stack = new OurStack<>();
                String value = stack.pop();
            } catch (EmptyStackException ignored){
                return;
            }
            fail();
        }

        @Test
        public void Pop_NonEmptyStack_PopSuccessfully(){
            try {
                StackInterface<String> stack = new OurStack<>();
                stack.push("Hello");

                String value = stack.pop();
                assertEquals("Stack top value should be \"Hello\"", "Hello", value);
                assertTrue("Stack should be empty", stack.isEmpty());
            } catch (Exception ignored){
                fail();
            }
        }

    }

    public static class PeekTest{
        @Test
        public void Peek_EmptyStack_ThrowEmptyStackException(){
            try {
                StackInterface<String> stack = new OurStack<>();
                String value = stack.peek();
            } catch (EmptyStackException ignored){
                return;
            }
            fail();
        }

        @Test
        public void Peek_NonEmptyStack_PeekSuccessfully(){
            try {
                StackInterface<String> stack = new OurStack<>();
                stack.push("Hello");

                String value = stack.peek();
                assertEquals("Stack top value should be \"Hello\"", "Hello", value);
                assertFalse("Stack should not be empty", stack.isEmpty());
            } catch (Exception ignored){
                fail();
            }
        }

    }

    public static class IsEmptyTest{
        @Test
        public void IsEmpty_EmptyStack_ReturnTrue(){
            try {
                StackInterface<String> stack = new OurStack<>();
                assertTrue("Stack should be empty", stack.isEmpty());
            } catch (Exception ignored){
                fail();
            }
        }

        @Test
        public void IsEmpty_NonEmptyStack_ReturnFalse(){
            try {
                StackInterface<String> stack = new OurStack<>();
                stack.push("Hello");

                assertFalse("Stack should not be empty", stack.isEmpty());
            } catch (Exception ignored){
                fail();
            }
        }

    }

    public static class ClearTest{
        @Test
        public void Clear_GenericUseCase(){
            try {
                StackInterface<Integer> stack = new OurStack<>();
                int batchSize = 100;
                for (int i = 0; i < batchSize; ++i) stack.push(i);
                assertFalse("Stack should not be empty", stack.isEmpty());
                stack.clear();
                assertTrue("Stack should be empty", stack.isEmpty());
            } catch (Exception ignored) {
                fail();
            }
        }
    }

}