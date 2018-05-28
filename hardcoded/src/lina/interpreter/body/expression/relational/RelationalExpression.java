package lina.interpreter.body.expression.relational;

import lina.interpreter.body.expression.arithmetic.ArithmeticExpression;
import lina.interpreter.body.expression.logical.LogicalExpression;
import lina.interpreter.body.flow.exception.*;
import lina.lexer.tokenizer.TokenType;

public class RelationalExpression {

    private LogicalExpression parent;
    private ArithmeticExpression a, b;
    private TokenType ope;

    public RelationalExpression(LogicalExpression parent) {
        this.parent = parent;
    }

    public void setA(ArithmeticExpression a) {
        this.a = a;
    }

    public void setB(ArithmeticExpression b) {
        this.b = b;
    }

    public void setOpe(TokenType ope) {
        this.ope = ope;
    }
    
    public int lookUp(String varName) throws LinaException {
        return parent.lookUp(varName);
    }
    
    public boolean evaluate() throws LinaException {
        int a = this.a.evaluate(), b = this.b.evaluate();
        
        switch(ope) {
            case REL_LESS:
                return a < b;
            case REL_LESS_EQ:
                return a <= b;
            case REL_GRTR:
                return a > b;
            case REL_GRTR_EQ:
                return a >= b;
            case REL_EQ:
                return a == b;
            case REL_NOT_EQ:
                return a != b;
        }
        
        return false;
    }
}
