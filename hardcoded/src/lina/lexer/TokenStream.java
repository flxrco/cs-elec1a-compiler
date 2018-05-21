package lina.lexer;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import lina.lexer.tokenizer.Token;

public class TokenStream {
	List<Token> tokens;
	List<String> lines;

	public TokenStream(List<Token> tokens, List<String> lines) {
		this.tokens = new ArrayList<>();
		this.lines = new ArrayList<>();
		this.tokens.addAll(tokens);
		this.lines.addAll(lines);
	}

	public Queue<Token> toQueue() {
		LinkedList<Token> linkedList = new LinkedList<>();
		linkedList.addAll(tokens);
		return linkedList;
	}

	public String getLinePointer(Token token) {
		String line = lines.get(token.getLineNo());
		StringBuilder str = new StringBuilder(line);
		str.append("\n");

		for (int i = 0; i < line.length(); i++) {
			if (i == token.getStartCol()) {
				str.append("^");
			} else if (i > token.getStartCol()) {
				break;
			} else {
				str.append(" ");
			}
		}

		str.append("\n");
		return str.toString();
	}

	public String getLocationDetails(Token token) {
		StringBuilder str = new StringBuilder();
		str.append(getLinePointer(token));
		str.append(token.getLocation());
		return str.toString();
	}

	public String tokensToString() {
		StringBuilder str = new StringBuilder();

		for (Token token : tokens) {
			str.append(token.toString());
			str.append("\n");
		}

		return str.toString();
	}

	public String linesToString() {
		StringBuilder str = new StringBuilder();

		for (String line : lines) {
			str.append(line);
			str.append("\n");
		}

		return str.toString();
	}
}