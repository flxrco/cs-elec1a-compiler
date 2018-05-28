package lina.interpreter.body.expression.arithmetic;

import lina.interpreter.body.flow.exception.*;
import lina.lexer.tokenizer.TokenType;

public class ArithmeticTerm {
    
    private ArithmeticExpression parent;
    private ArithmeticFactor a;
    private ArithmeticTerm b;
    private TokenType ope;

    public ArithmeticTerm(ArithmeticExpression parent) {
        this.parent = parent;
    }

    public void setA(ArithmeticFactor a) {
        this.a = a;
    }

    public void setB(ArithmeticTerm b) {
        this.b = b;
    }

    public void setOpe(TokenType ope) {
        this.ope = ope;
    }
    
    public int lookUp(String varName) throws LinaException {
        return parent.lookUp(varName);
    }

    public int evaluate() throws LinaException {
        if (ope != null) {
            switch (ope) {
                case ARI_MUL:
                    return a.evaluate() * b.evaluate();
                case ARI_DIV:
                    return a.evaluate() / b.evaluate();
                case ARI_MOD:
                    return a.evaluate() % b.evaluate();
            }
        }
        
        return a.evaluate();
    }
}
