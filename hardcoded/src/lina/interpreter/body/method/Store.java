/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lina.interpreter.body.method;

import java.util.Scanner;
import lina.interpreter.body.flow.AssignmentValue;
import lina.interpreter.body.flow.exception.InvalidCharacterException;
import lina.interpreter.body.flow.Statement;

/**
 *
 * @author User
 */
public class Store implements AssignmentValue {

    private Statement parent;

    public Store(Statement parent) {
        this.parent = parent;
    }

    @Override
    public int evaluate() throws InvalidCharacterException {
        try {
            Scanner sc = new Scanner(System.in);
            return sc.nextInt();
        } catch (Exception ex) {
            throw new InvalidCharacterException();
        }
    }
}
