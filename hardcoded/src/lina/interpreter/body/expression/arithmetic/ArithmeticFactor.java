package lina.interpreter.body.expression.arithmetic;

import lina.interpreter.body.flow.exception.*;

public abstract class ArithmeticFactor {
    
    private ArithmeticTerm parent;
    
    public ArithmeticFactor(ArithmeticTerm parent) {
        this.parent = parent;
    }
    
    public ArithmeticTerm getParent() {
        return parent;
    }
    
    public abstract int evaluate() throws LinaException;
}
