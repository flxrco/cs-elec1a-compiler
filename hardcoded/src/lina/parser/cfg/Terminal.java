package lina.parser.cfg;

import java.util.List;
import java.util.ArrayList;

import lina.lexer.tokenizer.Token;
import lina.lexer.tokenizer.TokenType;
import lina.lexer.TokenStream;
import lina.parser.parsetree.ParseNode;
import lina.parser.parsetree.ParseTerminal;

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
	public ParseNode parse(TokenStream stream, List<String> errors) {
		Token tok = stream.peek();

		ParseTerminal node = new ParseTerminal(tok);

		if (!compareType(tok)) {
			StringBuilder str = new StringBuilder();
			str.append(String.format("%d: %s expected but saw %s instead\n", tok.getLineNo() + 1, type.getPattern(), tok.getType().getPattern()));
			str.append(stream.getPointer(tok, "          "));
			str.append(String.format("\tsymbol: %s %s\n", tok.getTypeLabel(), tok.getLexeme()));
			str.append(String.format("\tlocation: %s", tok.getCoordinates()));

			errors.add(str.toString());

			return null;
		} else {
			if (type != TokenType.EPSILON) {
				stream.poll();
			}
		}

		return node;
	}

	@Override
	public String toString() {
		return type.getLabel();
	}
}