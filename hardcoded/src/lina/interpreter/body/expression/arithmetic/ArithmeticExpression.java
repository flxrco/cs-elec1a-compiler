package lina.interpreter.body.expression.arithmetic;

import lina.interpreter.body.expression.relational.RelationalExpression;
import lina.interpreter.body.flow.AssignmentValue;
import lina.interpreter.body.flow.Statement;
import lina.interpreter.body.flow.exception.*;
import lina.lexer.tokenizer.TokenType;

public class ArithmeticExpression implements AssignmentValue {

    private Object parent;
    private ArithmeticTerm a;
    private ArithmeticExpression b;
    private TokenType ope;

    public ArithmeticExpression(Object parent) {
        this.parent = parent;
    }

    public void setA(ArithmeticTerm a) {
        this.a = a;
    }

    public void setB(ArithmeticExpression b) {
        this.b = b;
    }

    public void setOpe(TokenType ope) {
        this.ope = ope;
    }
    
    public int lookUp(String varName) throws LinaException {
        if (parent instanceof Statement) {
            return ((Statement) parent).getValue(varName);
        } else {
            return ((RelationalExpression) parent).lookUp(varName);
        }
    }
    
    public int evaluate() throws LinaException {
        if (ope != null) {
            switch (ope) {
                case ARI_ADD:
                    return a.evaluate() + b.evaluate();
                case ARI_SUB:
                    return a.evaluate() - b.evaluate();
            }
        }
        
        return a.evaluate();
    }
}
