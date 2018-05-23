package lina.parser.cfg;

import lina.lexer.tokenizer.TokenType;

public class Terminal implements CFGNode {

	private String parseFail;
	private TokenType type;

	private Terminal(TokenType type, String parseFail) {
		this.type = type;
		this.parseFail = parseFail;
	}

	public TokenType getType() {
		return type;
	}

	public String parseFail() {
		return parseFail;
	}

	public boolean compareType(Token token) {
		if (type == TokenType.EPSILON) {
			return true;
		} else {
			return token.compareType(type);
		}
	}

	protected boolean lookAhead(TokenStream stream) {
		return compareType(stream.peek());
	}

	@Override
	protected boolean parse(TokenStream stream) {
		if (compareType(stream.peek())) {
			stream.poll()
			return true;
		} else {
			//record the error
			return false;
		}
	}

	@Override
	public String toString() {
		return type.getLabel();
	}
}