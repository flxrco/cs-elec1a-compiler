/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lina.interpreter.body.flow;

import lina.interpreter.body.flow.exception.LinaException;

/**
 *
 * @author User
 */
public interface AssignmentValue {
    public int evaluate() throws LinaException;
}
