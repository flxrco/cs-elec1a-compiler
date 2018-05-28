package lina.interpreter.body.flow;

import lina.interpreter.body.expression.arithmetic.ArithmeticExpression;
import lina.interpreter.body.expression.logical.LogicalExpression;
import lina.interpreter.body.flow.exception.LinaException;

import lina.interpreter.body.method.InnerStatement;

public class ForStatement extends Statement {

	private LogicalExpression conditions;
	private ArithmeticExpression start, end, step;

	public ForStatement(Statement parent, ArithmeticExpression start, ArithmeticExpression end, ArithmeticExpression step) {
		super(parent);
		this.start = start;
		this.end = end;
		this.step = step;
	}

	public void addStatement(InnerStatement stmt) {
		super.addStatement(stmt);
	}

	@Override
	public void execute() throws LinaException {
		for (int i = start.evaluate(); i < end.evaluate(); i += step.evaluate()) {
			super.execute();
		}
	}
}