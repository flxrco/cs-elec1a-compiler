package lina.interpreter.body.flow;

import lina.interpreter.body.flow.exception.LinaException;
import lina.interpreter.body.method.InnerStatement;

public class Assignment extends InnerStatement {
	
	private String varName;
	private AssignmentValue value;

	public Assignment(Statement parent, String varName, AssignmentValue value) {
		super(parent);
		this.varName = varName;
		this.value = value;
	}

        @Override
	public void execute() throws LinaException {
            super.getParent().assign(varName, value.evaluate());
	}
}