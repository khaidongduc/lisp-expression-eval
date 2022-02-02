package testcases;

import assignment.LispToken;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class LispTokenTest {
    public static double DELTA = 1e-7;

    public static class ConstructorTest {

        @Test
        public void Constructor_OperatorWithValidChar_Successfully() {
            try {
                LispToken ignored = new LispToken('+');
            } catch (Exception e) {
                fail(Arrays.toString(e.getStackTrace()));
            }
        }

        @Test
        public void Constructor_OperatorWithInvalidChar_Successfully() {
            try {
                LispToken operator = new LispToken('8');
            } catch (Exception e) {
                fail(Arrays.toString(e.getStackTrace()));
            }
        }

        @Test
        public void Constructor_Operand_Successfully() {
            try {
                LispToken operator = new LispToken(7.0);
            } catch (Exception e) {
                fail(Arrays.toString(e.getStackTrace()));
            }
        }

    }

    public static class ApplyOperatorTest {

        @Test
        public void ApplyOperator_SumOperator_Successfully() {
            try {
                LispToken operator = new LispToken('+');
                Double result = operator.applyOperator(1.0, 2.0);
                assertEquals("1.0 + 2.0 = 3.0", 3.0, result, DELTA);
            } catch (Exception ignored) {
                fail();
            }
        }

        @Test
        public void ApplyOperator_SubtractOperator_Successfully() {
            try {
                LispToken operator = new LispToken('-');
                Double result = operator.applyOperator(1.0, 2.0);
                assertEquals("1.0 - 2.0 = 3.0", -1.0, result, DELTA);
            } catch (Exception ignored) {
                fail();
            }
        }

        @Test
        public void ApplyOperator_ProductOperator_Successfully() {
            try {
                LispToken operator = new LispToken('*');
                Double result = operator.applyOperator(2.0, 2.0);
                assertEquals("2.0 * 2.0 = 4.0", 4.0, result, DELTA);
            } catch (Exception ignored) {
                fail();
            }
        }

        @Test
        public void ApplyOperator_DivideOperator_Successfully() {
            try {
                LispToken operator = new LispToken('/');
                Double result = operator.applyOperator(1.0, 2.0);
                assertEquals("1.0 / 2.0 = 0.5", 0.5, result, DELTA);
            } catch (Exception ignored) {
                fail();
            }
        }

        @Test
        public void ApplyOperator_InvalidOperator_Successfully() {
            try {
                LispToken operator = new LispToken('8');
                Double result = operator.applyOperator(1.0, 2.0);
                assertNull(result);
            } catch (Exception ignored) {
                fail();
            }
        }

    }

    public static class GetIdentityTest {

        @Test
        public void GetIdentity_SumAndSubtractOperator_ReturnZero() {
            try {
                LispToken sum = new LispToken('+');
                LispToken subtract = new LispToken('-');

                assertEquals(sum.getIdentity(), 0.0, DELTA);
                assertEquals(subtract.getIdentity(), 0.0, DELTA);
            } catch (Exception e) {
                fail(Arrays.toString(e.getStackTrace()));
            }
        }

        @Test
        public void GetIdentity_ProductAndDivideOperator_ReturnOne() {
            try {
                LispToken product = new LispToken('/');
                LispToken divide = new LispToken('*');

                assertEquals(product.getIdentity(), 1.0, DELTA);
                assertEquals(divide.getIdentity(), 1.0, DELTA);
            } catch (Exception e) {
                fail(Arrays.toString(e.getStackTrace()));
            }
        }

        @Test
        public void GetIdentity_InvalidOperator_ReturnNull() {
            try {
                LispToken product = new LispToken('8');
                assertNull(product.getIdentity());
            } catch (Exception e) {
                fail(Arrays.toString(e.getStackTrace()));
            }
        }

        @Test
        public void GetIdentity_InvalidOperand_ReturnNull() {
            try {
                LispToken product = new LispToken(8.0);
                assertNull(product.getIdentity());
            } catch (Exception e) {
                fail(Arrays.toString(e.getStackTrace()));
            }
        }

    }

    public static class TakesZeroOperandsTest {
        @Test
        public void TakeZeroOperands_SumAndProductOperator_ReturnTrue() {
            try {
                LispToken sum = new LispToken('+');
                LispToken product = new LispToken('*');
                assertTrue(sum.takesZeroOperands());
                assertTrue(product.takesZeroOperands());
            } catch (Exception e) {
                fail(Arrays.toString(e.getStackTrace()));
            }
        }

        @Test
        public void TakeZeroOperands_SubtractAndDivideOperator_ReturnOne() {
            try {
                LispToken divide = new LispToken('/');
                LispToken subtract = new LispToken('-');
                assertFalse(subtract.takesZeroOperands());
                assertFalse(divide.takesZeroOperands());
            } catch (Exception e) {
                fail(Arrays.toString(e.getStackTrace()));
            }
        }

        @Test
        public void TakeZeroOperands_Operand_ReturnFalse() {
            try {
                LispToken operand = new LispToken(8.0);
                assertFalse(operand.takesZeroOperands());
            } catch (Exception e) {
                fail(Arrays.toString(e.getStackTrace()));
            }
        }

    }

}