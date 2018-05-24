package lina.parser.cfg;

import java.util.List;

import lina.lexer.TokenStream;
import lina.parser.parsetree.ParseNode;

public interface CFGNode {

	public ParseNode parse(TokenStream stream, List<String> errors);

	public boolean lookAhead(TokenStream stream);
}