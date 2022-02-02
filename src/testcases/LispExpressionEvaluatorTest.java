package testcases;

import assignment.LispExpressionEvaluator;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;


@RunWith(Enclosed.class)
public class LispExpressionEvaluatorTest {

    public static double DELTA = 1e-7;

    public static class EvaluateTest {

        @Test
        public void Evaluate_CompleteLispExpressions_Successfully() {
            try {
                String[] tests = {
                        "(+ 1 3)",
                        "(- 1)",
                        "(+)",
                        "(*)",
                        "(- 1 2)",
                        "(+ (- 1) (* 3 3 4) (/ 3 2 3) (* 4 4))"
                };
                ArrayList<String> mes = new ArrayList<>();
                ArrayList<Double> results = new ArrayList<>();
                for (String test : tests) {
                    double res = LispExpressionEvaluator.evaluate(test, mes);
                    results.add(res);
                }
                assertArrayEquals(
                        new Double[]{4.0, -1.0, 0.0, 1.0, -1.0, 51.5},
                        results.toArray());

            } catch (Exception e) {
                fail(Arrays.toString(e.getStackTrace()));
            }
        }

        @Test
        public void Evaluate_FoundOperatorWhenWeShouldNot_ReturnTheErrorMessageAccordingly() {
            try {
                ArrayList<String> mes = new ArrayList<>();
                String expression = "+ (+ 1 2) (- 1 3)";
                double res = LispExpressionEvaluator.evaluate(expression, mes);
                assertEquals(0.0, res, DELTA);
                String errorMessage = mes.get(0);
                errorMessage = errorMessage.strip().split("\n")[2];
                assertEquals("found an operator when we should not", errorMessage);
            } catch (Exception e) {
                fail(Arrays.toString(e.getStackTrace()));
            }
        }

        @Test
        public void Evaluate_OperatorDivideWithNoOperand_ReturnTheErrorMessageAccordingly() {
            try {
                ArrayList<String> mes = new ArrayList<>();
                String expression = "(/)";
                double res = LispExpressionEvaluator.evaluate(expression, mes);
                assertEquals(0.0, res, DELTA);
                String errorMessage = mes.get(0);
                errorMessage = errorMessage.strip().split("\n")[2];
                assertEquals("operator / requires at least one operand", errorMessage);
            } catch (Exception e) {
                fail(Arrays.toString(e.getStackTrace()));
            }
        }

        @Test
        public void Evaluate_OperatorSubtractWithNoOperand_ReturnTheErrorMessageAccordingly() {
            try {
                ArrayList<String> mes = new ArrayList<>();
                String expression = "(-)";
                double res = LispExpressionEvaluator.evaluate(expression, mes);
                assertEquals(0.0, res, DELTA);
                String errorMessage = mes.get(0);
                errorMessage = errorMessage.strip().split("\n")[2];
                assertEquals("operator - requires at least one operand", errorMessage);
            } catch (Exception e) {
                fail(Arrays.toString(e.getStackTrace()));
            }
        }

        @Test
        public void Evaluate_MismatchParenthesis_ReturnTheErrorMessageAccordingly() {
            try {
                ArrayList<String> mes = new ArrayList<>();
                String expression = "(+))";
                double res = LispExpressionEvaluator.evaluate(expression, mes);
                assertEquals(0.0, res, DELTA);
                String errorMessage = mes.get(0);
                errorMessage = errorMessage.strip().split("\n")[2];
                assertEquals("mismatched )", errorMessage);
            } catch (Exception e) {
                fail(Arrays.toString(e.getStackTrace()));
            }
        }

        @Test
        public void Evaluate_MultipleExpressions_ReturnTheErrorMessageAccordingly() {
            try {
                ArrayList<String> mes = new ArrayList<>();
                String expression = "(+)(*)";
                double res = LispExpressionEvaluator.evaluate(expression, mes);
                assertEquals(0.0, res, DELTA);
                String errorMessage = mes.get(0);
                errorMessage = errorMessage.strip().split("\n")[2];
                assertEquals("incomplete expression / multiple expressions", errorMessage);
            } catch (Exception e) {
                fail(Arrays.toString(e.getStackTrace()));
            }
        }

        @Test
        public void Evaluate_FoundInvalidCharacters_ReturnTheErrorMessageAccordingly() {
            try {
                ArrayList<String> mes = new ArrayList<>();
                String expression = "(+)&#$(*)";
                double res = LispExpressionEvaluator.evaluate(expression, mes);
                assertEquals(0.0, res, DELTA);
                String errorMessage = mes.get(0);
                errorMessage = errorMessage.strip().split("\n")[2];
                assertEquals("found an illegal character", errorMessage);
            } catch (Exception e) {
                fail(Arrays.toString(e.getStackTrace()));
            }
        }

    }
}