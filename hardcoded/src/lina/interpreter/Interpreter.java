/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lina.interpreter;

import java.io.IOException;
import lina.interpreter.body.flow.Program;
import lina.interpreter.body.flow.exception.LinaException;
import lina.parser.Parser;
import lina.parser.parsetree.ParseNode;

/**
 *
 * @author User
 */
public class Interpreter {

    public static void main(String[] args) throws IOException, LinaException {
        try {
            Program program = interpret(Parser.parseStream(args[0]));
            //Program program = interpret(Parser.parseStream("D:\\Users\\User\\Documents\\Programming Projects\\cs-elec1a-compiler\\hardcoded\\test.txt"));
            if (program != null) {
                program.execute();
            }
            
            System.out.println("BUILD SUCCESSFUL");
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
            System.out.println("BUILD FAILED");
        } catch (LinaException ex) {
            System.out.println("LinaException: " + ex.getMessage());
            System.out.println("BUILD FAILED");
        }
    }

    public static Program interpret(ParseNode root) {
        if (root == null) {
            System.out.println("\nCompilation failed.");
            return null;
        }

        return ParseTreeConverter.convertProgram(root);
    }
}
