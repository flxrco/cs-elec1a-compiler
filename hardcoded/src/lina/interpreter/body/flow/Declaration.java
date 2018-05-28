package lina.interpreter.body.flow;

import lina.interpreter.body.flow.exception.LinaException;

public class Declaration extends Statement {
	
	private String varName;
	private AssignmentValue value;

	public Declaration(Statement parent, String varName, AssignmentValue value) {
		super(parent);
		this.varName = varName;
		this.value = value;
	}

	@Override
	public void execute() throws LinaException {
            super.getParent().initialize(varName, value.evaluate());
	}
}