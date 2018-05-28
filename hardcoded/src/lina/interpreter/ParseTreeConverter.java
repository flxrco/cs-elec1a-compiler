/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lina.interpreter;

import java.util.List;
import lina.interpreter.body.expression.arithmetic.ArithmeticExpression;
import lina.interpreter.body.expression.arithmetic.ArithmeticFactor;
import lina.interpreter.body.expression.arithmetic.ArithmeticTerm;
import lina.interpreter.body.expression.arithmetic.IntegerLiteral;
import lina.interpreter.body.expression.arithmetic.IntegerVariable;
import lina.interpreter.body.expression.logical.LogicalExpression;
import lina.interpreter.body.expression.relational.RelationalExpression;
import lina.interpreter.body.flow.Assignment;
import lina.interpreter.body.flow.AssignmentValue;
import lina.interpreter.body.flow.Declaration;
import lina.interpreter.body.flow.ForStatement;
import lina.interpreter.body.flow.IfStatement;
import lina.interpreter.body.flow.Program;
import lina.interpreter.body.flow.Statement;
import lina.interpreter.body.flow.WhileStatement;
import lina.interpreter.body.method.InnerStatement;
import lina.interpreter.body.method.Store;
import lina.interpreter.body.method.Yield;
import lina.lexer.tokenizer.Token;
import lina.lexer.tokenizer.TokenType;
import lina.parser.parsetree.ParseNode;
import lina.parser.parsetree.ParseNonTerminal;
import lina.parser.parsetree.ParseTerminal;

/**
 *
 * @author User
 */
public class ParseTreeConverter {
    //program
    public static Program convertProgram(ParseNode node) {
        Program program = new Program();
        
        ParseNode statements = extractNodes(extractNodes(node).get(1)).get(5);
        convertStatements(statements, program);
        return program;
    }
    
    private static Statement convertStatements(ParseNode node, Statement parent) {
        List<ParseNode> list = extractNodes(node);
        
        parent.addStatement(convertStatement(list.get(0), parent));
        convertStatementsPrime(list.get(1), parent);
        return parent;
    }
    
    private static void convertStatementsPrime(ParseNode node, Statement parent) {
        List<ParseNode> list = extractNodes(node);
        
        if (list.size() > 1) {
            parent.addStatement(convertStatement(list.get(0), parent));
            convertStatementsPrime(list.get(1), parent);
        }
        
        /*
        if (list.get(0) instanceof ParseNonTerminal) {
            parent.addStatement(convertStatement(node, parent));
            convertStatementsPrime(list.get(1), parent);
        }
        */
    }
    
    private static Statement convertStatement(ParseNode node, Statement parent) {      
        List<ParseNode> list = extractNodes(node);
        ParseNonTerminal pnt = (ParseNonTerminal) list.get(0);
        switch (pnt.getRuleName()) {
            case "if_statement":
                return convertIf(list.get(0), parent);
            case "for_statement":
                return convertFor(list.get(0), parent);
            case "while_statement":
                return convertWhile(list.get(0), parent);
            case "declaration_statement":
                return convertDeclaration(list.get(0), parent);
            case "inner_statement":
                return convertInner(list.get(0), parent);
        }
        
        return null;
    }
    
    private static IfStatement convertIf(ParseNode node, Statement parent) {
        List<ParseNode> list = extractNodes(node);
        
        IfStatement stmt = new IfStatement(parent, convertLogExpr(list.get(1), parent), convertElseIfs(list.get(5), parent));
        stmt.addStatement(convertInner(list.get(3), stmt));
        return stmt;
    }
    
    private static IfStatement convertElseIfs(ParseNode node, Statement parent) {
        List<ParseNode> list = extractNodes(node);
        
        if (list.size() > 1) {
            IfStatement elseIf = convertElseIf(list.get(0), parent);
            elseIf.setElseIf(convertElseIfs(list.get(1), parent));
            return elseIf;
        }
        
        return null;
        
        /*
        if (list.get(0) instanceof ParseTerminal) { //means that it is epsilon
            return null;
        } else {
            IfStatement elseIf = convertElseIf(node, parent);
            elseIf.setElseIf(convertElseIfs(list.get(1), parent));
            return elseIf;
        }*/
    }
    
    private static WhileStatement convertWhile(ParseNode node, Statement parent) {
        List<ParseNode> list = extractNodes(node);
        
        WhileStatement stmt = new WhileStatement(parent, convertLogExpr(list.get(1), parent));
        stmt.addStatement(convertInner(list.get(3), stmt));
        
        return stmt;
    }

    private static ForStatement convertFor(ParseNode node, Statement parent) {
        List<ParseNode> list = extractNodes(node);
        
        ForStatement stmt = new ForStatement(parent, convertArithExpr(list.get(1), parent), convertArithExpr(list.get(3), parent), convertArithExpr(list.get(7), parent));
        stmt.addStatement(convertInner(list.get(5), stmt));
        
        return stmt;
    }
    
    private static IfStatement convertElseIf(ParseNode node, Statement parent) {
        List<ParseNode> list = extractNodes(node);
        
        IfStatement stmt = new IfStatement(parent, convertLogExpr(list.get(1), parent));
        stmt.addStatement(convertInner(list.get(3), stmt));
        return stmt;
    }
    
    private static InnerStatement convertInner(ParseNode node, Statement parent) {
        List<ParseNode> list = extractNodes(node);
        ParseNonTerminal pnt = (ParseNonTerminal) list.get(0);
        switch (pnt.getRuleName()) {
            case "assignment_statement":
                return convertAssignment(list.get(0), parent);
            case "yield_method":
                return convertYield(list.get(0), parent);
        }
        
        return null;
    }
    
    //flow control
    
    private static Assignment convertAssignment(ParseNode node, Statement parent) {
        List<ParseNode> list = extractNodes(node);        
        return new Assignment(parent, extractToken(list.get(0)).getLexeme(), convertAssignmentValue(list.get(2), parent));
    }
    
    private static Declaration convertDeclaration(ParseNode node, Statement parent) {
        List<ParseNode> list = extractNodes(node);
        
        return new Declaration(parent, extractToken(list.get(4)).getLexeme(), convertAssignmentValue(list.get(6), parent));
    } 
    
    private static AssignmentValue convertAssignmentValue(ParseNode node, Statement parent) {
        List<ParseNode> list = extractNodes(node);
        
        if (((ParseNonTerminal) list.get(0)).getRuleName().equals("store_method")) {
            return convertStore(list.get(0), parent);
        } else {
            return convertArithExpr(list.get(0), parent);
        }
    }
    
    //methods
    private static Yield convertYield(ParseNode node, Statement parent) {
        List<ParseNode> list = extractNodes(node);
        return new Yield(parent, convertYieldParam(list.get(2), parent));
    }
    
    private static Object convertYieldParam(ParseNode node, Statement parent) {
        List<ParseNode> list = extractNodes(node);
        if (list.get(0) instanceof ParseTerminal) {
            return extractToken(list.get(0)).getLexeme();
        } else {
            return convertArithExpr(list.get(0), parent);
        }
    }
    
    private static Store convertStore(ParseNode node, Statement parent) {
        return new Store(parent);
    }
    
    //logical epxressions
    private static LogicalExpression convertLogExpr(ParseNode node, Statement parent) {
        List<ParseNode> list = extractNodes(node);
        
        LogicalExpression logExpr = new LogicalExpression(parent);
        
        logExpr.setA(convertRelExpr(list.get(0), logExpr));
        convertLogExprPrime(list.get(1), logExpr, parent);
        
        return logExpr;
    }
    
    private static void convertLogExprPrime(ParseNode node, LogicalExpression logExpr, Statement parent) {
        List<ParseNode> list = extractNodes(node);
        
        
        if (list.size() > 1) {
            LogicalExpression prime = new LogicalExpression(parent);
            logExpr.setOpe(convertLogTok(list.get(0)));
            logExpr.setB(prime);
            prime.setA(convertRelExpr(list.get(1), prime));
            convertLogExprPrime(list.get(2), prime, parent);
        }
    }
    
    private static TokenType convertLogTok(ParseNode node) {
        return extractFirstType(node);
    }
    
    //relational expressions
    
    private static RelationalExpression convertRelExpr(ParseNode node, LogicalExpression parent) {
        List<ParseNode> list = extractNodes(node);
        
        RelationalExpression relExpr = new RelationalExpression(parent);
        
        relExpr.setA(convertArithExpr(list.get(0), relExpr));
        relExpr.setOpe(convertRelTok(list.get(1)));
        relExpr.setB(convertArithExpr(list.get(2), relExpr));
        
        return relExpr;
    }
    
    private static TokenType convertRelTok(ParseNode node) {
        return extractFirstType(node);
    }
    
    //arithmetic expressions
    
    private static ArithmeticExpression convertArithExpr(ParseNode node, Object parent) {
        List<ParseNode> list = extractNodes(node);
        
        ArithmeticExpression arithExpr = new ArithmeticExpression(parent);
        arithExpr.setA(convertArithTerm(list.get(0), arithExpr));
        convertArithExprPrime(list.get(1), arithExpr, parent);
        
        return arithExpr;
    }
    
    private static void convertArithExprPrime(ParseNode node, ArithmeticExpression arithExpr, Object parent) {
        List<ParseNode> list = extractNodes(node); //get all list
        
        if (list.size() > 1) {
            ArithmeticExpression prime = new ArithmeticExpression(parent);
            arithExpr.setOpe(extractType(list.get(0)));
            arithExpr.setB(prime);
            prime.setA(convertArithTerm(list.get(1), prime));
            convertArithExprPrime(list.get(2), prime, parent);
        }
        
        /*
        for (ParseNode n : list) {
            
        
        arithExpr.setOpe(extractType(list.get(0))); //the leftmost token is the operator
        
        ArithmeticExpression prime = new ArithmeticExpression(parent); //create a new 'buffer'
        
        if (compareType(list.get(2), TokenType.EPSILON)) { //if rightmost token is epsilon, arithterm will be B. arithterm is to be turned into an expr first
            prime.setA(convertArithTerm(list.get(1), prime)); //add arithterm as the A of the 'buffer'
        } else { //if rightmost token is not epsilon then...
            arithExpr.setB(prime); //the buffer will act as the B
            prime.setA(convertArithTerm(list.get(1), prime)); //the A of the buffer will be the middle production
            convertArithExprPrime(list.get(2), prime, parent); //the rightmost production will be explored and this method will be called again
        }
        */
    }
    
    private static ArithmeticTerm convertArithTerm(ParseNode node, ArithmeticExpression parent) {
        List<ParseNode> list = extractNodes(node);
        
        ArithmeticTerm term = new ArithmeticTerm(parent);
        term.setA(convertArithFctr(list.get(0), term));
        convertArithTermPrime(list.get(1), term, parent);
        
        return term;
    }
    
    private static void convertArithTermPrime(ParseNode node, ArithmeticTerm arithTerm, ArithmeticExpression parent) {
        List<ParseNode> list = extractNodes(node);
        
        if (list.size() == 1) {
            return;
        }
        
        arithTerm.setOpe(extractType(list.get(0)));
        
        ArithmeticTerm prime = new ArithmeticTerm(parent);
        
        if (compareType(list.get(2), TokenType.EPSILON)) {
            prime.setA(convertArithFctr(node, prime));
        } else { //not EPSILON
            arithTerm.setB(prime);
            prime.setA(convertArithFctr(list.get(1), prime));
            convertArithTermPrime(list.get(2), prime, parent);
        }
        
    }
    
    private static ArithmeticFactor convertArithFctr(ParseNode node, ArithmeticTerm parent) {
        List<ParseNode> list = extractNodes(node);
        Token tok = extractToken(list.get(0));
        if (tok.compareType(TokenType.IDENTIFIER)) {
            return new IntegerVariable(parent, tok.getLexeme());
        } else { //LIT_INT
            return new IntegerLiteral(Integer.parseInt(tok.getLexeme()));
        }
    }
    
    //helper functions
    
    private static Token extractFirstToken(ParseNode node) {
        return extractToken(extractNodes(node).get(0));
    }
    
    private static TokenType extractFirstType(ParseNode node) {
        return extractType(extractNodes(node).get(0));
    }
    
    private static Token extractToken(ParseNode node) {
        ParseTerminal pt = (ParseTerminal) node;
        return pt;
    }
    
    private static List<ParseNode> extractNodes(ParseNode node) {
        ParseNonTerminal pnt = (ParseNonTerminal) node;
        return pnt.getNodes();
    }
    
    private static TokenType extractType(ParseNode node) {
        return ((ParseTerminal) node).getType();
    }
    
    private static boolean compareType(ParseNode node, TokenType type) {
        return extractType(node) == type;
    }
    
    private static String stringNode(ParseNode node) {
        if (node instanceof ParseTerminal) {
            return (((ParseTerminal) node).getType().name());
        } else {
            return (((ParseNonTerminal) node).getRuleName());
        }
    }
}
