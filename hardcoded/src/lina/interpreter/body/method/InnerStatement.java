package lina.interpreter.body.method;

import lina.interpreter.body.flow.Statement;

public class InnerStatement extends Statement {

    public InnerStatement(Statement parent) {
        super(parent);
    }

}