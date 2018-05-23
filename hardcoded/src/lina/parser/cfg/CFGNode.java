package lina.parser.cfg;

import lina.lexer.TokenStream;

public interface CFGNode {

	protected boolean parse(TokenStream stream);

	protected boolean lookAhead(TokenStream stream);
}