package lina.interpreter.body.flow;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import lina.interpreter.body.flow.exception.DuplicateVariableException;
import lina.interpreter.body.flow.exception.LinaException;
import lina.interpreter.body.flow.exception.VariableNotFoundException;

public abstract class Statement {

    private Map<String, Integer> variables;
    private Statement parent;
    private List<Statement> statements;

    public Statement(Statement parent) {
        this.parent = parent;
        variables = new HashMap<>();
        statements = new ArrayList<>();
    }

    public Statement getParent() {
        return parent;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    protected Map<String, Integer> getVariables() {
        return variables;
    }

    public void addStatement(Statement stmt) {
        statements.add(stmt);
    }

    public int getValue(String varName) throws VariableNotFoundException {
        if (variables.containsKey(varName)) {
            return variables.get(varName);
        } else {
            if (parent == null) {
                throw new VariableNotFoundException();
            } else {
                return parent.getValue(varName);
            }
        }
    }

    public int assign(String varName, int value) throws VariableNotFoundException {
        if (variables.containsKey(varName)) {
            variables.put(varName, value);
            return value;
        } else {
            if (parent == null) {
                throw new VariableNotFoundException();
            } else {
                return parent.assign(varName, value);
            }
        }
    }

    public void declare(String varName) throws DuplicateVariableException {
        try {
            getValue(varName);
            throw new DuplicateVariableException();
        } catch (VariableNotFoundException ex) {
            variables.put(varName, null);
        }
    }

    public int initialize(String varName, int value) throws DuplicateVariableException {
        declare(varName);
        try {
            return assign(varName, value);
        } catch (VariableNotFoundException ex) {
            //will not happen since declare ensures that the variable will exist before assigning
        }
        
        return -1;
    }

    public void execute() throws LinaException {
        for (Statement stmt : statements) {
            stmt.execute();
        }
    }

}
