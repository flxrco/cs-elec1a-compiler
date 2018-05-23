package lina.parser.cfg;

import lina.lexer.tokenizer.Token;
import lina.lexer.tokenizer.TokenType;
import lina.lexer.TokenStream;

public class Terminal implements CFGNode {

	private TokenType type;

	public Terminal(TokenType type) {
		this.type = type;
	}

	public TokenType getType() {
		return type;
	}

	public boolean compareType(Token token) {
		if (type == TokenType.EPSILON) {
			return true;
		} else {
			return token.compareType(type);
		}
	}

	public boolean lookAhead(TokenStream stream) {
		return compareType(stream.peek());
	}

	@Override
	public boolean parse(TokenStream stream) {
		if (compareType(stream.peek())) {
			if (type != TokenType.EPSILON) {
				stream.poll();
			}
			return true;
		} else {
			System.out.println(String.format("Parse failure: Expected %s but instead saw %s", type.getPattern(), stream.peek().getType().getPattern()));
			System.out.println();
			System.out.println(stream.getLocation(stream.peek()));
			return false;
		}
	}

	@Override
	public String toString() {
		return type.getLabel();
	}
}