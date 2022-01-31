package assignment;
/*
 * I affirm that I have carried out my academic endeavors with full academic honesty.
 * [Signed, Khai Dong]
 */
/**
 * This class represents either an operand or an operator for an arithmetic
 * expressions in Lisp.
 *
 * @author Charles Hoot
 * @author Khai Dong modified
 * @version 5.0
 */
public class LispToken {
    private final Character operator;
    private final Double operand;
    private final boolean isOperator;

    /**
     * Constructor for objects of class LispToken for operators.
     * isOperator is true and operand is 0.0, operator is anOperator
     *
     * @param anOperator of type Character
     */
    public LispToken(Character anOperator) {
        this(anOperator, 0.0, true);
    }

    /**
     * Constructor for objects of class LispToken for operands.
     * isOperator is false and operand is the value, operator is ' '
     *
     * @param value of type Double
     */
    public LispToken(Double value) {
        this(' ', value, false);
    }

    private LispToken(Character operator, Double operand, boolean isOperator) {
        this.operator = operator;
        this.operand = operand;
        this.isOperator = isOperator;
    }

    /**
     * applyOperator: Applies this operator to two given operand values.
     *
     * @param value1 The value of the first operand.
     * @param value2 The value of the second operand.
     * @return The real result of the operation.
     */
    public Double applyOperator(Double value1, Double value2) {
        if (this.isOperator()) {
            Character operator = this.operator;
            Double result = null;
            switch (operator) {
                case '+':
                    result = value1 + value2;
                    break;
                case '-':
                    result = value1 - value2;
                    break;
                case '*':
                    result = value1 * value2;
                    break;
                case '/':
                    result = value1 / value2;
                    break;
            }
            return result;
        }
        return null;
    }

    /**
     * getIdentity: Gets the identity value of this operator. For example, x + 0 = x, so 0 is the
     * identity for + and will be the value associated with the expression (+).
     *
     * @return The identity value of the operator as Double.
     */
    public Double getIdentity() {
        Character operator = this.operator;
        Double result = null;
        switch (operator) {
            case '+':
            case '-':
                result = 0.0;
                break;
            case '*':
            case '/':
                result = 1.0;
                break;
        }
        return result;
    }

    /**
     * takesZeroOperands: Detects whether this operator returns a value when it has no operands.
     *
     * @return True if the operator returns a value when it has no operands, or
     * false if not.
     */
    public boolean takesZeroOperands() {
        if (!this.isOperator()) return false;
        Character operator = this.operator;
        return operator == '*' || operator == '+';
    }

    /**
     * getValue: Gets the value of this operand.
     *
     * @return The real value of the operand.
     */
    public Double getValue() {
        return this.operand;
    }

    /**
     * isOperator: Returns true if the object is an operator.
     *
     * @return True is this object is an operator.
     */
    public boolean isOperator() {
        return this.isOperator;
    }

    /**
     * toString: Returns a string representation of the operator or operand
     *
     * @return String
     */
    @Override
    public String toString() {
        if (this.isOperator()) return operator.toString();
        return this.operand.toString();
    }

}