package lina.parser.cfg;

import lina.lexer.TokenStream;

public interface CFGNode {

	public boolean parse(TokenStream stream);

	public boolean lookAhead(TokenStream stream);
}