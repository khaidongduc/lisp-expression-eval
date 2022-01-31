package assignment;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class evaluates a simple arithmetic Lisp expression of numeric values.
 *
 * @author Charles Hoot
 * @author Jesse Grabowski
 * @author Joseph Erickson
 * @author Zeynep Orhan modified
 * @author Khai Dong modified
 * @version 5.0
 */
public class LispExpressionEvaluator {
    /**
     * Evaluates a Lisp expression.
     * <p>
     * The algorithm: Scan the tokens in the string.
     * If you see "(", push the next operator onto the stack.
     * If you see an operand, push it onto the stack.
     * If you see ")", Pop operands and push them onto a second stack until you find an
     * operator. Apply the operator to the operands on the second stack. Push the
     * result on the stack.
     * <p>
     * What may occur? (Samples only)
     * <p>
     * If you run out of tokens, the value on the top
     * of the stack is the value of the expression.
     * OR
     * How to detect illegal expressions:
     * If you read numeric values and the
     * expression stack is empty
     * Error message: Bad Expression: needs an operator for the data
     * <p>
     * If there is a ) and the expression stack is empty
     * Error message: mismatched )
     * <p>
     * If there is a ) and operands needed but the expression stack is empty
     * Error message: mismatched )
     * <p>
     * If the top operator requires at least one operand, but it is not in the expression stack
     * Error message:operator nameOfTheOperand + " requires at least one operand"
     * <p>
     * If the string does not have any more characters but the expression stack is not empty
     * Error message:incomplete expression / multiple expressions
     * <p>
     * If the operator is not one of the +, -, *, ?
     * Error message: found an operator when we should not
     * <p>
     * If the expression is legal
     * Message:" and evaluates to " + whateverTheResultIs
     * <p>
     * <p>
     * <p>
     * <p>
     * Format of sample messages:
     * Message for a legal expression
     * <p>
     * The expression '(+ (- 1) (* 3 3 4) (/ 3 2 3) (* 4 4))' is legal in Lisp:
     * and evaluates to 51.5
     * <p>
     * Message for an illegal expression
     * <p>
     * The expression '(+ (-) (* 3 3 4) (/ 3 2 3) (* 4 4))' is not legal in Lisp:
     * operator - requires at least one operand
     *
     * Time complexity is O(n) overall since we only iterate through the expression
     * and push and pop each element once.
     *
     * @param lispExp A string that is a valid lisp expression.
     * @param mes     An ArrayList of strings that stores the messages generated.
     * @return A double that is the value of the expression.
     */
    @SuppressWarnings("resource")
    public static double evaluate(String lispExp, ArrayList<String> mes) {

        try {
            StackInterface<LispToken> expressionStack = new OurStack<>();
            StackInterface<LispToken> secondStack = new OurStack<>();
            boolean nextIsOperator = false;
            Scanner lispExpScanner = new Scanner(lispExp);
            // Use zero or more white spaces as delimiter
            // that breaks the string into single characters
            lispExpScanner.useDelimiter("\\s*");
            // Hint: use
            // lispExpScanner.hasNext() to test if there are more tokens
            // lispExpScanner.hasNextInt() to test if there is an integer
            // lispExpScanner.next() to get the next String

            while (lispExpScanner.hasNext()) {
                if (lispExpScanner.hasNextInt()) {
                    int value = lispExpScanner.nextInt();
                    expressionStack.push(new LispToken((double) value));
                } else {
                    char ch = lispExpScanner.next().charAt(0); // get character at the start of the string
                    switch (ch) {
                        case '(':
                            nextIsOperator = true;
                            break;
                        case '+':
                        case '-':
                        case '*':
                        case '/':
                            if (!nextIsOperator) {
                                throw new Exception("found an operator when we should not");
                            }
                            expressionStack.push(new LispToken(ch));
                            nextIsOperator = false;
                            break;
                        case ')':
                            evaluateCurrentOperation(expressionStack, secondStack);
                            break;
                        default:
                            throw new Exception("found an illegal character");
                    }
                }
            }

            Double res = expressionStack.pop().getValue();
            if (!expressionStack.isEmpty()) {
                // if every expression is evaluated
                // and there are multiple expressions
                // at the end
                // we can reduce any further
                throw new Exception("incomplete expression / multiple expressions");
            }
            mes.add(String.format(
                    "The expression '%s'\nis legal in Lisp:\nand evaluates to %f\n",
                    lispExp, res
            ));
            return res;
        } catch (Exception e) {
            mes.add(String.format(
                    "The expression '%s'\nis not legal in Lisp:\n%s\n",
                    lispExp, e.getMessage()
            ));
            return 0.0;
        }
    }

    private static void evaluateCurrentOperation(
            StackInterface<LispToken> expressionStack,
            StackInterface<LispToken> secondStack) throws Exception {
        int numberOfOperands = 0;

        while (!expressionStack.isEmpty() && !expressionStack.peek().isOperator()) {
            LispToken expression = expressionStack.pop();
            secondStack.push(expression);
            ++numberOfOperands;
        }
        if (expressionStack.isEmpty()) {
            // in the case where there is a ')' and there is no operator
            // there is a mismatched ')'
            throw new Exception("mismatched )");
        }

        LispToken operator = expressionStack.pop();
        char operatorChar = operator.toString().charAt(0);
        Double expressionValue = null;
        if (numberOfOperands == 0) {
            if (operator.takesZeroOperands()) {
                expressionValue = operator.getIdentity();
            } else {
                throw new Exception(String.format("operator %s requires at least one operand", operatorChar));
            }
        } else if (numberOfOperands == 1) {
            LispToken operandToken = secondStack.pop();
            double defaultValue = (operatorChar == '+' || operatorChar == '-') ? 0.0 : 1.0;
            expressionValue = operator.applyOperator(defaultValue, operandToken.getValue());
        } else {

            switch (operatorChar) {
                case '+':
                    expressionValue = 0.0;
                    break;
                case '*':
                    expressionValue = 1.0;
                    break;
                case '-':
                case '/':
                    expressionValue = secondStack.pop().getValue();
                    break;
            }

            while (!secondStack.isEmpty()) {
                LispToken removed = secondStack.pop();
                expressionValue = operator.applyOperator(expressionValue, removed.getValue());
            }

        }
        expressionStack.push(new LispToken(expressionValue));
    }

    public static void main(String[] args) {
        String[] tests = {
                "(+ 1 3)",
                "(- 1)",
                "(-)",
                "(+)",
                "(*)",
                "(/)",
                "(- 1 2)",
                "(+ (- 1) (* 3 3 4) (/ 3 2 3) (* 4 4))",
                "(+ (-) (* 3 3 4) (/ 3 2 3) (* 4 4))",
                "(+ (- 1) (* 3 3 4) ) 5 (* (/ 3 2 3) (* 4 4))",
                "(+ (- 1) (* 3 3 4) (/ 3 2 3)) (* 4 4))",
                "+ (- 1) (* 3 3 4) (/ 3 2 3)) (* 4 4))"
        };
        ArrayList<String> mes = new ArrayList<>();
        for (int i = 0; i < tests.length; i++) {
            evaluate(tests[i], mes);
            System.out.println(mes.get(i));
        }
        System.out.println("Done.");
    }
}
