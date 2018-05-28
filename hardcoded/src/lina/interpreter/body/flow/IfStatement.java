package lina.interpreter.body.flow;

import lina.interpreter.body.expression.logical.LogicalExpression;
import lina.interpreter.body.flow.exception.LinaException;


import lina.interpreter.body.method.InnerStatement;

public class IfStatement extends Statement {

    private IfStatement elseIf;
    private LogicalExpression conditions;

    public IfStatement(Statement parent, LogicalExpression conditions) {
        super(parent);
        this.conditions = conditions;
    }

    public IfStatement(Statement parent, LogicalExpression conditions, IfStatement elseIf) {
        this(parent, conditions);
        setElseIf(elseIf);
    }

    public void setElseIf(IfStatement elseIf) {
        this.elseIf = elseIf;
    }

    public void addStatement(InnerStatement stmt) {
        super.addStatement(stmt);
    }

    @Override
    public void execute() throws LinaException {
        if (conditions.evaluate()) {
            super.execute();
        } else if (elseIf != null) {
            elseIf.execute();
        }
    }
}
