package lina.interpreter.body.expression.arithmetic;

import lina.interpreter.body.flow.exception.*;

public class IntegerLiteral extends ArithmeticFactor {

    private int value;

    public IntegerLiteral(int value) {
        super(null);
        this.value = value;
    }

    @Override
    public int evaluate() throws LinaException {
        return value;
    }

}
