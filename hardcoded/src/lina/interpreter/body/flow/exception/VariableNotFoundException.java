/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lina.interpreter.body.flow.exception;

/**
 *
 * @author User
 */
public class VariableNotFoundException extends LinaException {
    
    public VariableNotFoundException() {
        super("No such variable exists.");
    }
    
}
