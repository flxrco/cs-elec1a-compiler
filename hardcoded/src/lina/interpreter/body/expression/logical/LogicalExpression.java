package lina.interpreter.body.expression.logical;

import lina.interpreter.body.expression.relational.RelationalExpression;
import lina.interpreter.body.flow.Statement;

import lina.interpreter.body.flow.exception.*;
import lina.lexer.tokenizer.TokenType;

public class LogicalExpression {

    private Statement parent;
    private RelationalExpression a;
    private TokenType ope;
    private LogicalExpression b;

    public LogicalExpression(Statement parent) {
        this.parent = parent;
    }

    public void setA(RelationalExpression a) {
        this.a = a;
    }

    public void setOpe(TokenType ope) {
        this.ope = ope;
    }

    public void setB(LogicalExpression b) {
        this.b = b;
    }
    
    public int lookUp(String varName) throws LinaException {
        return parent.getValue(varName);
    }

    public boolean evaluate() throws LinaException {
        if (ope != null) {
            switch (ope) {
                case LOG_OR:
                    return a.evaluate() || b.evaluate();
                case LOG_AND:
                    return a.evaluate() && b.evaluate();
            }
        }
        
        return a.evaluate();
    }
}
