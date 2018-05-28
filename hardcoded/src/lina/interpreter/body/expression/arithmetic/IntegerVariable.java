package lina.interpreter.body.expression.arithmetic;

import lina.interpreter.body.flow.Statement;
import lina.interpreter.body.flow.exception.*;

public class IntegerVariable extends ArithmeticFactor {

    private String varName;
    
    public IntegerVariable(ArithmeticTerm parent, String varName) {
        super(parent);
        this.varName = varName;
    }

    @Override
    public int evaluate() throws LinaException {
        return super.getParent().lookUp(varName);
    }
}
