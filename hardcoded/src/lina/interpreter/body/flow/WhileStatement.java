package lina.interpreter.body.flow;

import lina.interpreter.body.expression.logical.LogicalExpression;

import lina.interpreter.body.flow.exception.*;

import lina.interpreter.body.method.InnerStatement;

public class WhileStatement extends Statement {

    private LogicalExpression conditions;

    public WhileStatement(Statement parent, LogicalExpression conditions) {
        super(parent);
        this.conditions = conditions;
    }

    public void addStatement(InnerStatement stmt) {
        super.addStatement(stmt);
    }

    @Override
    public void execute() throws LinaException {
        while (conditions.evaluate()) {
            super.execute();
        }
    }
}
