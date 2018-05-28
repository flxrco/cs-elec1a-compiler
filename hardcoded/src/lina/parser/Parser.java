package lina.parser;

import java.util.*;
import java.io.*;

import lina.lexer.TokenStream;
import lina.lexer.tokenizer.TokenType;
import lina.parser.cfg.Grammar;

import lina.parser.parsetree.ParseNode;
import lina.parser.parsetree.ParseNonTerminal;

import lina.lexer.Lexer;

public class Parser {

    private static final Grammar GRAMMAR = new Grammar();

    static {
        GRAMMAR.addProduction("program", TokenType.DEL_CODE_OP, "class", TokenType.DEL_CODE_CL);
        GRAMMAR.addProduction("class", TokenType.KEY_CLASS, TokenType.DEL_BRCKT_OP, TokenType.IDENTIFIER, TokenType.DEL_BRCKT_CL, TokenType.DEL_CURLY_OP, "statements", TokenType.DEL_CURLY_CL);
        GRAMMAR.addProduction("statements", "statement", "statements_prime");
        GRAMMAR.addProduction("statements_prime", "statement", "statements_prime");
        GRAMMAR.addProduction("statements_prime", TokenType.EPSILON);
        GRAMMAR.addProduction("statement", "if_statement");
        GRAMMAR.addProduction("statement", "for_statement");
        GRAMMAR.addProduction("statement", "while_statement");
        GRAMMAR.addProduction("statement", "declaration_statement");
        GRAMMAR.addProduction("statement", "inner_statement", TokenType.SEMICOLON);
        GRAMMAR.addProduction("inner_statement", "assignment_statement");
        GRAMMAR.addProduction("inner_statement", "yield_method");
        GRAMMAR.addProduction("if_statement", TokenType.KEY_IF, "logical_expression", TokenType.KEY_THEN, "inner_statement", TokenType.SEMICOLON, "elseif_statements_prime");
        GRAMMAR.addProduction("elseif_statements_prime", "elseif_statement", "elseif_statements_prime");
        GRAMMAR.addProduction("elseif_statements_prime", TokenType.EPSILON);
        GRAMMAR.addProduction("elseif_statement", TokenType.KEY_ELSEIF, "logical_expression", TokenType.KEY_THEN, "inner_statement", TokenType.SEMICOLON);
        GRAMMAR.addProduction("while_statement", TokenType.KEY_WHILE, "logical_expression", TokenType.KEY_DO, "inner_statement", TokenType.SEMICOLON);
        GRAMMAR.addProduction("for_statement", TokenType.KEY_FOR, "arithmetic_expression", TokenType.KEY_TO, "arithmetic_expression", TokenType.KEY_DO, "inner_statement", TokenType.KEY_STEP, "arithmetic_expression", TokenType.KEY_END, TokenType.SEMICOLON);
        GRAMMAR.addProduction("declaration_statement", TokenType.KEY_DECLARE, TokenType.COLON, TokenType.DATATYPE_INTEGER, TokenType.COLON, TokenType.IDENTIFIER, TokenType.ASSIGN, "assignment_value", TokenType.SEMICOLON);
        GRAMMAR.addProduction("assignment_statement", TokenType.IDENTIFIER, TokenType.ASSIGN, "assignment_value");
        GRAMMAR.addProduction("assignment_value", "arithmetic_expression");
        GRAMMAR.addProduction("assignment_value", "store_method");
        GRAMMAR.addProduction("arithmetic_expression", "arithmetic_term", "arithmetic_expression_prime");
        GRAMMAR.addProduction("arithmetic_expression_prime", TokenType.ARI_ADD, "arithmetic_term", "arithmetic_expression_prime");
        GRAMMAR.addProduction("arithmetic_expression_prime", TokenType.ARI_SUB, "arithmetic_term", "arithmetic_expression_prime");
        GRAMMAR.addProduction("arithmetic_expression_prime", TokenType.EPSILON);
        GRAMMAR.addProduction("arithmetic_term", "arithmetic_factor", "arithmetic_term_prime");
        GRAMMAR.addProduction("arithmetic_term_prime", TokenType.ARI_MUL, "arithmetic_factor", "arithmetic_term_prime");
        GRAMMAR.addProduction("arithmetic_term_prime", TokenType.ARI_DIV, "arithmetic_factor", "arithmetic_term_prime");
        GRAMMAR.addProduction("arithmetic_term_prime", TokenType.ARI_MOD, "arithmetic_factor", "arithmetic_term_prime");
        GRAMMAR.addProduction("arithmetic_term_prime", TokenType.EPSILON);
        GRAMMAR.addProduction("arithmetic_factor", TokenType.IDENTIFIER);
        GRAMMAR.addProduction("arithmetic_factor", TokenType.LIT_INT);
        GRAMMAR.addProduction("relational_expression", "arithmetic_expression", "relational_operator", "arithmetic_expression", "relational_expression_prime");
        GRAMMAR.addProduction("relational_expression_prime", "relational_operator", "arithmetic_expression", "relational_expression_prime");
        GRAMMAR.addProduction("relational_expression_prime", TokenType.EPSILON);
        GRAMMAR.addProduction("relational_operator", TokenType.REL_GRTR_EQ);
        GRAMMAR.addProduction("relational_operator", TokenType.REL_LESS_EQ);
        GRAMMAR.addProduction("relational_operator", TokenType.REL_GRTR);
        GRAMMAR.addProduction("relational_operator", TokenType.REL_LESS);
        GRAMMAR.addProduction("relational_operator", TokenType.REL_EQ);
        GRAMMAR.addProduction("relational_operator", TokenType.REL_NOT_EQ);
        GRAMMAR.addProduction("logical_expression", "relational_expression", "logical_expression_prime");
        GRAMMAR.addProduction("logical_expression_prime", "logical_operator", "relational_expression", "logical_expression_prime");
        GRAMMAR.addProduction("logical_expression_prime", TokenType.EPSILON);
        GRAMMAR.addProduction("logical_operator", TokenType.LOG_AND);
        GRAMMAR.addProduction("logical_operator", TokenType.LOG_OR);
        GRAMMAR.addProduction("store_method", TokenType.METHOD_STORE, TokenType.DEL_PAREN_OP, TokenType.DEL_PAREN_CL);
        GRAMMAR.addProduction("yield_method", TokenType.METHOD_YIELD, TokenType.DEL_PAREN_OP, "yield_param", TokenType.DEL_PAREN_CL);
        GRAMMAR.addProduction("yield_param", "arithmetic_expression");
        GRAMMAR.addProduction("yield_param", TokenType.LIT_STRING);
        GRAMMAR.buildGrammar();
    }

    public static ParseNode parseStream(String path) throws IOException {
        return parseStream(Lexer.analyze(path));
    }

    public static ParseNode parseStream(TokenStream stream) {      
        ParseNode node = GRAMMAR.parse(stream);
        return node;
    }

    public static void main(String[] args) throws IOException {
        ParseNode node = parseStream(args[0]);
        
        if (node == null) {
            return;
        }
        
        for (String s : ((ParseNonTerminal) node).depthFirst()) {
            System.out.println(s);
        }
    }

}
