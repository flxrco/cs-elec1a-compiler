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

	private static Grammar grammar = new Grammar();

	static {
		grammar.addProduction("program", TokenType.DEL_CODE_OP, "class", TokenType.DEL_CODE_CL);
		grammar.addProduction("class", TokenType.KEY_CLASS, TokenType.DEL_BRCKT_OP, TokenType.IDENTIFIER, TokenType.DEL_BRCKT_CL, TokenType.DEL_CURLY_OP, "statements", TokenType.DEL_CURLY_CL);
		grammar.addProduction("statements", "statement", "statements_prime");
		grammar.addProduction("statements_prime", "statement", "statements_prime");
		grammar.addProduction("statements_prime", TokenType.EPSILON);
		grammar.addProduction("statement", "if_statement");
		grammar.addProduction("statement", "for_statement");
		grammar.addProduction("statement", "while_statement");
		grammar.addProduction("statement", "declaration_statement");
		grammar.addProduction("statement", "inner_statement", TokenType.SEMICOLON);
		grammar.addProduction("inner_statement", "assignment_statement");
		grammar.addProduction("inner_statement", "yield_method");
		grammar.addProduction("if_statement", TokenType.KEY_IF, "logical_expression", TokenType.KEY_THEN, "inner_statement", TokenType.SEMICOLON, "elseif_statements");
		grammar.addProduction("elseif_statements", "elseif_statement", "elseif_statements_prime");
		grammar.addProduction("elseif_statements_prime", "elseif_statement", "elseif_statements_prime");
		grammar.addProduction("elseif_statements_prime", TokenType.EPSILON);
		grammar.addProduction("elseif_statement", TokenType.KEY_ELSEIF, "logical_expression", TokenType.KEY_THEN, "inner_statement", TokenType.SEMICOLON);
		grammar.addProduction("while_statement", TokenType.KEY_WHILE, "logical_expression", TokenType.KEY_DO, "inner_statement", TokenType.SEMICOLON);
		grammar.addProduction("for_statement", TokenType.KEY_FOR, "arithmetic_expression", TokenType.KEY_TO, "arithmetic_expression", TokenType.KEY_DO, "inner_statement", TokenType.KEY_STEP, "arithmetic_expression", TokenType.KEY_END, TokenType.SEMICOLON);
		grammar.addProduction("declaration_statement", TokenType.KEY_DECLARE, TokenType.COLON, TokenType.DATATYPE_INTEGER, TokenType.COLON, TokenType.IDENTIFIER, TokenType.ASSIGN, "assignment_value", TokenType.SEMICOLON);
		grammar.addProduction("assignment_statement", TokenType.IDENTIFIER, TokenType.ASSIGN, "assignment_value");
		grammar.addProduction("assignment_value", "arithmetic_expression");
		grammar.addProduction("assignment_value", "store_method");
		grammar.addProduction("arithmetic_expression", "arithmetic_term", "arithmetic_expression_prime");
		grammar.addProduction("arithmetic_expression_prime", TokenType.ARI_ADD, "arithmetic_term", "arithmetic_expression_prime");
		grammar.addProduction("arithmetic_expression_prime", TokenType.ARI_SUB, "arithmetic_term", "arithmetic_expression_prime");
		grammar.addProduction("arithmetic_expression_prime", TokenType.EPSILON);
		grammar.addProduction("arithmetic_term", "arithmetic_factor", "arithmetic_term_prime");
		grammar.addProduction("arithmetic_term_prime", TokenType.ARI_MUL, "arithmetic_factor", "arithmetic_term_prime");
		grammar.addProduction("arithmetic_term_prime", TokenType.ARI_DIV, "arithmetic_factor", "arithmetic_term_prime");
		grammar.addProduction("arithmetic_term_prime", TokenType.ARI_MOD, "arithmetic_factor", "arithmetic_term_prime");
		grammar.addProduction("arithmetic_term_prime", TokenType.EPSILON);
		grammar.addProduction("arithmetic_factor", TokenType.IDENTIFIER);
		grammar.addProduction("arithmetic_factor", TokenType.LIT_INT);
		grammar.addProduction("relational_expression", "arithmetic_expression", "relational_operator", "arithmetic_expression", "relational_expression_prime");
		grammar.addProduction("relational_expression_prime", "relational_operator", "arithmetic_expression", "relational_expression_prime");
		grammar.addProduction("relational_expression_prime", TokenType.EPSILON);
		grammar.addProduction("relational_operator", TokenType.REL_GRTR_EQ);
		grammar.addProduction("relational_operator", TokenType.REL_LESS_EQ);
		grammar.addProduction("relational_operator", TokenType.REL_GRTR);
		grammar.addProduction("relational_operator", TokenType.REL_LESS);
		grammar.addProduction("relational_operator", TokenType.REL_EQ);
		grammar.addProduction("relational_operator", TokenType.REL_NOT_EQ);
		grammar.addProduction("logical_expression", "relational_expression", "logical_expression_prime");
		grammar.addProduction("logical_expression_prime", "logical_operator", "relational_expression", "logical_expression_prime");
		grammar.addProduction("logical_expression_prime", TokenType.EPSILON);
		grammar.addProduction("logical_operator", TokenType.LOG_AND); 
		grammar.addProduction("logical_operator", TokenType.LOG_OR);
		grammar.addProduction("store_method", TokenType.METHOD_STORE, TokenType.DEL_PAREN_OP, TokenType.DEL_PAREN_CL);
		grammar.addProduction("yield_method", TokenType.METHOD_YIELD, TokenType.DEL_PAREN_OP, "yield_param", TokenType.DEL_PAREN_CL);
		grammar.addProduction("yield_param", "arithmetic_expression");
		grammar.addProduction("yield_param", TokenType.LIT_STRING);
		grammar.buildGrammar();
	}

	public static ParseNode parseStream(String path) throws IOException {
		return parseStream(Lexer.analyze(path));
	}

	public static ParseNode parseStream(TokenStream stream) {
		return grammar.parse(stream);
	}

	public static void main(String[] args) throws IOException {
		ParseNode node = parseStream(args[0]);

		for (String s : ((ParseNonTerminal) node).depthFirst()) {
			System.out.println(s);
		}
	}

}