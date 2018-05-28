package lina.parser.parsetree;

import lina.lexer.tokenizer.Token;

public class ParseTerminal extends Token implements ParseNode {

	public ParseTerminal(Token token) {
		super(token);
	}

	@Override
	public String toString() {
		return String.format("%s('%s')", super.getType().name(), super.getLexeme());
	}
}