package lina.interpreter.body.method;

import lina.interpreter.body.expression.arithmetic.ArithmeticExpression;
import lina.interpreter.body.flow.Statement;
import lina.interpreter.body.flow.exception.LinaException;

public class Yield extends InnerStatement {

    private Object param;

    public Yield(Statement parent, Object param) {
        super(parent);
        this.param = param;
    }

    @Override
    public void execute() throws LinaException {
        if (param instanceof String) {
            String str = param.toString();
            System.out.println(str.substring(1, str.length() - 1));
        } else {
            ArithmeticExpression ae = (ArithmeticExpression) param;
            System.out.println(String.format("%d", ae.evaluate()));
        }
    }
}
